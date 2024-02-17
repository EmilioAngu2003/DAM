
package ut03.propuestos.ejercicio03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        
        final int PUERTO = 12345;

        try (ServerSocket servidorSocket = new ServerSocket(PUERTO)) {
            System.out.println("Esperando conexiones en el puerto " + PUERTO);

            while (true) {
                try {
                    Socket clienteSocket = servidorSocket.accept();
                    Thread clienteManager = new Thread(new ClienteManager(clienteSocket));
                    clienteManager.start();

                } catch (IOException e) {
                    System.out.println("Exception " + e);
                }
            }
        } catch (IOException e) {
            System.out.println("Exception " + e);
        }
    }

    private static class ClienteManager implements Runnable {
        
        private static int nextId = 1;
        private final int id;
        private final Socket clienteSocket;

        public ClienteManager(Socket clienteSocket) {
            this.clienteSocket = clienteSocket;
            this.id = nextId++;
        }

        @Override
        public void run() {
            
            System.out.println("cliente " + id + " conectado: " + clienteSocket.getInetAddress().getHostAddress());
            
            try (BufferedReader br = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                 PrintWriter pw = new PrintWriter(clienteSocket.getOutputStream(), true)) {

                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.equalsIgnoreCase("Bye")) {
                        System.out.println("cliente " + id + " desconectado: " + clienteSocket.getInetAddress().getHostAddress());
                        break;
                    }else{
                        System.out.println("cliente " + id + ": " + linea);
                        pw.println("echo: " + linea);
                    }
                }
            } catch (IOException e) {
                System.out.println("Exception " + e);
            } finally {
                try {
                    clienteSocket.close();
                } catch (IOException e) {
                    System.out.println("Exception " + e);
                }
            }
        }
    }
}
