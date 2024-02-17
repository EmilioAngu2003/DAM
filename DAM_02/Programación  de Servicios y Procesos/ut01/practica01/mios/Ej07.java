
package ut01.practica01.mios;

import java.io.*;

public class Ej07 {
    
    public static void main(String[] args) throws IOException, InterruptedException {
	    	
        Runtime r = Runtime.getRuntime();

        Process p = r.exec("CMD /C DIR"); 
        
        InputStream is = p.getInputStream();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        FileOutputStream fos = new FileOutputStream("Salida.txt");
        
        PrintWriter pw = new PrintWriter(fos);

        String linea;
        
        while( (linea = br.readLine()) != null) pw.println(linea);
        
        Runtime.getRuntime().exec("CMD /C Salida.txt");
        
        br.close();   
        pw.close();

    }
}
