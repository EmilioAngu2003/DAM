
package ut02.ejercicios04.supermercado01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Principal {
    
    private static final Random random = new Random();
    private static final List<String> resultados = new ArrayList<>();
    private static final int NUM_CAJAS = 3;
    private static final int NUM_CLIENTES = 100;
    private static long tiempo_total = 0;

    public static void main(String[] args) throws InterruptedException {

        ArrayList<Caja> cajas = new ArrayList<>();
        for (int i=0; i<NUM_CAJAS; i++) cajas.add(new Caja());
        
        ArrayList<Cliente> clientes = new ArrayList<>();
        for (int i=0; i<NUM_CLIENTES; i++) clientes.add(new Cliente(cajas.get(random.nextInt(NUM_CAJAS))));
        
        for(Cliente c:clientes)c.start();
        
        for(Cliente c:clientes) c.join();
        verResultados();
        verTiempoMedio();
        
    }
    
    public static void addTIempo(long tiempo){
        tiempo_total += tiempo;
    }
    
    public static void addResultado(String resultado){
        resultados.add(resultado);
        System.out.println(resultado);
    }
    
    private static void verResultados(){
        System.out.println("----------");
        System.out.println("RESULTADOS");
        System.out.println("----------");
        resultados.forEach(s -> System.out.println(s));
    }
    
    private static void verTiempoMedio(){
        System.out.println("------------");
        System.out.println("TIEMPO MEDIO");
        System.out.println("------------");
        System.out.println((tiempo_total/NUM_CLIENTES));
    }
}
