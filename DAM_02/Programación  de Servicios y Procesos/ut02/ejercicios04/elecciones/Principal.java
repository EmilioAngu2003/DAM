
package ut02.ejercicios04.elecciones;

import java.util.ArrayList;
import java.util.Random;

public class Principal {
    
    private static final Random random = new Random();
    private static final int CIUDADANOS = 5000;
    private static final int MESAS_ELECTORALES = 3;
    private static final int HORARIO_APERTURA = 15000;
    
    public static void main(String[] args) throws InterruptedException {
        
        ArrayList<MesaElectoral> mesas = new ArrayList<>();
        for (int i=0; i<MESAS_ELECTORALES; i++) mesas.add(new MesaElectoral());
        
        ArrayList<Ciudadano> ciudadanos = new ArrayList<>();
        for (int i=0; i<CIUDADANOS; i++){
            
            boolean vota        = random.nextInt(10) < 8;
            MesaElectoral mesa  = mesas.get(random.nextInt(MESAS_ELECTORALES));
            int horaLlegada     = random.nextInt(HORARIO_APERTURA);
            
            ciudadanos.add(new Ciudadano(vota, mesa, horaLlegada));
        }
        
        for(Ciudadano c:ciudadanos) c.start();
        
        for(Ciudadano c:ciudadanos) c.join();
        
        MesaElectoral.printResultado();
        
    }

}
