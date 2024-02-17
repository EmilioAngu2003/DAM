package ut01.practica01.mios;

import java.io.IOException;

public class Ej02 {
    
    public static void main(String[] args) {
        
        try{
            
            Runtime.getRuntime().exec("notepad");
        
        } catch (IOException e){}
        
    }
}
