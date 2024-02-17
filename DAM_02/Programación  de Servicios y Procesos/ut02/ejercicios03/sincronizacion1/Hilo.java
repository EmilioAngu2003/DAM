package sincronizacion1;

public class Hilo implements Runnable {

    private boolean seguir;  
    private final String nombreHilo;
    
    public Hilo(String nombreHilo) {
        this.seguir = true;  
        this.nombreHilo = nombreHilo;
    }

    @Override
    public void run() {
            while(seguir)
            {
                    System.out.println(nombreHilo + ". Mensaje linea 1");
                    System.out.println(nombreHilo + ". Mensaje linea 2");
                    System.out.println(nombreHilo + ". Mensaje linea 3");
                    System.out.println(nombreHilo + ". Mensaje linea 4");
                    System.out.println(nombreHilo + ". Mensaje linea 5");
            }
    }

    public void finalizar() {
        seguir=false;
    }
    
}

 

    

 


