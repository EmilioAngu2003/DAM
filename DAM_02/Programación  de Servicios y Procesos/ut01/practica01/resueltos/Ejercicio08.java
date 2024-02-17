package ut01.practica01.resueltos;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Ejercicio08 {
    public static void main(String[] args) {

    	Runtime r = Runtime.getRuntime();
        Process p=null;
 
        try {
            p= r.exec("CMD /C DATE");
            OutputStream os = p.getOutputStream();
            os.write("01-01-16".getBytes());
            os.flush();
         
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String linea;
            while((linea=br.readLine())!=null) {
                System.out.println(linea);
            }          
            br.close();
        }catch(Exception e){
            
        }
    }
}
