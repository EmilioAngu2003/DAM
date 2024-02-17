
package ut02.practica01.base;

import java.io.Serializable;
import ut02.practica01.modelo.ContactoModelo;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ut02.practica01.modelo.AgendaModelo;

public class AgendaSerializable implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private final Integer nextID;
    private final List<ContactoSerializable> listaContactos;
    private final List<String> listaTipos;

    public AgendaSerializable(Integer nextID, List<ContactoSerializable> listaContactos, List<String> listaTipos) {
        this.nextID = nextID;
        this.listaContactos = listaContactos;
        this.listaTipos = listaTipos;
    }

    public Integer getNextID() {
        return nextID;
    }

    public List<ContactoSerializable> getListaContactos() {
        return listaContactos;
    }

    public List<String> getListaTipos() {
        return listaTipos;
    }
    
    public AgendaModelo toModelo() {
        return new AgendaModelo(
                nextID,
                toListContactoModelo(listaContactos),
                listaTipos
        );
    }
    
    private ObservableList<ContactoModelo> toListContactoModelo(List<ContactoSerializable> contactos) {
        ObservableList<ContactoModelo> contactosModelo = FXCollections.observableArrayList();
        for (ContactoSerializable contactoSerializable : contactos) contactosModelo.add(contactoSerializable.toModelo());
        return contactosModelo;
    }
}
