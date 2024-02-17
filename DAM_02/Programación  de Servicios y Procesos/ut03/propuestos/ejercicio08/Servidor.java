
package ut03.propuestos.ejercicio08;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Servidor {
    
    public static void main(String[] args) {
        ArrayList<ClienteManager> clientesManager = new ArrayList<>();
        Chat chat = new Chat();
        SwingUtilities.invokeLater(chat);
        iniciarServidor(chat, clientesManager);
    }
    
    private static class Chat extends JFrame implements Runnable{

        private final JTextArea text = new JTextArea();

        @Override
        public void run() {
            setTitle("Chat - Servidor");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 300);
            text.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(text);
            add(scrollPane, BorderLayout.CENTER);
            setVisible(true);
        }
    }
    
    private static void iniciarServidor(Chat chat, ArrayList<ClienteManager> clientesManager) {
        try (
            ServerSocket socket = new ServerSocket(12345)
        ){
            while (true) {
                Socket clienteSocket = socket.accept();
                ClienteManager clienteManager = new ClienteManager(clienteSocket, chat, clientesManager);
                clientesManager.add(clienteManager);
                new Thread(clienteManager).start();
            }
        } catch (IOException e) {
            System.out.println("Error de Servidor: " + e);
        }
    }
    
    private static class ClienteManager implements Runnable {
        private final Socket clienteSocket;
        private final Chat chat;
        private final ArrayList<ClienteManager> clientesManager;
        private BufferedReader br;
        private PrintWriter pw;
        private String nombre;

        public ClienteManager(Socket clienteSocket, Chat chat, ArrayList<ClienteManager> clientesManager){
            this.clienteSocket = clienteSocket;
            this.chat = chat;
            this.clientesManager = clientesManager;
        }

        @Override
        public void run() {
            try {
                br = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                pw = new PrintWriter(clienteSocket.getOutputStream(), true);

                String cadenaNombre = br.readLine();
                if (cadenaNombre != null && cadenaNombre.startsWith("HOLA ")) {
                    nombre = cadenaNombre.substring(5);
                    mensaje("Bienvenido " + nombre + " al chat!");
                } else {
                    desconectar();
                    return;
                }

                String mensaje;
                while ((mensaje = br.readLine()) != null) {
                    if (mensaje.equals("ADIOS")) {
                        mensaje(nombre + " se ha desconectado.");
                        desconectar();
                        return;
                    } else {
                        String formato = String.format("%s:%s (%s)", nombre, mensaje, getHora());
                        mensaje(formato);
                    }
                }

            } catch (IOException e) {
                System.out.println("Error de Manager: " + e);
            }
        }

        private void mensaje(String message) {
            chat.text.append(message + "\n");
            for(ClienteManager cm:clientesManager){
                cm.pw.println(message);
            }
        }

        private void desconectar() {
            try {
                br.close();
                pw.close();
                clienteSocket.close();
            } catch (IOException e) {
                System.out.println("Error de Desconexion: " + e);
            }
        }

        private String getHora() {
            SimpleDateFormat sdf=  new SimpleDateFormat("HH:mm");
            return sdf.format(new Date());
        }
    }
}
