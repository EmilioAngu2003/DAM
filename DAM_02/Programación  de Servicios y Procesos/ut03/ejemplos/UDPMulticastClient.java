/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut03.ejemplos;

/**
 *
 * @author fran
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class UDPMulticastClient {

    JFrame frame;
    JPanel panel;
    JButton button1, button2;
    JTextArea area;
    JScrollPane pane;
    JTextField direccion,mensaje;
    Thread thread;
    MulticastSocket socket;

    public static void main(String[] args) {
        UDPMulticastClient u = new UDPMulticastClient();
    }

    public UDPMulticastClient() {
        frame = new JFrame("Broadcast Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        panel = new JPanel();
        panel.setLayout(null);
        direccion = new JTextField(10);
        mensaje = new JTextField(20);
        panel.add(direccion);
        panel.add(mensaje);
        area = new JTextArea();
        button1 = new JButton("Enviar");
        button1.setBounds(210, 10, 75, 40);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StartServer();
            }
        });
        panel.add(button1);
        button2 = new JButton("Stop");
        button2.setBounds(300, 10, 75, 40);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                thread.interrupt();
                socket.close();
                area.append("Server is stopped\n");
                button1.setEnabled(true);
                button2.setEnabled(false);
            }
        });
        button2.setEnabled(false);
        panel.add(button2);
        pane = new JScrollPane(area);
        pane.setBounds(10, 60, 365, 250);
        panel.add(pane);
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    public class StartServer implements Runnable {

        InetAddress address;

        StartServer() {
            thread = new Thread(this);
            thread.start();
            button1.setEnabled(false);
            button2.setEnabled(true);
        }

        public void run() {
            try {
                byte[] buffer = new byte[65535];
                int port = 5000;
                String address = "224.0.0.0";
                String address1 = "235.0.0.1";
                String address2 = "235.255.0.1";
                String address3 = "224.0.255.1";
                String addresStr[] = {address, address1, address2, address3};
                try {
                    socket = new MulticastSocket(port);
                    InetAddress add = InetAddress.getByName(address);
                    socket.joinGroup(add);

                    InetAddress add1 = InetAddress.getByName(address1);
                    socket.joinGroup(add1);
                    InetAddress add2 = InetAddress.getByName(address2);
                    socket.joinGroup(add2);
                    InetAddress add3 = InetAddress.getByName(address3);
                    socket.joinGroup(add3);
                    InetAddress str[] = {add, add1, add2, add3};
                    area.append("Server is started\n");
                    while (true) {
                        try {
                            //Receive request from client
                            for (int i = 0; i < str.length; i++) {
                                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, str[i], port);
                                socket.receive(packet);
                                addresStr[i] = packet.getAddress().toString();
                                InetAddress client = packet.getAddress();
                                int client_port = packet.getPort();
                                area.append("Received : '" + new String(buffer).trim() + "' from " + addresStr[i] + "\n");
                                // send information to the client
                                String message = "your request\n ";
                                buffer = message.getBytes();
                                packet = new DatagramPacket(buffer, buffer.length, client, client_port);
                                socket.send(packet);
                            }
                        } catch (UnknownHostException ue) {
                        }
                    }
                } catch (java.net.BindException b) {
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
