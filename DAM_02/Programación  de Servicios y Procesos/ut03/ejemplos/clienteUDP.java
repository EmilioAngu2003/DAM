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
import java.net.UnknownHostException;

/**
 *
 * @author Fran
 */
public class clienteUDP {
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException {
        InetAddress destino = InetAddress.getLocalHost();
        int port = 12345;
        byte [] mensaje = new byte[1024];
        String saludo = "Enviando saludos...";
        mensaje = saludo.getBytes();
        
        //CONSTRUYO EL DATAGRAMA A ENVIAR
        DatagramPacket envio =new DatagramPacket(mensaje,mensaje.length,destino,port);
        DatagramSocket socket = new DatagramSocket();
        System.out.println("Enviando mensaje longitud "+mensaje.length);
        System.out.println("Host destino "+destino.getHostName());
        System.out.println("IP destino "+destino.getHostAddress());
        System.out.println("Puerto local "+ socket.getLocalPort());
        System.out.println("Puerto destino "+ envio.getPort());
        
        //ENVIO DEL DATAGRAMA
        socket.send(envio);
        socket.close();
        
        
    }
}
