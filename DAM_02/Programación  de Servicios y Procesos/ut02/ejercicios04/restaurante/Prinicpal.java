
package ut02.ejercicios04.restaurante;

import java.util.ArrayList;
import java.util.Random;

public class Prinicpal {
    
    private static final Random random = new Random();
    private static final int NUM_SALAS = 3;
    private static final int NUM_GRUPOS = 50;
    private static final int MAX_MESAS = 5;
    private static final int MIN_MESAS = 3; 
    private static final int MAX_PERSONAS = 6;
    private static final int MIN_PERSONAS = 2;
    private static final int TIEMPO_LLEGADA = 200;
    
    public static void main(String[] args) throws InterruptedException {

        ArrayList<Sala> salas = new ArrayList<>();
        for (int i=0; i<NUM_SALAS; i++){
            Sala sala = new Sala(MAX_MESAS,MIN_MESAS,MAX_PERSONAS,MIN_PERSONAS); 
            System.out.println(sala.toString() + " | Mesas " + sala.getMesasDisponibles());
            salas.add(sala);
        }
        
        System.out.println("---------------------------------------------------");
        
        ArrayList<Grupo> grupos = new ArrayList<>();
        for (int i=0; i<NUM_GRUPOS; i++) grupos.add(new Grupo(MAX_PERSONAS, MIN_PERSONAS, salas));
        
        for(Grupo g:grupos){
            Thread.sleep(random.nextInt(TIEMPO_LLEGADA));
            g.start();
        }
        
        for(Grupo g:grupos) g.join();
        
        System.out.println("---------------------------------------------------");
        
        for(Sala s:salas) System.out.println(s.toString() + " | Mesas " + s.getMesasDisponibles());
    }
    
}
