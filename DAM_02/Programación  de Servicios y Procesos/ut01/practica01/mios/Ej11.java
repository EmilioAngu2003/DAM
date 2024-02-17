
package ut01.practica01.mios;

import java.io.*;

public class Ej11 {
    
    public static void main(String[] args) throws IOException {

    	ProcessBuilder pb  = new ProcessBuilder("CMD");
        
        File fOutput = new File("salida.txt");
        pb.redirectOutput(fOutput);
        
        File fError = new File("error.txt");
        pb.redirectError(fError);
        
        File fBat = new File("fichero.txt");
        pb.redirectInput(fBat);
        
        pb.start();

    }
}
