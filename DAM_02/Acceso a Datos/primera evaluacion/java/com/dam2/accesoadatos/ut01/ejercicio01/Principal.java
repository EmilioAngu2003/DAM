package com.dam2.accesoadatos.ut01.ejercicio01;

import com.dam2.accesoadatos.ut01.ejercicio01.gui.Ventana;
import com.dam2.accesoadatos.ut01.ejercicio01.gui.VentanaControlador;
import com.dam2.accesoadatos.ut01.ejercicio01.gui.VentanaModelo;

public class Principal {

    public static void main(String args[]) {
        Ventana view = new Ventana();
        VentanaModelo model = new VentanaModelo();
        VentanaControlador controller = new VentanaControlador(model, view);
    }

}
