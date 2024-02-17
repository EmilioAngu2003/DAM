
package productor_consumidor_2;

public class Cola {
    
    private Integer numero;
    
    public Cola(){
        numero = null;
    }
    
    public synchronized void add(int id, Integer numero){
        System.out.println( "Productor " + id + " anade " + numero + " a la cola" );
        this.numero = numero;
    }
    
    public synchronized Integer get(int id){
        System.out.println( "Consumidor " + id + " obtiene " + numero + " de la cola" );
        Integer n = numero;
        numero = null;
        return n;
    }
}
