package bloqueo2;

public class Hilo implements Runnable {
	
	private boolean finalizado = false;
	private boolean suspendido = false;    
   
	public void run() {
		
		while(!finalizado)
		{
			this.esperarReady();
	
			//...
			System.out.println("Hilo ejecutándose");
   			//...
			
		}   
	}


	public synchronized void finalizar() {finalizado = true;}
	public synchronized void suspender() {suspendido = true;  this.notifyAll();}
	public synchronized void reactivar() {suspendido = false; this.notifyAll();}

	private synchronized void esperarReady() {while(suspendido) try {wait(); suspendido = false;} catch (InterruptedException ex ) {}

	}
	
}
	
	
	
	



 

    

 


