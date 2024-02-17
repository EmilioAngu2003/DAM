
package ut01.practica02.mios;

import java.io.IOException;

public class Ej01_02 {
    
    public static void main(String[] args) {
        
        try{
            
            Process p = new ProcessBuilder("notepad").start();
            
            Thread.sleep(3000);
            
            p.destroy();
            
            p.onExit().defaultExecutor().execute( () -> System.out.println("Calculadora cerrada") );
        
        } catch (IOException | InterruptedException e){}
        
    }
}
