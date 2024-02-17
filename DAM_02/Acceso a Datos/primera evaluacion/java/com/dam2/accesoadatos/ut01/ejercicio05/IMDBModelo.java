package com.dam2.accesoadatos.ut01.ejercicio05;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class IMDBModelo {
    private final ObservableList<Pelicula> listaPeliculas;

    public IMDBModelo() {
        this.listaPeliculas = FXCollections.observableArrayList();
    }

    public ObservableList<Pelicula> getListaPeliculas() {
        return listaPeliculas;
    }
}