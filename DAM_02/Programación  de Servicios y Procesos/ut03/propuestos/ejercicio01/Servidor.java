
package ut03.propuestos.ejercicio01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        
        final int PORT = 12345;

        try (ServerSocket servidorSocket = new ServerSocket(PORT)) {
            
            System.out.println("Esperando conexiones en el puerto " + PORT);

            while (true) {
                try (Socket clienteSocket = servidorSocket.accept();
                     BufferedReader br = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                     PrintWriter pw = new PrintWriter(clienteSocket.getOutputStream(), true)) {

                    System.out.println("Conectado " + clienteSocket.getInetAddress().getHostAddress());

                    String linea;
                    while ((linea = br.readLine()) != null) {
                        if (linea.equalsIgnoreCase("Bye")) {
                            System.out.println("Desconectado");
                            break;
                        }else{
                            System.out.println("cliente: " + linea);
                            pw.println("echo: " + linea);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Excepcion " + e);
                }
            }
        } catch (IOException e) {
            System.out.println("Excepcion " + e);
        }
    }
}
