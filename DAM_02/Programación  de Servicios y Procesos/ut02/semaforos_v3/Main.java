
package ut02.semaforos_v3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {
    
    public static void main(String[] args) {
        // Crear una instancia de la clase Interfaz
        Interfaz interfaz = new Interfaz();
        
        // Obtener la configuración de la interfaz
        boolean aleatorio = interfaz.aleatorio;
        int ficheros = interfaz.ficheros;
        int escritores = interfaz.escritores;
        int lectores = interfaz.lectores;
        int maxLectores = aleatorio ? (int)(Math.random()*(lectores/2)) : interfaz.maxLectoresDefecto;
        List<Integer> ficherosCustom = interfaz.ficherosCustom;
        
        // Imprimir la configuracion
        System.out.println("Configuracion:");
        System.out.println("Ficheros: " + ficheros);
        System.out.println("Escritores: " + escritores);
        System.out.println("Lectores: " + lectores);
        System.out.println("Lectores por defecto: " + maxLectores);
        System.out.println("Lista de Maximos Lectores en Ficheros Personalizados:");
        ficherosCustom.forEach(s -> System.out.print(s + " - "));
        System.out.println("");

        // Crear una lista de semáforos
        ArrayList<Semaphore> s = new ArrayList<>();
        ArrayList<Integer> p = new ArrayList<>();
        
        // Crear semáforos para ficheros personalizados
        for (Integer i : ficherosCustom) {
            s.add(new Semaphore(i));
            p.add(i);
        }

        // Crear el resto de los semáforos con el máximo de lectores aleatorio o no
        for (int i = 0; i < ficheros - ficherosCustom.size(); i++) {
            s.add(new Semaphore(maxLectores));
            p.add(maxLectores);
        }

        // Crear y arrancar hilos escritores
        for (int i = 1; i <= escritores; i++) {
            new Escritor("Escritor " + i, s, p).start();
        }

        // Crear y arrancar hilos lectores
        for (int i = 1; i <= lectores; i++) {
            new Lector("Lector " + i, s).start();
        }
    }
}
