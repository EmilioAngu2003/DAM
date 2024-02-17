
package ut03.propuestos.ejercicio03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        
        final String SERVER_HOST = "localhost";
        final int SERVER_PORT = 12345;

        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado al servidor en " + SERVER_HOST + ":" + SERVER_PORT);
            
            boolean continuar;
            String linea;
            
            do {
                linea = scanner.nextLine();
                pw.println(linea);
                continuar = !linea.equalsIgnoreCase("Bye");
                if (continuar) {
                    String echo = br.readLine();
                    System.out.println(echo);
                }
            } while (continuar);

            System.out.println("Desconectado del servidor");

        } catch (IOException e) {
            System.out.println("Excepcion " + e);
        }
    }
}
