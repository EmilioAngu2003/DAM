package ut05.ejemplos;

import java.io.*;
public class Ejemplo2 {
	public static void main(String [] args){
	try{
		BufferedWriter fichero = new BufferedWriter(new FileWriter("c:\\ficheros\\prueba.txt"));
		fichero.write("Prueba de escritura");
		fichero.close();
		System.out.println("Fin de programa");
		
	}catch (Exception e){e.printStackTrace();}
	}
}