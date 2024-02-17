package com.dam2.accesoadatos.ut01.ejercicio01.base;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Alumno implements Serializable {
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String ciclo;

    public Alumno(String nombre, String apellidos, LocalDate fechaNacimiento, String ciclo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.ciclo = ciclo;
    }

    public Alumno() {
        this(null, null, null, null);
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }


    @Override
    public String toString() {
        return  nombre + ' ' +
                apellidos + ", " +
                fechaNacimiento.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ", " +
                ciclo;
    }

}
