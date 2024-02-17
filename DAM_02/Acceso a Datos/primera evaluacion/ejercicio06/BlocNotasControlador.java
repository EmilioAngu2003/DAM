package com.dam2.accesoadatos.ut01.ejercicio06;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class BlocNotasControlador {

    public TextArea txtArea;
    public BlocNotasModelo modelo;

    @FXML
    public void initialize(){
        modelo = new BlocNotasModelo();
    }

    public void nuevo() {
        txtArea.clear();
    }

    public void guardar() {
        if(modelo.getFichero() != null) escribir();
        if(modelo.getFichero() == null) guardarComo();
    }

    public void guardarComo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar como");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos txt", "*.txt"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"), "Documents"));
        modelo.setFichero(fileChooser.showSaveDialog(null));
        escribir();
    }

    public void escribir() {
        try {
            Files.write(modelo.getFichero().toPath(), txtArea.getText().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException | NullPointerException e) {
            System.out.println("Error de escritura del fichero " + e);
        }
    }
}
