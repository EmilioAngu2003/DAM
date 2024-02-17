package bloqueo2;

public class Principal 
{
	
   public static void main( String[] args )
   {

	 System.out.println();
 	 System.out.println("Lanzando hilo secundario");
 	 System.out.println("**");
	 Hilo hilo = new Hilo();
     Thread thread = new Thread (hilo);
     thread.start();
     
     try{Thread.sleep(2);} catch (Exception ex) {}

     System.out.println();     
     System.out.println("Suspendido hilo secundario (NO consumirá CPU)");
 	 System.out.println("**");
     hilo.suspender();

     try{Thread.sleep(2);} catch (Exception ex) {}

	 System.out.println();
     System.out.println("Reactivando hilo secundario");
 	 System.out.println("**");
     hilo.reactivar();
     
     try{Thread.sleep(2);} catch (Exception ex) {}
		
     System.out.println();    
     System.out.println("Finalizando hilo secundario");
 	 System.out.println("**");
     hilo.finalizar();
     
     try{Thread.sleep(2);} catch (Exception ex) {}
     
 	 System.out.println();
     System.out.println("Hilo principal acabando...");
 	 System.out.println("**");

   }

}

 

    

 


