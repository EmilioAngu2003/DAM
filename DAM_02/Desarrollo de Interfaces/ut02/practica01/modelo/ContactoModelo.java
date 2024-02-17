
package ut02.practica01.modelo;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import ut02.practica01.base.ContactoSerializable;

public class ContactoModelo{
    
    private final SimpleIntegerProperty idProperty;
    private final SimpleStringProperty nombreProperty;
    private final SimpleStringProperty apellidosProperty;
    private final SimpleListProperty<String> telefonosProperty;
    private final SimpleListProperty<String> tiposProperty;

    public ContactoModelo(Integer id, String nombre, String apellidos, List<String> telefonos, List<String> tipos) {
        this.idProperty =  new SimpleIntegerProperty(id);
        this.nombreProperty = new SimpleStringProperty(nombre);
        this.apellidosProperty = new SimpleStringProperty(apellidos);
        this.telefonosProperty = new SimpleListProperty<>(FXCollections.observableArrayList(telefonos));
        this.tiposProperty = new SimpleListProperty<>(FXCollections.observableArrayList(tipos));
    }
    
    public SimpleIntegerProperty idProperty(){
        return idProperty;
    }

    public SimpleStringProperty nombreProperty() {
        return nombreProperty;
    }

    public SimpleStringProperty apellidosProperty() {
        return apellidosProperty;
    }

    public SimpleListProperty<String> telefonosProperty() {
        return telefonosProperty;
    }

    public SimpleListProperty<String> tiposProperty() {
        return tiposProperty;
    }
    
    public ContactoSerializable toSerializable(){
        return new ContactoSerializable(
                idProperty.get(),
                nombreProperty.get(),
                apellidosProperty.get(),
                new ArrayList<>(telefonosProperty),
                new ArrayList<>(tiposProperty)
        );
    }
}
