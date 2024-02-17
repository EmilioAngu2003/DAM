/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut03.ejemplos;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Fran
 */
public class ServidorUDP2 {
    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket serverSocket = new DatagramSocket (9876);
        byte [] enviados = new byte[1024];
        byte [] recibidos = new byte[1024];
        String cadena;
        while(true){
            System.out.println("Esperando datagramas ...");
            recibidos = new byte[1024];
            DatagramPacket paqRecibido = new DatagramPacket(recibidos,recibidos.length);
            serverSocket.receive(paqRecibido);
            
            InetAddress IPOrigen = paqRecibido.getAddress();
            int puerto = paqRecibido.getPort();
            
            cadena= new String(paqRecibido.getData());
            
            System.out.println("Mensaje: "+cadena);
            String mayuscula = cadena.trim().toUpperCase();
            enviados= mayuscula.getBytes();
            
            DatagramPacket paqEnviado = new DatagramPacket(enviados,enviados.length,IPOrigen,puerto);
            serverSocket.send(paqEnviado);
            if (cadena.trim().equals("*")) break;
        }
        serverSocket.close();
    }
}
