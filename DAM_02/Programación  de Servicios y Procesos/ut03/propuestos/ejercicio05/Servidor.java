
package ut03.propuestos.ejercicio05;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.filechooser.FileSystemView;

public class Servidor {
    
    private static final int PORT = 12345;

    public static void main(String[] args)  {
        
        System.out.println("Servidor iniciado, esperando conexiones...");
        
        try (
            ServerSocket servidorSocket = new ServerSocket(PORT);
            Socket clienteSocket = servidorSocket.accept();
            ObjectOutputStream oos = new ObjectOutputStream(clienteSocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(clienteSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clienteSocket.getOutputStream());
        ) {

            System.out.println("Cliente conectado desde " + clienteSocket.getInetAddress());

            FileSystemView fsv = FileSystemView.getFileSystemView();
            File directorio = fsv.getDefaultDirectory();
            
            ArrayList<String> listaArchivos = getListaArchivos(directorio);

            while (true) {
                
                oos.writeObject(listaArchivos);
                
                String respuesta = (String) ois.readObject(); 
                if (!respuesta.equalsIgnoreCase("s")){
                    break;
                }
                
                String fichero = (String) ois.readObject();      
                if (listaArchivos.contains(fichero)) {
                    enviarArchivo(dos, new File(directorio, fichero));
                } 
            }
                
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception " + e);
        }
    }

    private static ArrayList<String> getListaArchivos(File directorio) {
        ArrayList<String> listaArchivos = new ArrayList<>();
        if (directorio.exists() && directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();
            if (archivos != null) {
                for (File fichero : archivos) if (fichero.isFile() && fichero.getName().endsWith(".txt")) listaArchivos.add(fichero.getName());
            }
        }
        return listaArchivos;
    }

    private static void enviarArchivo(DataOutputStream dos, File fichero) {
        try (
            FileInputStream fis = new FileInputStream(fichero);
        ) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, bytesRead);
                System.out.println("Enviados: " + bytesRead + " bytes");
            }
            System.out.println("Archivo '" + fichero.getName() + "' enviado con exito.");
        } catch (FileNotFoundException  e) {
            System.out.println("Exception " + e);
        } catch (IOException e) {
            System.out.println("Exception " + e);
        }
    }
}
