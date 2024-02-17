
package ut03.propuestos.ejercicio04;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Servidor {

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
                new Thread(new ClienteManagerEcho(clienteSocket)).start();
            }
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
                new Thread(new ClienteManagerHora(clienteSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Exception " + e);
        }
    }

    private static abstract class ClienteManager implements Runnable {
        
        private static int nextId = 1;
        protected final int id;
        protected final Socket clienteSocket;
        protected final DataOutputStream dos;

        public ClienteManager(Socket clienteSocket) throws IOException {
            this.clienteSocket = clienteSocket;
            this.id = nextId++;
            this.dos = new DataOutputStream(clienteSocket.getOutputStream());
        }
        
        protected void mandarId(){
            try { 
                dos.writeInt(id);
            } catch (IOException e) {
                System.out.println("Exception " + e);
            }
            
            System.out.println("cliente " + id + " conectado al puerto: " + clienteSocket.getPort());
        }
    }
    
    private static class ClienteManagerEcho extends ClienteManager{

        public ClienteManagerEcho(Socket clienteSocket) throws IOException {
            super(clienteSocket);
        }
        
        @Override
        public void run() {
            
            mandarId();

            try (
                BufferedReader br = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                PrintWriter pw = new PrintWriter(clienteSocket.getOutputStream(), true); clienteSocket; dos
            ) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.equalsIgnoreCase("bye")) {
                        break;
                    }else{
                        System.out.println("cliente " + id + ": " + linea);
                        pw.println("echo: " + linea);
                    }
                }
                System.out.println("cliente " + id + " desconectado del puerto: " + clienteSocket.getPort());
            } catch (IOException e) {
                System.out.println("Exception " + e);
            }
        }
    }
    
    private static class ClienteManagerHora extends ClienteManager{

        public ClienteManagerHora(Socket clienteSocket) throws IOException {
            super(clienteSocket);
        }
        
        @Override
        public void run() {
            
            mandarId();

            try (
                PrintWriter pw = new PrintWriter(clienteSocket.getOutputStream(), true); clienteSocket; dos
            ) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String hora = sdf.format(new Date());
                pw.println("Hora: " + hora);
                System.out.println("cliente " + id + " desconectado del puerto: " + clienteSocket.getPort());
            } catch (IOException e) {
                System.out.println("Exception " + e);
            }
        }
    }
}
