package estructura1;

public class Principal{

    public static void main( String[] args ) {

        Thread h = new Thread (new Hilo());

        h.start();

        System.out.println("Hilo principal ejecutandose");
    }
}

 

    

 


