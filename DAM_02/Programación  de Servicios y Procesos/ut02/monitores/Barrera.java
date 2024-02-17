
package ut02.monitores;

import java.util.LinkedList;
import java.util.Queue;

class Barrera {

    public static String direccionActual = null;
    private final Object lock;
    private final Queue<Coche> cola;
    private int contadorEntrada;
    private int contadorSalida;
    
    public Barrera(Object lock){
        this.lock = lock;
        this.cola = new LinkedList<>();
        this.contadorEntrada = 0;
        this.contadorSalida = 0;
    }

    public Queue<Coche> getCola(){
        return cola;
    }
    
    public void llegaCoche(Coche c){
        synchronized(lock){
            cola.add(c);
            if(direccionActual == null){
                direccionActual = c.getDireccion();
                System.out.println("Primera direccion: "+ direccionActual);
            }
            System.out.println("Direccion " + c.getDireccion() + " | Coche " + c.getId() + " intenta cruzar el puente");
        }
    }
    
    public void bloqueo(String direccion, int maxPasoDeCoches) {
        
        while ( contadorEntrada == maxPasoDeCoches || !direccionActual.equals(direccion)) {
            try {
                synchronized(lock){
                    lock.wait();
                }
            } catch (InterruptedException ex) { }
        }
        contadorEntrada++;
    }
    
    public void cruzaCoche(int millis) {
        try {
            Coche c;
            synchronized(lock){
                c = cola.poll(); 
            }
            System.out.println("Direccion " + c.getDireccion() + " | Coche " + c.getId() + " esta cruzando el puente");
            Thread.sleep(millis);
            System.out.println("Direccion " + c.getDireccion() + " | Coche " + c.getId() + " ha cruzado el puente");
            
        } catch (InterruptedException | NullPointerException e) { } 
    }

    public void desbloqueo(String direccion, int pasoDeCoches){
        
        synchronized(lock){
            if(direccion == null) return;
        
            contadorSalida++;
            if(contadorSalida == pasoDeCoches){
            
                if(!direccionActual.equals(direccion)){
                    direccionActual = direccion;
                    System.out.println("Cambio de Direccion: " + direccionActual);
                }
                contadorEntrada = 0;
                contadorSalida = 0;
                lock.notifyAll();
            }
        }
    }
}
