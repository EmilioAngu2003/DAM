
package ut03.propuestos.ejercicio08;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente extends JFrame {

    private final JTextField mensaje;
    private final JTextArea chat;
    private BufferedReader br;
    private PrintWriter pw;

    public Cliente() {
        setTitle("Chat Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        mensaje = new JTextField();
        JButton enviar = new JButton("Enviar");
        enviar.addActionListener(new enviar());

        chat = new JTextArea();
        chat.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(chat);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(mensaje, BorderLayout.CENTER);
        panel.add(enviar, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        conectar();

        setVisible(true);
    }

    private void conectar() {
        try {
            Socket socket = new Socket("localhost", 12345);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);

            String cadenaNombre = JOptionPane.showInputDialog("Ingresa 'HOLA' seguido de tu nombre:");
            pw.println(cadenaNombre);

            String bienvenida = br.readLine();
            chat.append(bienvenida + "\n");

            new Thread(new chatear()).start();

        } catch (IOException e) {
            System.out.println("Error de Conexion: " + e);
        }
    }

    private class enviar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = mensaje.getText();
            if (!text.isEmpty()) {
                pw.println(text);
                mensaje.setText("");
            }
        }
    }

    private class chatear implements Runnable {
        @Override
        public void run() {
            try {
                String mensaje;
                while ((mensaje = br.readLine()) != null) {
                    chat.append(mensaje + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error de Chatear: " + e);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Cliente());
    }
}
