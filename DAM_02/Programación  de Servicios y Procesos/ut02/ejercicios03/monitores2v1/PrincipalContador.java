package ut02.ejercicios03.monitores2v1;

public class PrincipalContador {

    public static void main(String[] args) {

        Contador cont = new Contador(100);
        
        Persona a = new Persona("Persona A", cont);
        Persona b = new Persona("Persona B", cont);

        Thread uno = new Thread(a);
        Thread dos = new Thread(b);

        uno.start();
        dos.start();
        
        try {
            Thread.sleep(50);
            a.finalizar();
            b.finalizar();
        } catch (InterruptedException ex) {
            
        }
    }
}