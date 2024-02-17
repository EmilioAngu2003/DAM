package com.dam2.accesoadatos.ut01.ejercicio05;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Principal extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("IMDBVista.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("IMDB");
            stage.setScene(new Scene(root, 366, 305));
            stage.show();
        } catch (IOException e) {
            System.out.println("Error de ejecucion del Programa " + e);
        }
    }
}
