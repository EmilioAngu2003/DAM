package estructura3;

public class Hilo implements Runnable {
    
    private boolean seguir;
    
    public Hilo(){
        seguir = true;
    }
   
    @Override
    public void run() {
        while(seguir) System.out.println("Hilo ejecutandose");
    }

    public void setSeguir(boolean seguir) {
        this.seguir = seguir;
    }

}

 

    

 


