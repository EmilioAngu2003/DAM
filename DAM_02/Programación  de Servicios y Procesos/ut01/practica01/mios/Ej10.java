
package ut01.practica01.mios;

import java.io.*;

public class Ej10 {
    
    public static void main(String[] args) throws IOException {

    	ProcessBuilder pb  = new ProcessBuilder("CMD","/C","DIR");
        
        File ficheroSalida = new File("salida.txt");
        pb.redirectOutput(ficheroSalida);
        
        File ficheroError = new File("error.txt");
        pb.redirectError(ficheroError);
        
        pb.start();
        
    }
}