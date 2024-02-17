
package ut01.practica01.mios;

import java.io.*;

public class Ej06 {
    
    public static void main(String[] args) throws IOException {
    
        System.out.print("Introduce comando: " );
        
        String comando = new java.util.Scanner(System.in).nextLine();

        Runtime r = Runtime.getRuntime();
        
        Process p = r.exec("CMD /C " + comando);

        InputStream is;
        BufferedReader br;
        
        is = p.getInputStream();
        br = new BufferedReader(new InputStreamReader(is));
        
        String linea;
        
        while( (linea = br.readLine()) != null) System.out.println(linea);

        is = p.getErrorStream();
        br = new BufferedReader(new InputStreamReader(is));
        
        while( (linea = br.readLine()) != null) System.out.println(linea);
        
        br.close();

    }
}
