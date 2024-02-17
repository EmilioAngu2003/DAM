package ut02.ejercicios03.sincronizacionFinal;

public class Hilo implements Runnable {
    
    private static int contadorGlobalMensajes = 0;

    private boolean seguir = true;  
    private final String nombreHilo;

    public Hilo(String nombreHilo) {
        this.nombreHilo = nombreHilo;
    }

    @Override
    public void run() {
        while(seguir) { 
            
            int numLineas = 5;
            StringBuilder mensaje = new StringBuilder();

            for (int i=1; i<=numLineas; i++) {
                mensaje.append(nombreHilo).append(". Mensaje linea ").append(i).append("\n");
            }

            imprimirSincro(mensaje.toString());

        }
    }

    public static synchronized void imprimirSincro(String mensaje) {

        contadorGlobalMensajes++;
 
        try{Thread.sleep((int)(Math.random()*100));}catch(InterruptedException ex) {} 		

        System.out.println("Mensaje global " + contadorGlobalMensajes);
        System.out.println(mensaje);
    }

    public void finalizar() {
        seguir=false;
    }
}

 

    

 


