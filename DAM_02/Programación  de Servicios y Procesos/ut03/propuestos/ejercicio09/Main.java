
package ut03.propuestos.ejercicio09;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JOptionPane;

public class Main {

    private static final String BROADCAST = "192.168.1.255";
    private static final int PUERTO_PREGUNTA = 5678;
    private static final int PUERTO_RESPUESTA = 8765;
    private static final int PUERTO_JUEGO = 1234;
    private static DatagramUtils datagramPregunta;
    private static DatagramUtils datagramRespuesta;
    private static DatagramUtils datagramJuego;
    private static String IP_OPONENTE = "127.0.0.1";
    private static Respuesta respuesta;
    private static Pregunta pregunta;
    
    public static void main(String[] args) {
        
        datagramRespuesta = new DatagramUtils(PUERTO_RESPUESTA, IP_OPONENTE);
        datagramPregunta = new DatagramUtils(PUERTO_PREGUNTA, BROADCAST);
        
        respuesta = new Respuesta();
        pregunta = new Pregunta();
        
        respuesta.start();
        pregunta.start();
    }
    
    static class Pregunta extends Thread{

        @Override
        public void run() {
            
            boolean conexionAceptada = false;

            do{     
                try {
                    IP_OPONENTE = JOptionPane.showInputDialog("Ingresa la direccion IP de tu oponente:");

                    if(IP_OPONENTE != null && !IP_OPONENTE.isEmpty())
                    {
                        datagramPregunta.setDireccion(IP_OPONENTE);
                        datagramPregunta.sendObject(true);
                        System.out.println("Esperando respuesta...");
                        datagramRespuesta.setDireccion(IP_OPONENTE);
                        Object object = datagramRespuesta.receiveObject();
                        conexionAceptada = (boolean) object;
                        System.out.println("Respuesta: " + conexionAceptada);
                    }
                }
                catch (Exception ex)
                {
                    System.out.println("Conexion Pregunta Terminada");
                    break;
                }
            }while(!conexionAceptada && IP_OPONENTE != null);
            
            if(conexionAceptada || IP_OPONENTE == null)
            {
                datagramPregunta.disconnect();
                datagramRespuesta.disconnect();
            }
            if(conexionAceptada)
            {   
                Jugador jugador = new Jugador(true);
                jugador.start();
            }
        }
    }
    
    static class Respuesta extends Thread{

        @Override
        public void run() {
            
            boolean conexionAceptada = false;
            
            do{
                try {
                    datagramPregunta.setDireccion(BROADCAST);
                    datagramPregunta.receiveObject();
                    IP_OPONENTE = datagramPregunta.getIp();
                    datagramRespuesta.setDireccion(IP_OPONENTE);
                    int decision = JOptionPane.showConfirmDialog(null, "¿Aceptar la conexion desde " + IP_OPONENTE + "?", "Confirmacion", JOptionPane.YES_NO_OPTION);
                    conexionAceptada = decision == JOptionPane.YES_OPTION;
                    datagramRespuesta.sendObject(conexionAceptada);
                    System.out.println("Conexion: " + conexionAceptada);
                } 
                catch (Exception ex) 
                {
                    System.out.println("Conexion Respuesta Terminada");
                    break;
                }
            }while(!conexionAceptada);
            
            if(conexionAceptada)
            {
                JOptionPane.getRootFrame().dispose();
                datagramPregunta.disconnect();
                datagramRespuesta.disconnect();
                Jugador jugador = new Jugador(false);
                jugador.start();
            }
        }
    }
    
    static class Jugador extends Thread{
        
        boolean oponente;
        
        Jugador(boolean oponente){
            datagramJuego = new DatagramUtils(PUERTO_JUEGO, IP_OPONENTE);
            this.oponente = oponente;
        }

        @Override
        public void run() {
            new View(oponente, datagramJuego);
        }
    }
}

class DatagramUtils {

    private final int puerto;
    private String direccion;
    private String ip;
    private DatagramSocket datagramReceiving;
    private DatagramSocket datagramSending;

    public DatagramUtils(int puerto, String direccion) {
        this.ip = "";
        this.puerto = puerto;
        this.direccion = direccion;
    }

    public void sendObject(Object object) throws Exception {
        try (   
            DatagramSocket ds = new DatagramSocket();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
        ) {
            datagramSending = ds;
            oos.writeObject(object);
            oos.flush();
            byte[] data = baos.toByteArray();
            InetAddress address = InetAddress.getByName(direccion);
            DatagramPacket dp = new DatagramPacket(data, data.length, address, puerto);
            ds.send(dp);
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }

    public Object receiveObject() throws Exception {
        try (DatagramSocket ds = new DatagramSocket(puerto)) {
            datagramReceiving = ds;
            byte[] buffer = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            ds.receive(dp);
            ip = dp.getAddress().getHostAddress();
            try(
                ByteArrayInputStream bais = new ByteArrayInputStream(dp.getData());
                ObjectInputStream ois = new ObjectInputStream(bais);   
            ){
                return ois.readObject();
            }
        } 
        catch (Exception e)     
        {
            throw e;
        }
    }

    public String getIp() {
        return ip;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public void disconnect(){
        if( datagramReceiving != null )
        {
            datagramReceiving.close();
        }
        if( datagramSending != null )
        {
            datagramSending.close();
        }
    }
}