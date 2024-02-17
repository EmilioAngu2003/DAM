package estructura3;

public class Principal{

    public static void main( String[] args ) {

        Hilo hilo = new Hilo();
        Thread thread = new Thread (hilo);

        thread.start();

        System.out.println("Hilo principal ejecutandose");
        try{Thread.sleep(1000);} catch (Exception ex) {}

        hilo.setSeguir(false);

        System.out.println("Hilo principal acabando...");
        try{Thread.sleep(1000);} catch (Exception ex) {}

   }
}

 

    

 


