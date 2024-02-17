
package ut01.practica01.mios;

import java.io.*;

public class Ej04 {
    
    public static void main(String[] args) {
    	
    	Runtime r = Runtime.getRuntime();
        
        try{
        
            Process p = r.exec("CMD /C DIR");

            InputStream is = p.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String linea;

            while( (linea = br.readLine()) != null) System.out.println(linea);

            br.close();
            
        } catch(IOException e){ }    
        
     }
}
