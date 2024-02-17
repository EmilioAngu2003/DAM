package ut01.practica01.resueltos;

import java.io.IOException;

public class Ejercicio02 {
	public static void main(String[] args){
		Runtime r = Runtime.getRuntime();
		try {
			r.exec("notepad");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
