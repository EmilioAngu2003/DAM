package estructuraFinal;

public class Principal {

    public static void main( String[] args ){
	    
        Hilo hilo = new Hilo(true);
        Thread thread = new Thread (hilo);

        thread.start();

        System.out.println("Hilo principal ejecutandose");
        try{Thread.sleep(1000);} catch (InterruptedException ex) {}

        hilo.finalizar();

        System.out.println("Hilo principal acabando...");
        try{Thread.sleep(1000);} catch (InterruptedException ex) {}

    }
}

 

    

 


