package ut02.ejercicios04.supermercado01;

import java.util.LinkedList;

class Caja {
    
    private static int contadorCajas = 0;
    
    private final LinkedList<Cliente> cola;
    private final int id;
    private Cliente actual;
    
    public Caja(){
        this.id = ++contadorCajas;
        this.cola = new LinkedList<>();
        actual = null;
    }
    
    @Override
    public synchronized String toString(){
        return "Caja " + id + " " + cola;
    }
    
    public synchronized void entrarEnCola(Cliente cliente) throws InterruptedException {
        if(actual == null) {
            actual = cliente;
        }else{
            cola.add(cliente);
            System.out.println(toString());
            System.out.println(cliente.toString() + " ESPERA en Caja " + id);
        }
        while (actual != cliente) wait();
        System.out.println(cliente.toString() + " ENTRA en Caja " + id);
    }
    
    public synchronized void salirDeCaja(Cliente cliente, int pago){
        Principal.addResultado( actual.toString() + " PAGO " + pago + " euros en Caja " + id );
        actual = cola.poll();
        notify();
    }
}