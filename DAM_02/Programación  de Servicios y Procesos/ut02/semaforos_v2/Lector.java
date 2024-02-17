
package ut02.semaforos_v2;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Lector extends Thread {

    private final ArrayList<Semaphore> semaforos;
    private final int numFichero;

    public Lector(String nombre, ArrayList<Semaphore> s) {
        super(nombre);
        this.semaforos = s;
        this.numFichero = (int)(Math.random() *semaforos.size());
            //eleccion aleatoria del fichero
    }

    @Override
    public void run() {
        try {
            
            System.out.println(getName() + " intentando leer el fichero " + (numFichero+1));
                //mensaje de incio del hilo

            semaforos.get(numFichero).acquire();
                //solicita un permiso para leer el fichero
            
            System.out.println(getName() + " leyendo en el fichero " + (numFichero+1));
                //mensaje de obtencion del permiso
                
            sleep((int)(Math.random()*50));
                //duerme al hilo
            
            semaforos.get(numFichero).release();
                //libera el permiso
        
            System.out.println(getName() + " ya ha leido el fichero " + (numFichero+1));
                //mensaje de fin de hilo
            
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
