
package productor_consumidor_5;

import java.util.Random;

public class Producto {
    
    private static final Random random = new Random();
    private static String[] nombres = null;
    private final String nombre;
    
    public Producto(){
        int indice = random.nextInt(nombres.length);
        nombre = nombres[indice];
    }
    
    @Override
    public String toString(){
        return nombre;
    }
    
    public static void setNombres(String[] nombres){
        Producto.nombres = nombres;
    }
    
}
