
package ut02.ejercicios04.restaurante;

import java.util.ArrayList;
import java.util.Random;

public class Sala {
    
    private static final Random random = new Random();
    private static final ArrayList<Grupo> salaDeEspera = new ArrayList<>();
    private static final int MAX_SALAS_FUMAN = 2;
    private static final int MAX_SALAS_NO_FUMAN = 2;
    private static int salasNoFuman = 0;
    private static int salasFuman = 0;
    private static int contadorSalas = 0;
    private static boolean notificando = false; 

    private final ArrayList<Integer> mesasDisponibles;
    private final int id;
    private final int mesasTotal;
    private final int maxPersonas;
    private Boolean fuman;
   
    public Sala(int maxMesas, int minMesas,int maxPersonas, int minPersonas) {
        id = ++contadorSalas;
        mesasDisponibles = new ArrayList<>();
        this.maxPersonas = maxPersonas;
        mesasTotal = random.nextInt(maxMesas-minMesas+1)+minMesas;
        for (int i=0; i<mesasTotal; i++) mesasDisponibles.add(random.nextInt(maxPersonas-minPersonas+1)+minPersonas);
        fuman = null;
    }

    private synchronized void setFuman(Boolean fuman) {
        if(fuman == null){
            if( this.fuman) salasFuman--;
            if(!this.fuman) salasNoFuman--;           
        }else{
            if(fuman && salasFuman < MAX_SALAS_FUMAN) salasFuman++;
            if(!fuman && salasNoFuman < MAX_SALAS_NO_FUMAN) salasNoFuman++;
        }
        
        this.fuman = fuman;
    }
    
    public ArrayList<Integer> getMesasDisponibles() {
        return mesasDisponibles;
    }
    
    @Override
    public String toString() {
        return "Sala " + id;
    }
    
    public static synchronized Sala entrarEnSala(Grupo grupo, int tiempoEspera) throws InterruptedException {
        salaDeEspera.add(grupo);
        Sala sala = asignarSala(grupo);
        if (sala == null) {
            System.out.println("No se puede ofrecer ninguna  mesa al " + grupo.toString());
            System.out.println("--------------------------------------------------------");
            System.out.println("SALA DE ESPERA " + salaDeEspera);
            System.out.println("--------------------------------------------------------");
        }
        while (sala == null) {
            Sala.class.wait(tiempoEspera);
            if(notificando) {
                sala = asignarSala(grupo);
                notificando = false;
            }else{
                salaDeEspera.remove(grupo);
                System.out.println(grupo.toString() + " decidio en " + tiempoEspera + " millis");
                System.out.println("--------------------------------------------------------");
                System.out.println("SALA DE ESPERA " + salaDeEspera);
                System.out.println("--------------------------------------------------------");
                grupo.interrupt();
            }
        }
        return sala;
    }
    
    private static Sala asignarSala(Grupo grupo){
        for(Sala s:grupo.getSalas()){
            if(s.fuman != null && s.fuman == grupo.esFumador() && s.asignarMesa(grupo) ){
                return s;
            }else{
                if(s.asignarMesa(grupo)){
                    s.setFuman(grupo.esFumador());
                    return s; 
                }
            }
        }
        return null;
    }
    
    private synchronized boolean asignarMesa(Grupo grupo){
        Integer mesa = mesaPara(grupo.getPersonas());
        if( mesa != null) {
            System.out.println(toString() + " con mesas disponibles " + mesasDisponibles);
            grupo.setMesa(mesa);
            mesasDisponibles.remove(mesa);
            salaDeEspera.remove(grupo);
            return true;
        }
        return false;
    }
    
    private Integer mesaPara(int personas){
        int mesa = maxPersonas;
        boolean hayMesa = false;
        for(Integer m:mesasDisponibles){
            if(personas <= m && m <= mesa){
                hayMesa = true;
                mesa = m;
            }
        }
        if(hayMesa) return mesa;
        return null;
    }
    
    public synchronized void salirDeSala(Grupo grupo){
        mesasDisponibles.add(grupo.getMesa());
        if(mesasDisponibles.size() == mesasTotal) setFuman(null);
        synchronized(Sala.class){
            notificando = true;
            Sala.class.notifyAll();
        }
    }
}
