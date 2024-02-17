/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut03.ejemplos;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author Fran
 */
public class servidorUDP {
    public static void main(String[] args) throws SocketException, IOException {
        
        byte [] buffer = new byte[1024];
        //SOCKET ASOCIADO AL PUERTO 12345
        DatagramSocket socket = new DatagramSocket(12345);
        //ESPERANDO DATAGRAMA
        System.out.println("Esperando el datagrama ....");
        DatagramPacket recibo = new DatagramPacket(buffer, buffer.length);
        socket.receive(recibo);
        int bytesRec = recibo.getLength(); //Número de bytes recibidos.
        String paquete = new String(recibo.getData());
        
        //MOSTRAR INFORMACION RECIBIDA
        System.out.println("Número de bytes recibidos "+ bytesRec);
        System.out.println("Contenido del paquete " +paquete.trim());
        System.out.println("IP de origen " + recibo.getAddress().getHostAddress());
        System.out.println("Puerto destino del mensaje: "+ socket.getLocalPort());
        socket.close();
        
        
    }
}
