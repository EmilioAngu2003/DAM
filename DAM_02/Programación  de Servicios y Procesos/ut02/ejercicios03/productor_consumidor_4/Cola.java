
package productor_consumidor_4;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Cola {
    
    private final Queue<Integer> cola;
    
    public Cola(List<Integer> enteros){
        cola = new LinkedList<>(enteros);
    }
    
    public synchronized Queue<Integer> getCola(){
        return cola;
    }
    
    public synchronized void add(int id, Integer n){
        System.out.println( "Productor " + id + " anade " + n + " a la cola" );
        cola.add(n);
    }
    
    public synchronized Integer get(int id){
        Integer n = cola.poll();
        System.out.println( "Consumidor " + id + " obtiene " + n + " de la cola" );
        return n;
    }
}
