package sincronizacion3;

public class Hilo implements Runnable {
    
    private static int contadorGlobalMensajes = 0;

    private boolean seguir;  
    private final String nombreHilo;

    public Hilo(String nombreHilo) {
        this.nombreHilo = nombreHilo;
        this.seguir = true;
    }

    @Override
    public void run() {
        while(seguir) {

            String linea1 = nombreHilo + ". Mensaje linea 1";
            String linea2 = nombreHilo + ". Mensaje linea 2";
            String linea3 = nombreHilo + ". Mensaje linea 3";
            String linea4 = nombreHilo + ". Mensaje linea 4";
            String linea5 = nombreHilo + ". Mensaje linea 5";
            String mensaje = linea1+"\n"+linea2+"\n"+linea3+"\n"+linea4+"\n"+linea5;

            imprimirSincro(mensaje);

        }
    }

    public synchronized void imprimirSincro(String mensaje) {

        contadorGlobalMensajes++;

        /*   
         * Simulaci�n de procesos del hilo
         * Inicio
         */  
        try{Thread.sleep((int)(Math.random()*100));}catch(Exception ex) {}
        /*   
         * Simulaci�n de procesos del hilo
         * Fin
         */  		

        System.out.println("Mensaje global " + contadorGlobalMensajes);
        System.out.println(mensaje);
    }

    public void finalizar() {
        seguir=false;
    }
}

 

    

 


