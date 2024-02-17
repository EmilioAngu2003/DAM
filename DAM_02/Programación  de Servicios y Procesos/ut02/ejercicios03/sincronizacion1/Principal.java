package sincronizacion1;

public class Principal {

    public static void main( String[] args ) {

        Hilo hilo1 = new Hilo("Hilo 1");
        Hilo hilo2 = new Hilo("Hilo 2");
        Hilo hilo3 = new Hilo("Hilo 3");
        Hilo hilo4 = new Hilo("Hilo 4");
        Hilo hilo5 = new Hilo("Hilo 5");

        Thread thread1 = new Thread(hilo1);
        Thread thread2 = new Thread(hilo2);
        Thread thread3 = new Thread(hilo3);
        Thread thread4 = new Thread(hilo4);
        Thread thread5 = new Thread(hilo5);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        try{Thread.sleep(1000);} catch (Exception ex) {}
        
        hilo1.finalizar();
        hilo2.finalizar();
        hilo3.finalizar();
        hilo4.finalizar();
        hilo5.finalizar();
        
    }
}

 

    

 


