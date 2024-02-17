
package ut03.propuestos.ejercicio05;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
    
    private static final int PORT = 12345;
    private static final String HOST = "localhost";
    private static final String DESCARGAS = System.getProperty("user.home") + File.separator + "Downloads";

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(HOST, PORT);
            ObjectInputStream ins = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            Scanner sc = new Scanner(System.in)
        ){
            System.out.println("Conectado al servidor");
            ArrayList<String> listaArchivos = (ArrayList<String>) ins.readObject();

            while (!socket.isClosed()) {
                
                System.out.println("Archivos disponibles:");
                for (String ficheroNombre : listaArchivos) System.out.println(" - " + ficheroNombre);
                
                System.out.println("Desea descargar un archivo? (s/n)");
                oos.writeObject(sc.nextLine());
                
                System.out.print("Escriba un archivo: ");
                String nombreFichero = sc.nextLine();
                oos.writeObject(nombreFichero);
                
                recibirArchivo(dis, new File(DESCARGAS + File.separator + nombreFichero));
            }

            System.out.println("Desconectado del servidor.");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception " + e);
        }
    }
    
    private static void recibirArchivo(DataInputStream dis, File archivo) {

        try (
            FileOutputStream fos = new FileOutputStream(archivo); 
        ){
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = dis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
                System.out.println("Enviando...");
            }
            System.out.println("Archivo recibido con exito.");
        }catch(IOException e){
            System.out.println("Exception " + e);
        }
    }
}

