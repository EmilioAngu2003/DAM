
package monitores1;

public class HiloA implements Runnable{
    
    private final String nombre;
    private final Contador contador;

    public HiloA(String nombre, Contador contador) {
        this.nombre = nombre;
        this.contador = contador;
    }
    
    @Override
    public void run() {
        while(true){
            contador.incrementa();
            System.out.println( nombre + ": Cuenta actual en " + contador.getValor());
        }
    }
    
}
