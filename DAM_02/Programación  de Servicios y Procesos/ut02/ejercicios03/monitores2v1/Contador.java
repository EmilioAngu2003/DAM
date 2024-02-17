package ut02.ejercicios03.monitores2v1;

public class Contador {
	
    private int c;

    public Contador(int c) {
        this.c = c;
    }

    public synchronized void incrementa(int cantidad) {
        System.out.println("Incrementando");
        c += cantidad;
        System.out.println("Nuevo valor c: = " + c);      
    }

    public synchronized void decrementa(int cantidad) {
        System.out.println("Decrementando");
        c -= cantidad;
        System.out.println("Nuevo valor c = " + c);  
    }

    public synchronized int getValor() { 
        return c; 
    }

}
