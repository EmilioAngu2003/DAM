package ut01.practica01.resueltos;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ejercicio06 {
    public static void main(String[] args) {
    	
    	String linea;
        
        System.out.print("Introduce comando " );
        Scanner sc = new Scanner(System.in); 
        String comando = sc.nextLine();

        Runtime r = Runtime.getRuntime();
        Process p; 

        try {
            p = r.exec("CMD /C " + comando);
            
            InputStream is = p.getInputStream();
            BufferedReader bris = new BufferedReader(new InputStreamReader(is));
            while((linea=bris.readLine())!=null) System.out.println(linea);
            bris.close();
            
            InputStream es = p.getErrorStream();
            BufferedReader bres = new BufferedReader(new InputStreamReader(es));
            while((linea=bres.readLine())!=null) System.out.println(linea);
            bres.close();
            
        }catch (Exception e){e.printStackTrace();}

        }
    }
            
