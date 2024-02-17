
package productor_consumidor_5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Cola<T, U, V> {
    
    private final Queue<T> cola;
    private final int limite;
    
    public Cola(ArrayList<T> elementos, int limite){
        this.cola = new LinkedList<>(elementos);
        this.limite = limite;
    }
    
    public synchronized Queue<T> getCola(){
        return cola;
    }
    
    public synchronized void add(Object o, T elemento){
        if(cola.size() < limite){
            System.out.println(o.toString() + " anade " + elemento.toString() + " a la cola");
            cola.add(elemento);
        } else {
            System.out.println("Cola llena, " + o.toString() + " no anade nada");
        }
    }
    
    public synchronized T get(Object o){
        if(!cola.isEmpty()){
            T elemento = cola.poll();
            System.out.println(o.toString() + " obtiene " + elemento.toString() + " de la cola");
            return elemento;
        } else {
            System.out.println("Cola vacia, " + o.toString() + " no obtiene nada");
            return null;
        }
    }
}
