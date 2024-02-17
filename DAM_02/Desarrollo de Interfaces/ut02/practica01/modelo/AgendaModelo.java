
package ut02.practica01.modelo;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ut02.practica01.base.AgendaSerializable;
import ut02.practica01.base.ContactoSerializable;

public class AgendaModelo {
    
    private Integer nextID;
    private final ObservableList<ContactoModelo> listaContactos;
    private final SimpleListProperty<String> listaTipos;
    private ContactoModelo contactoModelo;
    
    public AgendaModelo(Integer nextID, List<ContactoModelo> listaContactos, List<String> listaTipos) {
        this.nextID = nextID;
        this.listaContactos = FXCollections.observableArrayList(listaContactos);
        this.listaTipos = new SimpleListProperty<>(FXCollections.observableArrayList(listaTipos));
    }
    
    public void setContactoModelo(ContactoModelo contactoModelo) {
        this.contactoModelo = contactoModelo;
    }
    
    public ContactoModelo getContactoModelo(){
        return contactoModelo;
    }

    public SimpleListProperty<String> getListaTipos() {
        return listaTipos;
    }
    
    public ObservableList<ContactoModelo> getListaContactos() {
        return listaContactos;
    }

    public Integer getNextID() {
        return nextID;
    }

    public Integer nextID(){
        return nextID + 1;
    }

    public void remove(ContactoModelo contacto){
        listaContactos.remove(contacto);
    }
    
    public void add(){
        listaContactos.add(contactoModelo);
        nextID++;
    }
    
    public AgendaSerializable toSerializable() {
        return new AgendaSerializable(
                nextID,
                toListContactoSerializable(listaContactos),
                new ArrayList<>(listaTipos)
        );
    }

    private List<ContactoSerializable> toListContactoSerializable(ObservableList<ContactoModelo> contactos) {
        List<ContactoSerializable> contactosSerializable = new ArrayList<>();
        for (ContactoModelo contactoProperty : contactos) contactosSerializable.add(contactoProperty.toSerializable());
        return contactosSerializable;
    }
}
