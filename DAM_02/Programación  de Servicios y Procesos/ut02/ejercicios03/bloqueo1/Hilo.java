package bloqueo1;

public class Hilo implements Runnable {
	
	private boolean finalizado = false;
	private boolean suspendido = false;   
   
	public void run() {
		
		while(!finalizado)
		{
			   System.out.println();
			   System.out.println("Bucle principal ejecutándose");	
			   
			if (!suspendido) 
			{
	
			   //...
			   System.out.println();	
			   System.out.println("Hilo ejecutándose");
			   System.out.println("Suspendido está a " + suspendido); 
			   System.out.println("Finalizado está a " + finalizado);
   			   //...
			}   
		}
	    
   }

	public synchronized void finalizar() {finalizado = true;}
	public synchronized void suspender() {suspendido = true;}
	public synchronized void reactivar() {suspendido = false;

	}
}

 

    

 


