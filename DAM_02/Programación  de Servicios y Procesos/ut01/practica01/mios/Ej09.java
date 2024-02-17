
package ut01.practica01.mios;

import java.io.*;
import java.util.*;

public class Ej09 {
    
    public static void main(String[] args) throws IOException, InterruptedException {

    	ProcessBuilder pb = new ProcessBuilder();
        
        Map m = pb.environment();
        
        System.out.println(m);

        
        pb.command("java", "UnSaludo", "Hola Mundo");
        
        List<String> comandos = pb.command();
        
        for(String c: comandos) System.out.println(c);
        
        
        pb.command("CMD", "/C", "DIR");
        
        Process p = pb.start();
        
        InputStream is = p.getInputStream();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        String linea;
        
        while( (linea = br.readLine()) != null) System.out.println(linea);
        
        br.close();
        
    }
}
