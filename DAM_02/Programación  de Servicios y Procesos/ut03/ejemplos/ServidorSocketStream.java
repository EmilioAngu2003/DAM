/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ut03.ejemplos;

import java.net.*;
import java.io.*;

/**
 *
 * @author Fran Huertas
 */
public class ServidorSocketStream {
    public static void main(String[] args) {
        try {
            Alumno a;
            System.out.println("Creando el socket del servidor");
            
            //Crear objeto Server Socket
            ServerSocket serverSocket = new ServerSocket();
            
            System.out.println("Haciendo el bind");
          //Dirección y puerto de escucha
            InetSocketAddress direccion = new InetSocketAddress("localhost",6666);
            
            //Enlace del SErver Socket con la dirección y puerto
            serverSocket.bind(direccion);
            
            
            System.out.println("Aceptando conexiones");
           //Pongo el Server Socket en escucha para aceptar clientes
            
            Socket s = serverSocket.accept();
            
            System.out.println("Conexión recibida");
            System.out.println("Dirección Local "+s.getLocalAddress());
            System.out.println("Puerto Local "+s.getLocalPort());
            System.out.println("Dirección Remota "+s.getRemoteSocketAddress());
            System.out.println("Puerto Remoto "+ s.getPort());
          
           ObjectInputStream is = new ObjectInputStream(s.getInputStream());
           DataOutputStream os = new DataOutputStream(s.getOutputStream());   
           
        //   byte[] mensaje = new byte[25];
           //InputStreamReader isr = new InputStreamReader(is);
           //BufferedInputStream bis = new BufferedInputStream(is);
          
           while (s.isConnected()){
               Object obj = is.readObject();
             //  System.out.println(""+obj.getClass());
               if (obj.getClass().equals(Alumno.class)){
                   a = (Alumno)obj;
                   System.out.println("Objeto Alumno: " + a);
               }
              else      
               System.out.println("Mensaje " + obj);
           }
        
            
            System.out.println("Cerrando el socket");
            
            s.close();
            System.out.println("Cerrando el socket servidor");
            serverSocket.close();
            System.out.println("Finalizado");
            
            
        }catch (Exception e){
         //   e.printStackTrace();
        }
    }
}
