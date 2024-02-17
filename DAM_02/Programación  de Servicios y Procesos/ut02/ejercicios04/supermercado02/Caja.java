package ut02.ejercicios04.supermercado02;

import java.util.LinkedList;

class Caja {

    private static int contadorCajas = 0;
    private static final LinkedList<Cliente> colaGlobal = new LinkedList<>();

    private final int id;
    private Cliente actual;

    public Caja() {
        this.id = ++contadorCajas;
        actual = null;
    }
    
    @Override
    public synchronized String toString(){
        return "Caja " + id;
    }
    
    public static synchronized Caja entrarEnCola(Cliente cliente) throws InterruptedException {
        colaGlobal.add(cliente);
        Caja caja = asignar(cliente);
        if (caja == null || caja.actual != cliente) {
            System.out.println("Cola " + colaGlobal);
            System.out.println(cliente.toString() + " ESPERA en la cola");
        }
        while (caja == null) {
            Caja.class.wait();
            caja = asignar(cliente);
        }
        
        System.out.println(caja.actual.toString() + " ENTRA en " + caja.toString());
        return caja;
    }
    
    private static Caja asignar(Cliente cliente){
        for(Caja caja:cliente.getCajas()){
            if(caja.actual == null){
                caja.actual = colaGlobal.poll();
                return caja;
            }
        }
        return null;
    }
    
    public synchronized void salirDeCaja(Cliente cliente, int pago){
        Principal.addResultado( actual.toString() + " PAGO " + pago + " euros en caja " + id );
        actual = null;
        synchronized(Caja.class){
            Caja.class.notifyAll();
        }
    }
}