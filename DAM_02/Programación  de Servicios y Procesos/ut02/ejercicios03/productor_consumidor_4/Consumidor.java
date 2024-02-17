
package productor_consumidor_4;

public class Consumidor implements Runnable {
    
    private final int id;
    private final Cola cola;
    private boolean seguir;
    
    public Consumidor(int id, Cola cola){
        this.id = id;
        this.cola = cola;
        this.seguir = true;
    }

    @Override
    public void run() {
        while(seguir){
            try {
                cola.get(id);

                Thread.sleep(5);
            
            } catch (InterruptedException ex) {}
        }
    }
    
    public void finalizar(){
        seguir = false;
    }
}
