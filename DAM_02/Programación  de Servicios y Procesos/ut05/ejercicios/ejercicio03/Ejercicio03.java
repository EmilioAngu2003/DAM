package ut05.ejercicios;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Ejercicio03 {
    
    static final String NOMBRE_ARCHIVO = "pg72978-h.zip";
    static final String DIR_ARCHIVO = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + NOMBRE_ARCHIVO;
    static final String URL_ARCHIVO = "https://www.gutenberg.org/cache/epub/72978/pg72978-h.zip";

    public static void main(String[] args) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // Calcular hash del archivo local
            String hashLocal = calcularHashMD5(md, new FileInputStream(DIR_ARCHIVO));
            System.out.println("Hash MD5 del archivo local: " + hashLocal);

            // Calcular hash del archivo descargado desde URL
            String hashUrl = calcularHashMD5(md, new URL(URL_ARCHIVO).openStream());
            System.out.println("Hash MD5 del archivo url: " + hashUrl);

            // Comparar los hashes
            if (hashLocal.equals(hashUrl)) {
                System.out.println("El archivo conserva su integridad.");
            } else {
                System.out.println("El archivo NO conserva su integridad.");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error al calcular el hash MD5: " + e.getMessage());
        }
    }

    private static String calcularHashMD5(MessageDigest md, InputStream inputStream) throws IOException {
        try (
            BufferedInputStream bis = new BufferedInputStream(inputStream)
        ){
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) 
            {
                md.update(buffer, 0, bytesRead);
            }

            byte[] hashBytes = md.digest();

            StringBuilder hexadecimal = new StringBuilder();
            for (byte b : hashBytes) 
            {
                hexadecimal.append(String.format("%02x", b));
            }
            return hexadecimal.toString();
        } 
        catch (IOException e) 
        {
            throw new IOException("Error al leer el archivo: " + e.getMessage());
        }
    }
}
