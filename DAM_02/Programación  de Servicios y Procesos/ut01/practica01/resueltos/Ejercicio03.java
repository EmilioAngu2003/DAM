package ut01.practica01.resueltos;

import java.io.IOException;

public class Ejercicio03 {
	public static void main(String[] args){
		
		Runtime r = Runtime.getRuntime();
		try { r.exec("CMD /C DIR");	} 
		catch (IOException e) {e.printStackTrace();}
		
	}
}
