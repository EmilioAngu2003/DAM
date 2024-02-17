package monitores1;

public class PrincipalContador {

    public static void main(String[] args) {

        Contador cont = new Contador(100);
        
        HiloA a = new HiloA("Hilo A", cont);
        HiloB b = new HiloB("Hilo B", cont);

        Thread uno = new Thread(a);
        Thread dos = new Thread(b);

        uno.start();
        dos.start();
    }
}