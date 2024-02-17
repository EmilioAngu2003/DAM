package ut02.ejercicios03.monitores2v2;

public class Contador {
	
    private int c;

    public Contador(int c) {
        this.c = c;
    }

    public synchronized void incrementa(int cantidad) {
        c += cantidad;
    }

    public synchronized void decrementa(int cantidad) {
        c -= cantidad;
    }

    public synchronized int getValor() { 
        return c; 
    }

}
