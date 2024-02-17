
package ut03.propuestos.ejercicio04;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public abstract class Cliente implements Runnable{
    
    public static void main(String[] args) throws IOException {
        
        String host = "localhost";
        int puertoEcho = 9998;
        int puertoHora = 9999;

        new Thread(new ClienteEcho(host, puertoEcho)).start();
        new Thread(new ClienteHora(host, puertoHora)).start();
    }
    
    protected int id;
    protected final Socket socket;
    protected final String host;
    protected final int port;
    protected final DataInputStream dis;
    
    public Cliente(String host, int port) throws IOException{
        this.socket = new Socket(host, port);
        this.host = host;
        this.port = port;
        this.dis = new DataInputStream(socket.getInputStream());
    }
    
    protected void recibirId(){
        try {
            id = dis.readInt();
        } catch (IOException e) {
            id = 0;
            System.out.println("Exception " + e);
        }
        
        System.out.println("Cliente " + id + " contectado en el puerto " + port + " de " + host);
    }

    private static class ClienteEcho extends Cliente{
        
        public ClienteEcho(String host, int port) throws IOException{
            super(host, port);
        }
        
        @Override
        public void run() {
            
            recibirId();
            
            try (
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                Scanner sc = new Scanner(System.in); dis; socket
            ) {

                System.out.println("Cliente " + id + " | Conectado al servidor Echo" );

                boolean continuar;
                String linea;

                do {
                    linea = sc.nextLine();
                    pw.println(linea);
                    continuar = !linea.equalsIgnoreCase("bye");
                    if (continuar) {
                        String echo = br.readLine();
                        System.out.println("Cliente " + id + " | " + echo);
                    }
                } while (continuar);

                System.out.println("Cliente " + id + " | Desconectado del servidor Echo");

            } catch (IOException e) {
                System.out.println("Cliente " + id + " | Exception " + e);
            }
        }
    }
    
    private static class ClienteHora extends Cliente{
        
        public ClienteHora(String host, int port) throws IOException{
            super(host, port);
        }
        
        @Override
        public void run() {
            
            recibirId();
            
            try (
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())); dis; socket
            ) {
                System.out.println("Cliente " + id + " | Conectado al servidor Hora" );

                String hora = br.readLine();
                System.out.println(hora);

                System.out.println("Cliente " + id + " | Desconectado del servidor Hora");

            } catch (IOException e) {
                System.out.println("Cliente " + id + " | Exception " + e);
            }
        }
    }
}
