package ut01.practica01.resueltos;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Ejercicio09 {
    public static void main(String[] args) {
    	

    	System.out.println("Variables de entorno del proceso");
    	ProcessBuilder procesoPrueba = new ProcessBuilder();
        Map entorno = procesoPrueba.environment();
        System.out.println(entorno);
        
        System.out.println();

        System.out.println("Argumentos enviados al constructor del proceso");
        procesoPrueba = new ProcessBuilder("java", "UnSaludo", "Hola Mundo");
        List listaComandos = procesoPrueba.command();
        Iterator iter = listaComandos.iterator();
        while (iter.hasNext()) System.out.println(iter.next());
        
        System.out.println();
        
        procesoPrueba = procesoPrueba.command("CMD", "/C", "DIR");
        try{
            Process p = procesoPrueba.start();
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String linea;
            while((linea=br.readLine())!=null) { System.out.println(linea);}
            br.close();
        }catch (Exception e){e.printStackTrace();}
    }
}
