package com.dam2.accesoadatos.ut01.ejercicio04;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RecetarioControlador {
    public TextField txtNombre;
    public TextArea txtDescripcion;
    public ListView<String> listIngredientes;
    private RecetarioModelo receta;

    @FXML
    private void initialize() {

        receta = new RecetarioModelo();

        txtNombre.textProperty().bindBidirectional(receta.nombreProperty());
        txtDescripcion.textProperty().bindBidirectional(receta.descripcionProperty());
        listIngredientes.itemsProperty().bindBidirectional(receta.ingredientesProperty());

        listIngredientes.setCellFactory(TextFieldListCell.forListView(new StringConverter<>() {
            @Override
            public String toString(String object) {
                return object;
            }

            @Override
            public String fromString(String string) {
                return string;
            }
        }));

        listIngredientes.setOnEditCommit(event -> {
            int index = event.getIndex();
            listIngredientes.getItems().remove(index);
            listIngredientes.getItems().add(index, event.getNewValue());
        });
    }
    @FXML
    public void remove() {
        if(!listIngredientes.getItems().isEmpty() && !listIngredientes.getSelectionModel().isEmpty()) listIngredientes.getItems().remove(listIngredientes.getSelectionModel().getSelectedIndex());
    }
    @FXML
    public void add() {
        listIngredientes.getItems().add("Nuevo Ingrediente");
    }
    @FXML
    public void export() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Receta");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos XML", "*.xml"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"), "Documents"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            writeToFile(toDocument(receta), file.getAbsolutePath());
        }
    }

    private void writeToFile(Document document, String filePath) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileWriter(filePath));
            transformer.transform(source, result);
        } catch (IOException | TransformerException e) {
            System.out.println("Error " + e);
        }
    }

    public Document toDocument(RecetarioModelo receta) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = document.createElement("Receta");
            document.appendChild(rootElement);

            Element elementNombre = document.createElement("Nombre");
            elementNombre.appendChild(document.createTextNode(receta.nombreProperty().get()));
            rootElement.appendChild(elementNombre);

            Element elementDescripcion = document.createElement("Descripcion");
            elementDescripcion.appendChild(document.createTextNode(receta.descripcionProperty().get()));
            rootElement.appendChild(elementDescripcion);

            Element ingredientesElement = document.createElement("Ingredientes");
            receta.ingredientesProperty().get().forEach(ingrediente -> {
                Element elementIngrediente = document.createElement("Ingrediente");
                elementIngrediente.appendChild(document.createTextNode(ingrediente));
                ingredientesElement.appendChild(elementIngrediente);
            });
            rootElement.appendChild(ingredientesElement);

            return document;

        } catch (Exception e) {
            System.out.println("Error de creación del Document " + e);
            return null;
        }
    }
}
