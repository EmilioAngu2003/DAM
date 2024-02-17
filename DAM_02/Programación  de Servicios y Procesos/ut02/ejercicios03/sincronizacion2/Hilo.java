package sincronizacion2;

public class Hilo implements Runnable {

    private boolean seguir;  
    private final String nombreHilo;

    public Hilo(String nombreHilo) {
        this.nombreHilo = nombreHilo;
        this.seguir = true;  
    }
    
    public void finalizar() {
        seguir=false;
    }

    @Override
    public void run() {
        while(seguir){
            mostrarLineas(nombreHilo);
        }
    }

    private static synchronized void mostrarLineas(String nombre){
        System.out.println(nombre + ". Mensaje linea 1");
        System.out.println(nombre + ". Mensaje linea 2");
        System.out.println(nombre + ". Mensaje linea 3");
        System.out.println(nombre + ". Mensaje linea 4");
        System.out.println(nombre + ". Mensaje linea 5");
    }

}

 

    

 


