
package ut01.practica01.mios;

import java.io.*;

public class Ej08 {
    
    public static void main(String[] args) throws IOException, InterruptedException {

    	Runtime r = Runtime.getRuntime();
        
        Process p = r.exec("CMD /C DATE");
        
        OutputStream os = p.getOutputStream();
        
        os.write("01-01-16".getBytes());
        os.flush();

        InputStream is = p.getInputStream();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        String linea;
        
        while( (linea = br.readLine()) != null) System.out.println(linea);
                
        br.close();
    }
}
