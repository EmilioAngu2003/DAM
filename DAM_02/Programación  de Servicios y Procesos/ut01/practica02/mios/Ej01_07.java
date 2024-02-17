
package ut01.practica02.mios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ej01_07 {
    
    public static void main(String[] args) {
        
        try{
            
            String[] comand = {"CMD","/C","ipconfig", "|", "find", "\"IPv4\""};
            
            Process p = new ProcessBuilder(comand).start();
            
            System.out.println(leer(p));
        
        } catch (IOException e){}
        
    }
    
    private static String leer(Process p){
    
        try{
            
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

            StringBuilder sb = new StringBuilder();

            String linea;

            while( ( linea = br.readLine() ) != null) {
                sb.append(linea);
                sb.append(System.getProperty("line.separator"));
            }

            return sb.toString();
            
        } catch (IOException e){ 
            
            System.out.println(e);
            return null;
            
        }
    }
    
}
