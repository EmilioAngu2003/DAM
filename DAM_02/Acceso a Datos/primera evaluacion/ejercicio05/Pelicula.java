package com.dam2.accesoadatos.ut01.ejercicio05;

import java.util.List;

public record Pelicula(String titulo, String fecha, String genero, String sinopsis, List<String> reparto) {

    @Override
    public String toString() {
        return titulo;
    }
}
