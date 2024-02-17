
package ut02.ejercicios04.supermercado02;

import java.util.List;
import java.util.Random;

class Cliente extends Thread {
    
    private static final Random random = new Random();
    private static int contadorClientes = 0;
    
    private final int TIEMPO_COMPRA = 5000;
    private final int TIEMPO_PAGO = 500;
    private final int MAX_PAGO = 200;
    private final int id;  
    private final List<Caja> cajas;
    
    public Cliente(List<Caja> cajas) {
        this.id = ++contadorClientes;
        this.cajas = cajas;
    }
    
    public List<Caja> getCajas(){
        return cajas;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(random.nextInt(TIEMPO_COMPRA));
            long inicia = System.currentTimeMillis();
            Caja caja = Caja.entrarEnCola(this);
            Thread.sleep(random.nextInt(TIEMPO_PAGO));
            caja.salirDeCaja(this, random.nextInt(MAX_PAGO));
            long termina =  System.currentTimeMillis();
            Principal.addTIempo(termina - inicia);
        } catch (InterruptedException e) {}
    }

    @Override
    public String toString() {
        return "Cliente " + id;
    } 
}