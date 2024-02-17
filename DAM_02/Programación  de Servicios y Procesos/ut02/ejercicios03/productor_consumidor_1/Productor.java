
package productor_consumidor_1;

import java.util.Random;

public class Productor implements Runnable{
    
    private final int id;
    private final Cola cola;
    private final Random random;
    private boolean seguir;
    
    public Productor(int id, Cola cola){
        this.id = id;
        this.cola = cola;
        this.random = new Random();
        this.seguir = true;
    }
    
    @Override
    public void run() {
        while(seguir){
            try {
                int num = random.nextInt(10)+1;
                
                cola.add(id,num);

                Thread.sleep(5);
            
            } catch (InterruptedException ex) {}
        }
    }
    
    public void finalizar(){
        seguir = false;
    }
}
