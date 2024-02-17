
package ut03.propuestos.ejercicio02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Clientes {

    public static void main(String[] args) {

        int puertoEcho = 9998;
        int puertoHora = 9999;

        ClienteEcho clienteEcho = new ClienteEcho(puertoEcho);
        ClienteHora clienteHora = new ClienteHora(puertoHora);

        new Thread(clienteEcho).start();
        new Thread(clienteHora).start();
    }

    static class ClienteEcho implements Runnable {
       
        private final int port;

        public ClienteEcho(int port) {
            this.port = port;
        }

        @Override
        public void run() {
            try {
                Socket socket = new Socket("localhost", port);
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                socket.getOutputStream().write("Hola, servidor echo\n".getBytes());
                socket.getOutputStream().flush();

                String respuesta = br.readLine();
                System.out.println(respuesta);

                socket.close();
            } catch (IOException e) {
                System.out.println("Exception " + e);
            }
        }
    }

    static class ClienteHora implements Runnable {
        
        private final int port;

        public ClienteHora(int port) {
            this.port = port;
        }

        @Override
        public void run() {
            try {
                Socket socket = new Socket("localhost", port);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                
                String respuesta = reader.readLine();
                System.out.println(respuesta);

                socket.close();
            } catch (IOException e) {
                System.out.println("Exception " + e);
            }
        }
    }
}
