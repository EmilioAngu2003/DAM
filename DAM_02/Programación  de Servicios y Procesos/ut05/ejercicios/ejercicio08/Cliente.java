package ut05.ejercicios.ejercicio08;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class Cliente {

    static final String IP = "localhost";
    static final int PORT = 9999;
    static final String TRUSTSTORE_NAME = "client.truststore";
    static final String TRUSTSTORE_PATH = System.getProperty("user.dir") + File.separator +
            "src" + File.separator +
            "ut05" + File.separator +
            "ejercicios" + File.separator +
            "ejercicio08" + File.separator +
            TRUSTSTORE_NAME;
    static final String TRUSTSTORE_PASSWORD = "password";

    public static void main(String[] args) {
        new Cliente().iniciarCliente();
    }

    public void iniciarCliente() {
        try {
            KeyStore trustStore = cargarAlmacenConfianza();
            SSLContext sslContext = configurarContextoSSL(trustStore);
            SSLSocket sslSocket = crearSocketCliente(sslContext);
            comunicarseConServidor(sslSocket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private KeyStore cargarAlmacenConfianza() throws Exception {
        KeyStore trustStore = KeyStore.getInstance("JKS");
        trustStore.load(new FileInputStream(TRUSTSTORE_PATH), TRUSTSTORE_PASSWORD.toCharArray());
        return trustStore;
    }

    private SSLContext configurarContextoSSL(KeyStore trustStore) throws Exception {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(trustStore);
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
        return sslContext;
    }

    private SSLSocket crearSocketCliente(SSLContext sslContext) throws IOException {
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(IP, PORT);
        return sslSocket;
    }

    private void comunicarseConServidor(SSLSocket sslSocket) throws IOException {
        try (
                PrintWriter writer = new PrintWriter(sslSocket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
                BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String userInput;
            while ((userInput = userInputReader.readLine()) != null) {
                writer.println(userInput);
                String response = reader.readLine();
                System.out.println("Respuesta del servidor: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sslSocket.close();
        }
    }
}
