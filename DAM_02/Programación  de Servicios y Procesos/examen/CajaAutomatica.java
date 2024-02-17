
package examen;

import java.util.LinkedList;

public class CajaAutomatica {
 
    private static int contadorCajas = 0;
    private static final LinkedList<Cliente> colaGlobal = new LinkedList<>();
    private static boolean notificando = false;
    
    private final int id;
    private Cliente actual;

    public CajaAutomatica() {
        this.id = ++contadorCajas;
        actual = null;
    }
    
    @Override
    public synchronized String toString(){
        return "Caja Automatica " + id;
    }
    
    public static synchronized CajaAutomatica entrarEnCola(Cliente cliente) throws InterruptedException {
        colaGlobal.add(cliente);
        CajaAutomatica cajaAutomatica = asignar(cliente);
        if (cajaAutomatica == null || cajaAutomatica.actual != cliente) {
            System.out.println("Cola " + colaGlobal);
            System.out.println(cliente.toString() + " ESPERA en la cola de automaticas");
        }
        long esperaColaInicia = System.currentTimeMillis();
        while (cajaAutomatica == null) {
            CajaAutomatica.class.wait(cliente.getTiempoMaxEspera());
            if(notificando) {
                cajaAutomatica = asignar(cliente);
                notificando = false;
            }else{
                colaGlobal.remove(cliente);
                System.out.println(cliente.toString() + " decidio en IRSE");
                System.out.println("Cola de Automaticas " + colaGlobal);
                long tiempoEsperaInterumpt = System.currentTimeMillis();
                int tiempoMedioDescontento = (int)(tiempoEsperaInterumpt-esperaColaInicia);
                cliente.setTiempoEspera(tiempoMedioDescontento);
                Principal.addTiempoDescontentos(cliente.getTiempoCliente());
                cliente.interrupt();
            }
        }
        long esperaColaTermina =  System.currentTimeMillis();
        cliente.setTiempoEspera((int)(esperaColaTermina-esperaColaInicia));
        System.out.println(cajaAutomatica.actual.toString() + " ENTRA en " + cajaAutomatica.toString());
        return cajaAutomatica;
    }
    
    private static CajaAutomatica asignar(Cliente cliente){
        for(CajaAutomatica caja:cliente.getCajasAutomaticas()){
            if(caja.actual == null){
                caja.actual = colaGlobal.poll();
                return caja;
            }
        }
        return null;
    }
    
    public synchronized void salirDeCaja(Cliente cliente, int pago){
        System.out.println(actual.toString() + " PAGO " + pago + " euros en Caja Automatica " + id );
        Principal.addImporte(pago);
        Principal.addTiempoCliente(cliente.getTiempoCliente());
        actual = null;
        synchronized(CajaAutomatica.class){
            notificando = true;
            CajaAutomatica.class.notifyAll();
        }
    }
}
