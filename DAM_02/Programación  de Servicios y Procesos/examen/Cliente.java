
package examen;

import java.util.List;
import java.util.Random;

public class Cliente extends Thread{
    
    private static final Random random = new Random();
    private static final int TIEMPO_MIN_COMPRA = 5;
    private static final int TIEMPO_MAX_COMPRA = 125;
    private static final int TIEMPO_MIN_PAGO = 1;
    private static final int TIEMPO_MAX_PAGO = 30;
    private static final int TIEMPO_MAX_ESPARA = 35;
    private static final int MIN_PAGO = 1;
    private static final int MAX_PAGO = 200;
    private static int nextId = 1;
    
    private final int tiempoCompra;
    private final int pago;
    private final int id;
    private final int tiempoMaxEspera;
    private final List<CajaAutomatica> cajasAutomaticas;
    private final List<CajaManual> cajasManuales;
    
    private int tiempoEspera;
    private int tiempoPago;
    
    public Cliente(List<CajaAutomatica> cajasAutomaticas, List<CajaManual> cajasManuales){
        this.tiempoCompra = intEntre(TIEMPO_MAX_COMPRA, TIEMPO_MIN_COMPRA);
        this.pago = intEntre(MAX_PAGO, MIN_PAGO);
        this.tiempoMaxEspera = TIEMPO_MAX_ESPARA;
        this.tiempoPago = 0;
        this.tiempoEspera = 0;
        this.id = nextId++;
        this.cajasAutomaticas = cajasAutomaticas;
        this.cajasManuales = cajasManuales;
    }
    
    private int intEntre(int max, int min){
        return random.nextInt(max - min + 1) + min;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(tiempoCompra);
            if(random.nextInt(1)==0){
                CajaManual cajaManual = cajasManuales.get(random.nextInt(cajasManuales.size()));
                cajaManual.entrarEnCola(this);
                tiempoPago = intEntre(TIEMPO_MAX_PAGO, TIEMPO_MIN_PAGO);
                Thread.sleep(tiempoPago);
                cajaManual.salirDeCaja(this, pago);
            }else{
                CajaAutomatica cajaAutomatica = CajaAutomatica.entrarEnCola(this);
                tiempoPago = intEntre(TIEMPO_MAX_PAGO, TIEMPO_MIN_PAGO);
                Thread.sleep(tiempoPago);
                cajaAutomatica.salirDeCaja(this, pago);
            }
        } catch (InterruptedException e) {}
    }
    
    public void setTiempoEspera(int tiempoEspera){
        this.tiempoEspera = tiempoEspera;
    }
    
    public List<CajaAutomatica> getCajasAutomaticas(){
        return cajasAutomaticas;
    }

    public int getTiempoMaxEspera() {
        return tiempoMaxEspera;
    }

    @Override
    public String toString() {
        return "Cliente " + id;
    }
    
    public int getTiempoCliente(){
        return tiempoCompra + tiempoEspera + tiempoPago;
    }
}
