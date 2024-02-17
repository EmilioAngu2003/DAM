
package productor_consumidor_3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Cola {
    
    private final Deque<Integer> cola;
    
    public Cola(List<Integer> enteros){
        cola = new ArrayDeque<>(enteros);
    }
    
    public synchronized Deque<Integer> getCola(){
        return cola;
    }
    
    public synchronized void add(int id, Integer n){
        System.out.println( "Productor " + id + " anade " + n + " a la cola" );
        cola.push(n);
    }
    
    public synchronized Integer get(int id){
        Integer n = cola.pop();
        System.out.println( "Consumidor " + id + " obtiene " + n + " de la cola" );
        return n;
    }
}
