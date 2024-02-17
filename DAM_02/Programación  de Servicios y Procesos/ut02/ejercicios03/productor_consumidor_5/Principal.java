
package productor_consumidor_5;

import java.util.ArrayList;

public class Principal {
    
    private static final String[] NOMBRES_PRODUCTOS = {"carne", "pan", "leche", "maiz"};
    private static final int MAXIMO_PRODUCTOS = 5;
    private static final int CONSUMIDORES = 3;
    private static final int PRODUCTORES = 3;
    
    public static void main(String[] args){
        
        Producto.setNombres(NOMBRES_PRODUCTOS);
        
        ArrayList<Producto> productos = new ArrayList<>();
        for(int i=1; i<=MAXIMO_PRODUCTOS; i++) productos.add(new Producto());
        Cola<Producto, Productor, Consumidor> cola = new Cola(productos,MAXIMO_PRODUCTOS);
        
        System.out.println("Cola " + cola.getCola() + " | Limite de productos " + MAXIMO_PRODUCTOS);
        
        ArrayList<Productor> productores = new ArrayList<>();
        ArrayList<Consumidor> consumidores = new ArrayList<>();
        
        for(int i=1; i<=PRODUCTORES; i++) productores.add(new Productor(i, cola));
        for(int i=1; i<=CONSUMIDORES; i++) consumidores.add(new Consumidor(i, cola));
        
        for(Productor p:productores) new Thread(p).start();
        for(Consumidor c:consumidores) new Thread(c).start();
        
        try {
            Thread.sleep(50);
            for(Productor p:productores) p.finalizar();
            for(Consumidor c:consumidores) c.finalizar();
        } catch (InterruptedException ex) { }
        
    }
    
}
