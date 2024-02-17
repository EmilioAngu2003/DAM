package com.dam2.accesoadatos.ut01.ejercicio06;

import java.io.File;

public class BlocNotasModelo {

    private File fichero;

    public BlocNotasModelo() {
        this.fichero = null;
    }

    public File getFichero() {
        return fichero;
    }

    public void setFichero(File fichero) {
        this.fichero = fichero;
    }
}
