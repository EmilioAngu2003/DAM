
package ut02.monitores_v2;

import java.util.LinkedList;

class Barrera {
    
    private static final LinkedList<Coche> cruzando = new LinkedList<>();
    private static String direccionActual = null;
    private static int turnoDeCocheActual = 0;

    private final LinkedList<Coche> cola;
    private final int maxCoches;
    
    Barrera(int maxCoches) {
       this.cola = new LinkedList<>();
       this.maxCoches = maxCoches;
    }

    public synchronized void add(Coche c){
        setDireccion(c.getDireccion());
        cola.add(c);
        System.out.println("Cola " + c.getDireccion() + " " + cola);
    }
    
    private static synchronized void setDireccion(String direccion) {
        if(direccionActual == null) direccionActual = direccion;
    }

    public synchronized Coche get() {
        Coche c = cola.getFirst();
        while(!c.getDireccion().equals(direccionActual) || turnoDeCocheActual == maxCoches){
            try {
                System.out.println(c.toString() + " | ESPERA");
                wait();
            } catch (InterruptedException ex) {}
        }
        cola.poll();
        turnoDeCocheActual++;
        return pasaBarrera(c);
    }
    
    private static synchronized Coche pasaBarrera(Coche c) {
        System.out.println(c.toString() + " | CRUZANDO");
        cruzando.add(c);
        System.out.println("Cruzando " +cruzando);
        return cruzando.getLast();
    }

    public synchronized void update(){
        Coche c = pasaPuente();
        if( cruzando.isEmpty() ){
            turnoDeCocheActual = 0;
            direccionContraria();
            System.out.println("CAMBIO DE DIRECCION " + direccionActual); 
            notifyAll();
        }
    }
    
    private static synchronized Coche pasaPuente() {
        return cruzando.pollFirst();
    }

    private static void direccionContraria(){
        direccionActual = switch(direccionActual){
            case "->" -> "<-";
            case "<-" -> "->";
            default -> null;
        };
    }
}
