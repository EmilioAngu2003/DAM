
package ut03.propuestos.ejercicio07;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Progarma {

    private static final int PUERTO_TOKKEN = 1234;
    private static final int PUERTO_PREGUNTA = 5678;
    private static final int PUERTO_RESPUESTA = 8765;
    private static final String BROADCAST =  "192.168.1.255";
    private static DatagramUtils datagramPregunta;
    private static DatagramUtils datagramRespuesta;
    private static DatagramUtils datagramTokken;
    private static List<String> direcciones;
    private static String IP;
    private static String TOKKEN;
    
    public static void main(String[] args) {
        
        IP = "";
        TOKKEN = "";
        
        direcciones = new ArrayList<>();
        
        datagramRespuesta = new DatagramUtils(PUERTO_RESPUESTA, BROADCAST);
        datagramPregunta = new DatagramUtils(PUERTO_PREGUNTA, BROADCAST);
        datagramTokken = new DatagramUtils(PUERTO_TOKKEN, BROADCAST);
        
        new Thread(new Respuesta()).start();
        new Thread(new Pregunta()).start();
        new Thread(new Cliente()).start();
        new Thread(new Servidor()).start();
    }
    
    static class Pregunta implements Runnable{

        @Override
        public void run() {  
            datagramPregunta.sendObject(direcciones);
            
            while(true) 
            {
                Object object = datagramRespuesta.receiveObject();

                if(object != null)
                {   
                    if( object instanceof List<?> lista)
                    {
                        if(lista.isEmpty())
                        {
                            if(datagramPregunta.countDirecciones() == 1)
                            {
                                IP = datagramPregunta.getIp();
                                System.out.println(IP);
                                direcciones.add(IP);
                            }
                        }
                        else
                        {
                            direcciones = (List<String>) object;
                        }
                    }
                }
            }
        }
    }
    
    static class Respuesta implements Runnable{

        @Override
        public void run() {  
            while(true) 
            {
                Object object = datagramPregunta.receiveObject();
                
                if(object != null)
                {
                    if( object instanceof List<?> lista)
                    {
                        if(lista.isEmpty())
                        {
                            if(!direcciones.isEmpty())
                            {
                                direcciones.add(datagramPregunta.getIp());
                            }
                            datagramRespuesta.sendObject(direcciones);
                        }
                    }
                }
            }
        }
    }

    static class Cliente implements Runnable{

        @Override
        public void run() {
            try (
                Scanner sc = new Scanner(System.in);
            ){
                while(true) 
                {
                    TOKKEN = sc.next();

                    if (TOKKEN != null && !TOKKEN.isEmpty()) 
                    {
                        send(TOKKEN);
                    }
                }
            }  
        }
    }

    static class Servidor implements Runnable{

        @Override
        public void run() {
            while (true) 
            {    
                Object object = datagramTokken.receiveObject();

                if(object instanceof String tokken)
                {
                    if(tokken.equals(TOKKEN))
                    {
                        System.out.println("El Tokken " + tokken + " ha vuelto");
                    }
                    else
                    {
                        System.out.println("Tokken: " + tokken);
                        send(tokken);
                    }

                }
            }
        }
    }
    
    static void send(String tokken){
        System.out.println("Orden: " + direcciones);
        int index = direcciones.lastIndexOf(IP) + 1;              
        if(index == direcciones.size())
        {
            index = 0;
        }
        
        String destino = direcciones.get(index);
        System.out.println("Destino: " + destino);
        datagramTokken.setDireccion(destino);
        datagramTokken.sendObject(tokken);
    }
}

class DatagramUtils {

    private final int puerto;
    private final Set<String> countDirecciones;
    private String direccion;
    private String ip;

    public DatagramUtils(int puerto, String direccion) {
        this.ip = "";
        this.puerto = puerto;
        this.direccion = direccion;
        this.countDirecciones = new HashSet<>();
    }

    public void sendObject(Object object) {
        try (DatagramSocket ds = new DatagramSocket()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.flush();
            byte[] data = baos.toByteArray();
            InetAddress address = InetAddress.getByName(direccion);
            DatagramPacket dp = new DatagramPacket(data, data.length, address, puerto);
            ds.send(dp);
        } catch (Exception e) {
            System.out.println("Error Enviar: " + e);
        }
    }

    public Object receiveObject() {
        try (DatagramSocket ds = new DatagramSocket(puerto)) {
            byte[] buffer = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            ds.receive(dp);
            synchronized(countDirecciones){
                ip = dp.getAddress().getHostAddress();
                countDirecciones.add(ip);
            }
            ByteArrayInputStream bais = new ByteArrayInputStream(dp.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            System.out.println("Error Recibir: " + e);
            return null;
        }
    }

    public int countDirecciones() {
        return countDirecciones.size();
    }

    public String getIp() {
        return ip;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}



