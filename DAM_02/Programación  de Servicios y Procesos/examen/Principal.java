
package examen;

import java.util.ArrayList;

public class Principal {

    private static final int N_CAJAS_MANUALES = 20;
    private static final int N_CAJAS_AUTOMATICAS = 6;
    private static final int N_CLIENTES = 100; //Numero de hilos que se lanzan
    private static final ArrayList<Integer> importes = new ArrayList<>();
    private static final ArrayList<Integer> tiemposTotal = new ArrayList<>();
    private static final ArrayList<Integer> tiemposDescontentos = new ArrayList<>();
    
    public static void main(String[] args) throws InterruptedException {
        
        ArrayList<CajaManual> cajasManuales = new ArrayList<>();
        for (int i=0; i<N_CAJAS_MANUALES; i++) cajasManuales.add(new CajaManual());
        
        ArrayList<CajaAutomatica> cajasAutomaticas = new ArrayList<>();
        for (int i=0; i<N_CAJAS_AUTOMATICAS; i++) cajasAutomaticas.add(new CajaAutomatica());
        
        ArrayList<Cliente> clientes = new ArrayList<>();
        for (int i=0; i<N_CLIENTES; i++) clientes.add(new Cliente(cajasAutomaticas, cajasManuales));
        
        for(Cliente c:clientes)c.start();
        
        for(Cliente c:clientes) c.join();
        
        System.out.println("-----------------------------------------------------------");
        System.out.println("RESULTADOS");
        System.out.println("-----------------------------------------------------------");
        
        verResultados();
        
    }
    
    private static void verResultados() {
        
        System.out.println("Hubieron " + importes.size() + " que pagaron");
        System.out.println("Media de compras " + mediaDe(importes));
        System.out.println("Media de clientes contentos " + mediaDe(tiemposTotal));
        System.out.println("Hubieron " + tiemposDescontentos.size() + " clientes descontentos");
        System.out.println("Media de clientes descontentos " + mediaDe(tiemposDescontentos));
        
    }
    
    private static int mediaDe(ArrayList<Integer> lista) {
        int suma = 0;
        for(Integer i:lista) suma+= i;
        return (suma/lista.size());
    }
    
    public synchronized static void addTiempoCliente(int tiempoCliente){
        tiemposTotal.add(tiempoCliente);
    }
    
    public synchronized static void addImporte(int importe){
        importes.add(importe);
    }
    
    public synchronized static void addTiempoDescontentos(int tiempoDescontento){
        tiemposDescontentos.add(tiempoDescontento);
    }
}
