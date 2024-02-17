
package ut02.semaforos_v3;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Escritor extends Thread {

    private final Semaphore s;
    private final int f;
    private final int p;
    private final int TIEMPO_ESCRITURA = 50;

    public Escritor(String nombre, ArrayList<Semaphore> s, ArrayList<Integer> p) {
        super(nombre);
        this.f = (int)(Math.random()*s.size());
        this.s = s.get(f);
        //eleccion aleatoria del fichero
        
        this.p = p.get(f);
        //obtencion de los permisos
    }

    @Override
    public void run() {
        try {
            System.out.println(getName() + " intentando escribir en el fichero " + (f + 1));
            //mensaje de inicio del hilo
            
            s.acquire(p);
            //solicita todos los permisos para escribir en el fichero

            System.out.println(getName() + " escribiendo en el fichero " + (f + 1));
            //mensaje de obtención de permisos

            sleep(TIEMPO_ESCRITURA);
            //duerme al hilo
            
            System.out.println(getName() + " ya ha escrito en el fichero " + (f + 1));
            //mensaje de fin de hilo

            s.release(p);
            //libera los permisos
            
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
