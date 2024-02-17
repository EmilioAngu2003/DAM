
package examen;

import java.util.LinkedList;

public class CajaManual{
    
    private static int nextId = 1;
    
    private final LinkedList<Cliente> cola;
    private final int id;
    private boolean notificando = false;
    private Cliente actual;
    
    public CajaManual(){
        this.id = nextId++;
        this.cola = new LinkedList<>();
        actual = null;
    }
    
    @Override
    public synchronized String toString(){
        return "Caja Manual " + id + " " + cola;
    }
    
    public synchronized void entrarEnCola(Cliente cliente) throws InterruptedException {
        if(actual == null) {
            actual = cliente;
        }else{
            cola.add(cliente);
            System.out.println(toString());
            System.out.println(cliente.toString() + " ESPERA en Caja Manual " + id);
        }
        long esperaColaInicia = System.currentTimeMillis();
        while (actual != cliente) {
            wait(cliente.getTiempoMaxEspera());
            if(notificando) {
                notificando = false;
            }else{
                cola.remove(cliente);
                System.out.println(cliente.toString() + " decidio IRSE");
                System.out.println(toString());
                long tiempoEsperaInterumpt = System.currentTimeMillis();
                int tiempoMedioDescontento = (int)(tiempoEsperaInterumpt-esperaColaInicia);
                cliente.setTiempoEspera(tiempoMedioDescontento);
                Principal.addTiempoDescontentos(cliente.getTiempoCliente());
                cliente.interrupt();
            }
        }
        long esperaColaTermina =  System.currentTimeMillis();
        cliente.setTiempoEspera((int)(esperaColaTermina-esperaColaInicia));
        System.out.println(cliente.toString() + " ENTRA en Caja Manual " + id);
    }
    
    public synchronized void salirDeCaja(Cliente cliente, int pago){
        System.out.println(actual.toString() + " PAGO " + pago + " euros en Caja Manual " + id);
        Principal.addImporte(pago);
        Principal.addTiempoCliente(cliente.getTiempoCliente());
        actual = cola.poll();
        notificando = true;
        notify();
    }
    
}
