
package ut02.ejercicios04.elecciones;

public class Ciudadano extends Thread {
    
    private static int siguienteID = 1;
    
    private final int id;
    private final boolean vota;
    private final MesaElectoral mesa;
    private final int horaLlegada;
    
    public Ciudadano(boolean vota, MesaElectoral mesa, int horaLlegada){
        this.id = siguienteID++;
        this.vota = vota;
        this.mesa = mesa;
        this.horaLlegada = horaLlegada;
    }

    @Override
    public void run() {
        if(!vota) {
            System.out.println("Ciudadano " + id + " NO voto");
            MesaElectoral.noVotar(this);
        }else{
            try {
                Thread.sleep(horaLlegada);
                mesa.entrarEnMesa(this);
                MesaElectoral.votar();
                mesa.salirDeMesa(this);
                System.out.println("Ciudadano " + id + " voto");
            } catch (InterruptedException e) {}
        }
    }
    
}
