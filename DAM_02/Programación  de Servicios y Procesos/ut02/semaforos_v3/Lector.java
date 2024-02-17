
package ut02.semaforos_v3;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Lector extends Thread {

    private final Semaphore s;
    private final int f;
    private final int TIEMPO_ESCRITURA = 50;

    public Lector(String nombre, ArrayList<Semaphore> s) {
        super(nombre);
        this.f = (int)(Math.random()*s.size());
        this.s = s.get(f);
        //eleccion aleatoria del fichero
    }

    @Override
    public void run() {
        try {
            System.out.println(getName() + " intentando leer el fichero " + (f+1));
            //mensaje de inicio del hilo
            
            
            s.acquire();
            //solicita un permiso para leer el fichero
            
            System.out.println(getName() + " leyendo en el fichero " + (f+1));
            //mensaje de obtencion de permiso
            
            sleep(TIEMPO_ESCRITURA);
            //duerme al hilo
            
            System.out.println(getName() + " ya ha leido el fichero " + (f+1));
            //mensaje de fin de hilo
            
            s.release();
            //libera el permiso
        
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
