package estructuraFinal;

public class Hilo implements Runnable {
    
    private boolean seguir;
    
    public Hilo(boolean seguir){
        this.seguir = seguir;
    }
   
    @Override
    public void run() {
        while(seguir) System.out.println("Hilo ejecutandose");
    }

    public void finalizar(){
        seguir = false;
    }

}

 

    

 


