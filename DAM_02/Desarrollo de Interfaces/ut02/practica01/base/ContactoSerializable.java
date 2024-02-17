package ut02.practica01.base;

import ut02.practica01.modelo.ContactoModelo;
import java.io.Serializable;
import java.util.List;

public class ContactoSerializable implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer id;
    private final String nombre;
    private final String apellidos;
    private final List<String> telefonos;
    private final List<String> tipos;

    public ContactoSerializable(Integer id, String nombre, String apellidos, List<String> telefonos, List<String> tipos) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefonos = telefonos;
        this.tipos = tipos;
    }
    
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getApellidos() {
        return apellidos;
    }

    public List<String> getTelefonos() {
        return telefonos;
    }

    public List<String> getTipos() {
        return tipos;
    } 
    
    public ContactoModelo toModelo(){
        return new ContactoModelo(
                id,
                nombre,
                apellidos,
                telefonos,
                tipos
        );
    }
}
