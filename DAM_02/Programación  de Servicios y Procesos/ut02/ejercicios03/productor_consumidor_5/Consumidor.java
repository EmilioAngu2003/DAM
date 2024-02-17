
package productor_consumidor_5;

import java.util.Random;

public class Consumidor implements Runnable {
    
    private static final Random random = new Random();
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
        System.out.println( toString() + " inicia");
        while(seguir){
            try {
                Thread.sleep(random.nextInt(20));
                cola.get(this);
            } catch (InterruptedException ex) {}
        }
        System.out.println( toString() + " termina");
    }
    
    @Override
    public String toString() {
        return "Consumidor " + id;
    }
    
    public void finalizar(){
        seguir = false;
    }
}
