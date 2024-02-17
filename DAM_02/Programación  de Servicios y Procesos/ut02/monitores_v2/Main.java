
package ut02.monitores_v2;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    private static final String[] DIRECCIONES = {"<-","->"};
    private static final int COCHES_POR_TURNO = 3;
    private static final int MILLIS = 1000;
    private static final int MAX_COCHES = 20;
    private static final int MIN_COCHES = 10;
    
    public static void main(String[] args) {

        Puente puente = new Puente(COCHES_POR_TURNO, MILLIS);
        Random random = new Random();
        ArrayList<Coche> coches = new ArrayList<>();
        
        // numero de coches aleatorio
        int numeroCoches = random.nextInt(MAX_COCHES - MIN_COCHES) + MIN_COCHES;
        System.out.println("Total de Coches: " + numeroCoches);
        
        // direcciones aleatorias
        for (int i = 0; i < numeroCoches; i++){
            int indice = random.nextInt(DIRECCIONES.length);
            coches.add(new Coche(i, DIRECCIONES[indice], puente));
        }

        // lanzar hilos
        for (Coche coche : coches){
            try {
                Thread.sleep(random.nextInt(MILLIS));
                new Thread(coche).start();
            } catch (InterruptedException ex) {}
        }
    }
}