
package productor_consumidor_5;

import java.util.Random;

public class Productor implements Runnable{
    
    private static final Random random = new Random();
    private final int id;
    private final Cola cola;
    private boolean seguir;
    
    public Productor(int id, Cola cola){
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
                cola.add(this, new Producto());
            } catch (InterruptedException ex) {}
        }
        System.out.println( toString() + " termina");
    }

    @Override
    public String toString() {
        return "Productor " + id;
    }

    public void finalizar(){
        seguir = false;
    }
}
