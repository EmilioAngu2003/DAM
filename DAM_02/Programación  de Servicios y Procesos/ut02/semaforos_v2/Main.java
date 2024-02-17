
package ut02.semaforos_v2;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        
        ArrayList<Semaphore> semaforos = new ArrayList<>();
            //lista de semaforos 
       
        for(int i=0; i<3; i++)  semaforos.add(new Semaphore(5));
            //creacion de semaforos con un máximo de 5 hilos 
       
        for(int i=1; i<=5;i++)  new Escritor("Escritor " + i, semaforos).start();
            //crea e inicia 5 hilos escritores
           
        for(int i=1; i<=20;i++) new Lector("Lector " + i, semaforos).start();
            //crea e inicia 20 hilos lectores
    }
}
