
package productor_consumidor_3;

import java.util.ArrayList;
import java.util.List;

public class Principal {
    
    public static void main(String[] args){
        
        List<Integer> enteros = List.of(1, 2, 3, 4, 5);
        
        Cola cola = new Cola( new ArrayList<>(enteros));
        
        System.out.println("Cola " + cola.getCola());
        
        Productor p = new Productor(1,cola);
        Consumidor c = new Consumidor(1,cola);
        
        Thread uno = new Thread(p);
        Thread dos = new Thread(c);

        uno.start();
        dos.start();
        
        try {
            Thread.sleep(50);
            p.finalizar();
            c.finalizar();
        } catch (InterruptedException ex) {
            
        }finally{
            System.out.println("Cola " + cola.getCola());
        }
        
    }
    
}
