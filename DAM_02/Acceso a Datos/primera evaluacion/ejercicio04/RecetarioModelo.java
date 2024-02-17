package com.dam2.accesoadatos.ut01.ejercicio04;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class RecetarioModelo {
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty descripcion = new SimpleStringProperty();
    private final SimpleListProperty<String> ingredientes = new SimpleListProperty<>(FXCollections.observableArrayList());

    public RecetarioModelo() {
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public SimpleListProperty<String> ingredientesProperty() {
        return ingredientes;
    }
}
