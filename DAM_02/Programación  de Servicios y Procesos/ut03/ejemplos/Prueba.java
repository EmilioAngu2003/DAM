/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ut03.ejemplos;

/**
 *
 * @author profesor
 */
class Perro {
    
}

public class Prueba {
    public static void main(String[] args) {
        Object p = new Perro();
        if (p instanceof Perro)
            System.out.println("Es un perro");
        if (p.getClass().equals(Perro.class))
            System.out.println("Otro perro");
    }
}
