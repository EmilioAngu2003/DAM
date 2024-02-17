
package ut02.ejercicios04.elecciones;

import java.util.LinkedList;
import java.util.Random;

public class MesaElectoral {
    
    private static final Random random = new Random();
    private static final String[] partidos = {"PP","Podemos","Ciudadanos","PSOE","PRM"};
    private static final int[] votos = new int[partidos.length];
    private static Integer votosEmitidos = 0;
    private static Integer votosEnBlanco = 0;
    private static Integer votosNulos = 0;
    private static Integer faltas = 0;
    
    private final LinkedList<Ciudadano> cola;
    private Ciudadano actual;
    
    public MesaElectoral(){
        cola = new LinkedList<>();
        actual = null;
    }

    public synchronized void entrarEnMesa(Ciudadano c) throws InterruptedException {
        if(actual == null) {
            actual = c;
        }else{
            cola.add(c);
        }
        while (actual != c) wait();
    }
    
    public synchronized static void votar(){
        votosEmitidos++;
        int posibilidades = random.nextInt(partidos.length + 2);
        if(posibilidades < partidos.length) votos[posibilidades]++;
        if(posibilidades == partidos.length) votosEnBlanco++;
        if(posibilidades == partidos.length+1) votosNulos++;
    }
    
    public synchronized static void noVotar(Ciudadano c){
        faltas++;
        c.interrupt();
    }

    public synchronized void salirDeMesa(Ciudadano c) {
        actual = cola.poll();
        notify();
    }
    
    public static synchronized void printResultado(){
        System.out.println("-----------------------------");
        int suma = 0;
        for(int i=0; i<partidos.length; i++){
            System.out.println(partidos[i] + " " + votos[i]);
            suma += votos[i];
        }
        System.out.println("Suma            " + suma);
        System.out.println("-----------------------------");
        System.out.println("Votos en Blanco " + votosEnBlanco); 
        System.out.println("Votos Nulos     " + votosNulos);
        System.out.println("-----------------------------");
        System.out.println("Votos Emitidos  " + votosEmitidos);
        System.out.println("-----------------------------");
        System.out.println("Verificacion    " + (suma+votosEnBlanco+votosNulos));
        System.out.println("Faltas          " + faltas);
    }
    
}
