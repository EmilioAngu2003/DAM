package ut05.ejercicios.ejercicio08;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class Servidor {

    static final int PORT = 9999;
    static final String KEYSTORE_NAME = "server.keystore";
    static final String KEYSTORE_PATH = System.getProperty("user.dir") + File.separator +
            "src" + File.separator +
            "ut05" + File.separator +
            "ejercicios" + File.separator +
            "ejercicio08" + File.separator +
            KEYSTORE_NAME;
    static final String KEYSTORE_PASSWORD = "password";

    public static void main(String[] args) {
        new Servidor().iniciarServidor();
    }

    public void iniciarServidor() {
        try {
            KeyStore keyStore = cargarAlmacenClaves();
            SSLContext sslContext = configurarContextoSSL(keyStore);
            SSLServerSocket sslServerSocket = crearSocketServidor(sslContext);
            esperarConexiones(sslServerSocket);
        } catch (Exception e) {
            System.out.println("Error de inicio del servidor");
        }
    }

    private KeyStore cargarAlmacenClaves() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(KEYSTORE_PATH), KEYSTORE_PASSWORD.toCharArray());
        return keyStore;
    }

    private SSLContext configurarContextoSSL(KeyStore keyStore) throws Exception {
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, KEYSTORE_PASSWORD.toCharArray());
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), null, null);
        return sslContext;
    }

    private SSLServerSocket crearSocketServidor(SSLContext sslContext) throws IOException {
        SSLServerSocketFactory sslServerSocketFactory = sslContext.getServerSocketFactory();
        SSLServerSocket sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(PORT);
        System.out.println("Servidor SSL iniciado en el puerto " + PORT + "...");
        return sslServerSocket;
    }

    private void esperarConexiones(SSLServerSocket sslServerSocket) throws IOException {
        int contador = 0;
        while (true) {
            SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();
            System.out.println("Cliente conectado desde " + sslSocket.getInetAddress());
            int id = ++contador;
            Thread clientHandlerThread = new Thread(() -> {
               manejarConexionCliente(sslSocket, id);
            });
            clientHandlerThread.start();
        }
    }

    private void manejarConexionCliente(SSLSocket sslSocket, int id) {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(sslSocket.getOutputStream(), true)
        ) {
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Mensaje del cliente " + id + ": " + message);
                String response = message.toUpperCase();
                writer.println(response);
            }
        } catch (IOException e) {
            System.out.println("Cliente " + id + " desconectado");
        } finally {
            try {
                sslSocket.close();
            } catch (IOException e) {
            }
        }
    }
}
