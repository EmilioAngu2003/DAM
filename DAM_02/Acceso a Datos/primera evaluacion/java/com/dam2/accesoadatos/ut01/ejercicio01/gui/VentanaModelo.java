package com.dam2.accesoadatos.ut01.ejercicio01.gui;


import com.dam2.accesoadatos.ut01.ejercicio01.base.Alumno;
import java.util.ArrayList;
import java.util.List;

public class VentanaModelo {

    private final List<Alumno> listaAlumnos;
    private int posicion;

    public VentanaModelo() {
        listaAlumnos = new ArrayList<>();
        posicion = -1;
    }

    public void primerIndice() { posicion = 0; }

    public void anteriorIndice() { if (posicion > 0) posicion--; }

    public void siguienteIndice() { if (posicion < listaAlumnos.size() - 1) posicion++; }

    public void ultimoIndice() { posicion = listaAlumnos.size() - 1; }

    public Alumno getAlumno(){
        if(posicion < 0 || posicion > listaAlumnos.size() - 1) throw new IndexOutOfBoundsException();
        return listaAlumnos.get(posicion);
    }


    public void nuevo(Alumno alumno) {
        listaAlumnos.add(alumno);
        posicion = listaAlumnos.size() - 1;
    }

    public void guardar(Alumno a) {
        listaAlumnos.set(posicion, a);
    }

    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public int getPosicion(){
        return posicion;
    }
}
