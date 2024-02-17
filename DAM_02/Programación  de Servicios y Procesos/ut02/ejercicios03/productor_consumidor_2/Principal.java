
package productor_consumidor_2;

public class Principal {
    
    public static void main(String[] args){
        
        Cola cola = new Cola();
        
        Productor p = new Productor(1, cola);
        Consumidor c = new Consumidor(1, cola);
        
        Thread uno = new Thread(p);
        Thread dos = new Thread(c);

        uno.start();
        dos.start();
        
        try {
            Thread.sleep(50);
            p.finalizar();
            c.finalizar();
        } catch (InterruptedException ex) {
            
        }
        
    }
    
}
