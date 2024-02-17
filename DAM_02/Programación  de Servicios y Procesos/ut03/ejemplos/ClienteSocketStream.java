/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ut03.ejemplos;

import java.net.*;
import java.io.*;
import java.util.Calendar;

/**
 *
 * @author Fran Huertas
 */
class Alumno implements Serializable{
    String nombre;
    Alumno(String n){
        nombre=n;
    }
    public String toString(){
        return nombre;
    }
}

public class ClienteSocketStream {
 
    public static void main(String[] args) {
        try {
            Alumno a = new Alumno("Pepe");
            System.out.println("Creando el socket cliente");
            //Creo el Socket del cliente para conectar al servidor
            Socket clienteSocket = new Socket();
            int t[];
            System.out.println("Estableciendo la conexión");
           
            //2 crear la dirección de conexión
            InetSocketAddress direccion = new InetSocketAddress("localhost",6666);
            
            //Establece conexión con el servidor especificado en la dirección y puerto
            clienteSocket.connect(direccion);
         
            
           DataInputStream is = new DataInputStream(clienteSocket.getInputStream());
           ObjectOutput os = new ObjectOutputStream(clienteSocket.getOutputStream());
           
           System.out.println("Enviando mensaje");
           
           
           os.writeObject("Voy a enviar un objeto");
           os.writeObject(a);
           os.writeObject("adios");
           
           System.out.println("Mensaje enviado");
           
           System.out.println("Cerrando el socket cliente");
           clienteSocket.close();
           
           System.out.println("Socket Cerrado");
            
            
        }catch(Exception e){ e.printStackTrace();}
    }
}
