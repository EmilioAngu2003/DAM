/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut03.ejemplos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Fran
 */
public class clienteUDP2 {
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException {
     
        //FLUJO PARA ENTRADA ESTANDAR
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in) );
        DatagramSocket clienteSocket = new DatagramSocket();
        byte [] enviados = new byte[1024];
        byte [] recibidos = new byte[1024];
        //DATOS DEL SERVIDOR
        InetAddress IPServidor = InetAddress.getLocalHost();
        int port = 9876;
        //INTRODUCIR DATOS POR TECLADO
        System.out.println("Introduce un mensaje:" );
        String cadena = in.readLine();
        enviados =  cadena.getBytes();
        
        //ENVIANDO DATAGRAMA
        System.out.println("Enviando " + enviados.length + " bytes al servidor");
        DatagramPacket envio = new DatagramPacket (enviados,enviados.length,IPServidor,port);
        clienteSocket.send(envio);
        
        
        //RECIBIENDO DATAGRAMA DEL SERVIDOR
        
        DatagramPacket recibo = new DatagramPacket(recibidos,recibidos.length);
        System.out.println("Esperando datagrama ...");
        clienteSocket.receive(recibo);
        String mayuscula  = new String(recibo.getData());
        
        //OBTENIENDO INFORMACIÓN DEL DTATAGRAMA
   
        System.out.println("Mensaje "+mayuscula);
        System.out.println("IP origen "+recibo.getAddress());
        System.out.println("Puerto origen "+recibo.getPort());
        
        //ENVIO DEL DATAGRAMA
  
        clienteSocket.close();
        
        
    }
}
