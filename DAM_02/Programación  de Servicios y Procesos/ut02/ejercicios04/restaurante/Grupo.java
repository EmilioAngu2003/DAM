
package ut02.ejercicios04.restaurante;

import java.util.List;
import java.util.Random;

public class Grupo extends Thread{
    
    private static final Random random = new Random();
    private static int contadorGrupos = 0;
    
    private final int TIEMPO_ESPERA = 10000;
    private final int TIEMPO_COMIDA = 2000;
    private final int id;
    private final int personas;
    private final Boolean fuman;
    private final List<Sala> salas;
    private Integer mesa;
    
    public Grupo (int maxPersonas, int minPersonas, List<Sala> salas){
        this.id = ++contadorGrupos;
        this.personas = random.nextInt(maxPersonas-minPersonas+1)+minPersonas;
        this.fuman = random.nextInt(2) == 0;
        this.salas = salas;
        this.mesa = null;
    }
     
    public List<Sala> getSalas(){
        return salas;
    }

    public int getPersonas() {
        return personas;
    }

    public boolean esFumador(){
        return fuman;
    }

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

    @Override
    public String toString() {
        return "Grupo " + id;
    }

    @Override
    public void run() {
        try {
            System.out.println(toString() + " que " + (fuman? "FUMA": "NO FUMA") + " de " + personas + " personas llega al restaurante");
            Sala sala = Sala.entrarEnSala(this, random.nextInt(TIEMPO_ESPERA) );
            System.out.println(toString() + " OCUPA una mesa de " + mesa + " personas en la " + sala.toString());
            Thread.sleep(random.nextInt(TIEMPO_COMIDA));
            System.out.println(toString() + " DESOCUPA una mesa de " + mesa + " personas en la " + sala.toString());
            sala.salirDeSala(this);
        } catch (InterruptedException e) {}
    }
}
