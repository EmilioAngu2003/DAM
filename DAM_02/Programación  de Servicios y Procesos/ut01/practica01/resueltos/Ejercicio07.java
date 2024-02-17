package ut01.practica01.resueltos;

	


	import java.io.BufferedReader;
	import java.io.FileOutputStream;
	import java.io.InputStream;
	import java.io.InputStreamReader;
	import java.io.PrintWriter;


public class Ejercicio07 {
	    public static void main(String[] args) {
	    	
	        Runtime r = Runtime.getRuntime();
	        String comando= "cmd /c dir";
	        Process p=null;
       
	       try{

	           p = r.exec("CMD /C DIR"); 
	           InputStream is = p.getInputStream();
	           BufferedReader br = new BufferedReader(new InputStreamReader(is));
	            
	           FileOutputStream fos = new FileOutputStream("Salida.txt");
	           PrintWriter pw = new PrintWriter(fos);

	            String linea;
	            while((linea=br.readLine())!=null) {
	                System.out.println("Inserto en Salida.txt: " +linea);
	                pw.println(linea);
	        }
	        
	        br.close();   
	        pw.close();
	        
	       } catch(Exception e){ e.printStackTrace();}
	    }
	}


