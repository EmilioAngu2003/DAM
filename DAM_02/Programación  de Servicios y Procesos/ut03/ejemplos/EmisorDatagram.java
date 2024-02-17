/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ut03.ejemplos;
import java.io.*;
import java.net.*;
/**
 *
 * @author Fran Huertas
 */
public class EmisorDatagram {
 
    public static void main(String[] args) {
        try{
            System.out.println("Creando socket datagram");
            DatagramSocket datagramSocket = new DatagramSocket();
            System.out.println("Enviando");
            String mensaje = "Hola hola hola";
            String mensaje2 = "adios adios adios";
            InetAddress direccion =  InetAddress.getByName("226.0.0.0");
            InetAddress direccion2 =  InetAddress.getByName("226.0.0.1");
            DatagramPacket datagrama = new DatagramPacket(mensaje.getBytes(), 
                                    mensaje.getBytes().length,
                                    direccion,
                                    6666);
            DatagramPacket datagrama2 = new DatagramPacket(mensaje2.getBytes(), 
                                    mensaje2.getBytes().length,
                                    direccion2,
                                    6666);
            datagramSocket.send(datagrama);
            datagramSocket.send(datagrama2);
            System.out.println("Mensaje enviado");
            System.out.println("Cerrando Datagrama");
            datagramSocket.close();
            System.out.println("Terminado");
            
        }catch (Exception e){}
    }
}
