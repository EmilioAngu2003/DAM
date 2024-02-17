/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ut03.ejemplos;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fran Huertas
 */
public class ReceptorDatagram {
    
    static String dg2str(DatagramPacket p){
        String s="";
        byte [] b=p.getData();
        for (int i=0;i<p.getLength();i++)
            s+=(char)b[i];
        return s;
    }
    public static void main(String[] args) {
            try {
                System.out.println("Creando socket datagram");
            
                InetAddress direccion = InetAddress.getByName("226.0.0.0");
                InetAddress direccion2 = InetAddress.getByName("226.0.0.1");
                MulticastSocket datagramSocket = new MulticastSocket(6666);
                datagramSocket.joinGroup(direccion);
                datagramSocket.joinGroup(direccion2);
                
                System.out.println("Recibiendo");
                //String mensaje = "Mensaje del emisor";
                byte [] ms = new byte[1000];
                
                
                DatagramPacket dp1 = new DatagramPacket(ms,1000);
                
                datagramSocket.receive(dp1);
           
                //System.out.println(""+dp1.getLength());
                
                System.out.println("REcibido " + dg2str(dp1));
                datagramSocket.receive(dp1);
                System.out.println("REcibido " + dg2str(dp1));
                System.out.println("Cerrando Receptor");
                datagramSocket.close();
                System.out.println("Terminado");
                
                
            } catch (Exception ex) {
                
            }
            
            
    }
}
