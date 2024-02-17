
package ut03.propuestos.ejercicio02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Servidores {

    public static void main(String[] args) {
        
        int puertoEcho = 9998;
        int puertoHora = 9999;

        new Thread(() -> servidorEcho(puertoEcho)).start();
        new Thread(() -> servidorHora(puertoHora)).start();
    }

    private static void servidorEcho(int puerto) {
        try {
            ServerSocket servidorSocket = new ServerSocket(puerto);
            System.out.println("Servicio Echo iniciado en el puerto " + puerto);
            while (true) {
                Socket clienteSocket = servidorSocket.accept();
                System.out.println("Cliente conectado al servicio Echo");
                new Thread(() -> echo(clienteSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Exception " + e);
        }
    }

    private static void echo(Socket clienteSocket) {
        try (
            BufferedReader br = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            PrintWriter pw = new PrintWriter(clienteSocket.getOutputStream(), true)
        ) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println("cliente: " + linea);
                pw.println("echo: " + linea);
            }

            System.out.println("Cliente desconectado");
            clienteSocket.close();
        } catch (IOException e) {
            System.out.println("Exception " + e);
        }
    }

    private static void servidorHora(int puerto) {
        try {
            ServerSocket servidorSocket = new ServerSocket(puerto);
            System.out.println("Servicio Hora iniciado en el puerto " + puerto);

            while (true) {
                Socket clienteSocket = servidorSocket.accept();
                System.out.println("Cliente conectado al servicio Hora");
                new Thread(() -> hora(clienteSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Exception " + e);
        }
    }

    private static void hora(Socket clienteSocket) {
        try (
            PrintWriter pw = new PrintWriter(clienteSocket.getOutputStream(), true)
        ) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String hora = sdf.format(new Date());
            pw.println("Hora: " + hora);

            System.out.println("Cliente desconectado");
            clienteSocket.close();
        } catch (IOException e) {
            System.out.println("Exception " + e);
        }
    }
}

