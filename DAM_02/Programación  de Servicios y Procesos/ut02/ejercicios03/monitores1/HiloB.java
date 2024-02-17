
package monitores1;

public class HiloB implements Runnable{
    
    private final String nombre;
    private final Contador contador;

    public HiloB(String nombre, Contador contador) {
        this.nombre = nombre;
        this.contador = contador;
    }

    @Override
    public void run() {
        while(true){
            contador.decrementa();
            System.out.println( nombre + ": Cuenta actual en " + contador.getValor());
        }
    }
    
}
