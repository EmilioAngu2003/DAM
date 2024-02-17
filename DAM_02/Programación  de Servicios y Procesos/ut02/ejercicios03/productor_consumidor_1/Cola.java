
package productor_consumidor_1;

public class Cola {
    
    private int numero;
    
    public Cola(){
        this.numero = 0;
    }
    
    public synchronized void add(int id, int numero){
        System.out.println( "Productor " + id + " anade " + numero + " a la cola" );
        this.numero = numero;
    }
    
    public synchronized int get(int id){
        System.out.println( "Consumidor " + id + " obtiene " + numero + " de la cola" );
        return numero;
    }
}
