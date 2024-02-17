package monitores1;

public class Contador {
	
    private int c;

    public Contador(int c) {
        this.c = c;
    }

    public synchronized void incrementa() {
        System.out.println("Incrementando");
        c++;
        System.out.println("Nuevo valor c: = " + c);      
    }

    public synchronized void decrementa() {
        System.out.println("Decrementando");
        c--;
        System.out.println("Nuevo valor c = " + c);  
    }

    public synchronized int getValor() { 
        return c; 
    }

}
