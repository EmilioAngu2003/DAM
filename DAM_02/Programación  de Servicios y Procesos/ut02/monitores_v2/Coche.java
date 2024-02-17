
package ut02.monitores_v2;

class Coche implements Runnable {
    
    private final int id;
    private final String direccion;
    private final Puente puente;

    public Coche(int id, String direccion, Puente puente) {
        this.id = id;
        this.direccion = direccion;
        this.puente = puente;
    }

    public int getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }
    
    @Override
    public void run() {
        puente.pasar(this);
    }

    @Override
    public String toString() {
        return "Coche " + id;
    }
}
