
package ut02.semaforos_v2;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Escritor extends Thread {

    private final ArrayList<Semaphore> semaforos;
    private final int numFichero;

    public Escritor(String nombre, ArrayList<Semaphore> s) {
        super(nombre);
        this.semaforos = s;
        this.numFichero = (int)(Math.random() *semaforos.size());
            //eleccion aleatoria del fichero
    }

    @Override
    public void run() {
        try {
            
            System.out.println(getName() + " intentando escribir en el fichero " + (numFichero+1));
                //mensaje de incio del hilo
            
            semaforos.get(numFichero).acquire(5);
                //solicita 5 permiso para escribir en el fichero
            
            System.out.println(getName() + " escribiendo en el fichero " + (numFichero+1));
                //mensaje de obtencion de permisos
            
            sleep((int)(Math.random()*50));
                //duerme al hilo
                
            semaforos.get(numFichero).release(5);
                //libera los permisos
        
            System.out.println(getName() + " ya ha escrito en el fichero " + (numFichero+1));
                //mensaje de fin de hilo
                
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
