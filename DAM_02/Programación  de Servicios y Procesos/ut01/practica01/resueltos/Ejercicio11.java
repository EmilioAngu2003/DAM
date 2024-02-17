package ut01.practica01.resueltos;


import java.io.File;
import java.io.IOException;

public class Ejercicio11 {
     public static void main(String[] args) {
        ProcessBuilder pb  = new ProcessBuilder("CMD");
        
        File fOut = new File("salida.txt");
        File fErr = new File("error.txt");
        File fBat = new File("fichero.txt");
        
        pb.redirectError(fErr);
        pb.redirectOutput(fOut);
        pb.redirectInput(fBat);
         try {
             pb.start();
         } catch (IOException ex) {}
        
    }
}
