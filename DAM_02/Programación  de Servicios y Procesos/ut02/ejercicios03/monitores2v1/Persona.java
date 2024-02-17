
package ut02.ejercicios03.monitores2v1;

import java.util.Random;

public class Persona implements Runnable{
    
    private final String nombre;
    private final Contador cuenta;
    private final Random random;
    
    private boolean seguir;

    public Persona(String nombre, Contador contador) {
        this.nombre = nombre;
        this.cuenta = contador;
        this.random = new Random();
        this.seguir = true;
    }
    
    @Override
    public void run() {
        while(seguir){
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {

            }
            operacion( random.nextBoolean()? 1 : 0 );
            System.out.println( nombre + ": Cuenta actual en " + cuenta.getValor());
        }
    }
    
    private void operacion(int operacion){
        int cantidad = random.nextInt(10);
        switch(operacion){
            case 0 :
                System.out.println( nombre + " ingresa a la cuenta " + cantidad);
                cuenta.incrementa(cantidad);
                break;
            case 1 :
                System.out.println( nombre + " retira de la cuenta " + cantidad);
                cuenta.decrementa(cantidad);
                break;
        }
    }
    
    public void finalizar(){
        seguir = false;
    }
    
}
