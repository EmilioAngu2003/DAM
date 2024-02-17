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
import java.io.IOException; 
import java.net.DatagramSocket;
import java.net.InetAddress; 
import java.net.DatagramPacket; 
import java.util.Date; 
 public class MulticastServer {
     public MulticastServer() { 
         try{ 
             DatagramSocket socket = new DatagramSocket();
             while (true) { 
                byte buffer[] = new byte[256]; 
                String date = new Date().toString();
                buffer = date.getBytes(); 
                InetAddress address = InetAddress.getByName("224.0.0.0"); 
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000); 
                socket.send(packet); 
                socket.receive(packet);
                try{ 
                    Thread.sleep(10000);
                } // 10 sec } 
                catch (InterruptedException e){ 
            
                    e.printStackTrace();
                } 
            } // while 
         } catch(Exception e) {
            System.out.println(""+e);
         } 
     } 
     public static void main(String args[])
        throws IOException { new MulticastServer();
     } 
 }