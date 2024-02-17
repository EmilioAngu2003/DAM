package ut01.practica01.resueltos;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejercicio04 {
    public static void main(String[] args) {
    	
    	String linea;

        Runtime r = Runtime.getRuntime();
        Process p; 

        try {
            p = r.exec("CMD /C DIR");
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while((linea=br.readLine())!=null) System.out.println(linea);
            br.close();
        }catch (Exception e){e.printStackTrace();}

        }
    }
            
