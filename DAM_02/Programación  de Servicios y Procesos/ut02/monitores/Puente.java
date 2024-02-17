
package ut02.monitores;

import java.util.HashMap;

class Puente {

    private final String[] direcciones;
    private final int maxPasoDeCoches;
    private final int millis;
    
    private final Object lock;
    private final HashMap<String, Barrera> barreras;
    
    public Puente( String[] direcciones, int maxPasoDeCoches, int millis){
        this.direcciones = direcciones;
        this.maxPasoDeCoches = maxPasoDeCoches;
        this.millis = millis;
        
        this.lock = new Object();
        this.barreras = new HashMap<>();
        
        for(String direccion:direcciones) barreras.put(direccion, new Barrera(lock));
    }
    
    public void atravesarCoche(Coche c) {
        
        // barrera con la direccion del coche
        Barrera b = barreras.get(c.getDireccion());
        
        // llegada ordenada del coche
        b.llegaCoche(c);

        // bloquea si cruzan 3 coches o su direccion es incorrecta
        b.bloqueo(c.getDireccion(), maxPasoDeCoches);
        
        // salida ordenada del coche
        b.cruzaCoche(millis);
        
        // desbloquea si pasaron 3 coches y su direccion es correcta
        b.desbloqueo(nuevaDireccion(c.getDireccion()), maxPasoDeCoches);

    }
    
    
    // retorna una direccion nueva
    public String nuevaDireccion(String direccion){
        
        for(String d:direcciones) if(!d.equals(direccion) && !barreras.get(d).getCola().isEmpty()) return d;
        
        if(!barreras.get(direccion).getCola().isEmpty()) return direccion;
        
        return null;
    }
}