
package ut02.monitores;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    private static final String[] DIRECCIONES = {"<-", "->"};
    private static final int COCHES_POR_TURNO = 3;
    private static final int MAX_COCHES = 20;
    private static final int MIN_COCHES = 10;
    private static final int MILLIS = 1000;
    
    public static void main(String[] args) {

        Puente puente = new Puente(DIRECCIONES, COCHES_POR_TURNO, MILLIS);
        Random random = new Random();
        ArrayList<Coche> coches = new ArrayList<>();
        
        // numero de coches aleatorio
        int numeroCoches = random.nextInt(MAX_COCHES - MIN_COCHES) + MIN_COCHES;
        
        // direcciones aleatorias
        for (int i = 0; i < numeroCoches; i++) coches.add(new Coche(i, DIRECCIONES[random.nextInt(DIRECCIONES.length)], puente));
        
        // mensaje de inicio
        System.out.println("Total de Coches: " + numeroCoches);
        
        // lanzar hilos
        for (Coche coche : coches){
            try {
                Thread.sleep(random.nextInt(MILLIS));
                
            } catch (InterruptedException ex) {
            }
            new Thread(coche).start();
        }
    }
}