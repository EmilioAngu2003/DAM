package estructura2;

public class Hilo implements Runnable {
    
    boolean seguir = true;
   
    @Override
    public void run() {
        while(seguir) System.out.println("Hilo ejecutandose");
    }
}

 

    

 


