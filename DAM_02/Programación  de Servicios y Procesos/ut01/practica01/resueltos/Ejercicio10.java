package ut01.practica01.resueltos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Ejercicio10 {
    public static void main(String[] args) throws IOException {
    	
        ProcessBuilder pb  = new ProcessBuilder("CMD","/C","DIR");
//        Map entorno = pb.environment();
//        System.out.println("Entorno");
//        System.out.println(entorno);
        
        File fOut = new File("salida.txt");
        File fErr = new File("error.txt");
        
        pb.redirectError(fErr);
        pb.redirectOutput(fOut);
        pb.start();
    }
}
