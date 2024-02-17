package com.dam2.accesoadatos.ut01.ejercicio05;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

public class IMDBControlador {
    public TextArea txtxSinopsis;
    public TextField txtGenero;
    public TextField txtFecha;
    public TextField txtTitulo;
    public ListView<String> listActores;
    public ListView<Pelicula> listPeliculas;
    private IMDBModelo modelo;
    private DocumentBuilder builderCatalogo;
    private DocumentBuilder builderPelicula;

    @FXML
    public void initialize() {

        modelo = new IMDBModelo();

        builderCatalogo = newDocumentBuilder("catalogo.xsd");
        builderPelicula = newDocumentBuilder("pelicula.xsd");

        listPeliculas.setItems(modelo.getListaPeliculas());
        listPeliculas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> show(newValue));
    }

    private DocumentBuilder newDocumentBuilder(String xsdPath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File(Objects.requireNonNull(getClass().getResource(xsdPath)).toURI())));
            return factory.newDocumentBuilder();
        } catch (URISyntaxException | ParserConfigurationException | SAXException e) {
            System.out.println("Error de Creacion del DocumentBuilder " + xsdPath + " " + e.getMessage());
            return null;
        }
    }

    private void show(Pelicula pelicula) {
        if (pelicula != null) {
            txtTitulo.textProperty().set(pelicula.titulo());
            txtFecha.textProperty().set(pelicula.fecha());
            txtGenero.textProperty().set(pelicula.genero());
            txtxSinopsis.textProperty().set(pelicula.sinopsis());
            listActores.setItems(FXCollections.observableArrayList(pelicula.reparto()));
        } else {
            txtTitulo.clear();
            txtFecha.clear();
            txtGenero.clear();
            txtxSinopsis.clear();
            listActores.setItems(FXCollections.observableArrayList());
        }
    }

    @FXML
    public void importPelicula() {
        try {
            Document documentPelicula = builderPelicula.parse(getFile());
            Pelicula pelicula = buildPelicula(documentPelicula.getDocumentElement());
            modelo.getListaPeliculas().add(pelicula);
        } catch (IOException | SAXException e) {
            System.out.println("Error de validacion de xml pelicula " + e);
        }
    }

    public File getFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Importar XML");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos XML", "*.xml"));
        fileChooser.setInitialDirectory(new File("src/main/resources/com/dam2/accesoadatos/ut01/ejercicio05"));
        return fileChooser.showOpenDialog(null);
    }


    @SuppressWarnings("unchecked")
    private Pelicula buildPelicula(Element peliculaElement) {
        String titulo = (String) getElementValue(peliculaElement, "titulo");
        String fecha = (String) getElementValue(peliculaElement, "fecha");
        String genero = (String) getElementValue(peliculaElement, "genero");
        String sinopsis = (String) getElementValue(peliculaElement, "sinopsis");

        Object repartoObject = getElementValue(peliculaElement, "actor");
        List<String> reparto = (repartoObject instanceof List) ? (List<String>) repartoObject : Collections.singletonList((String) repartoObject);

        return new Pelicula(titulo, fecha, genero, sinopsis, reparto);
    }

    private Object getElementValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);

        if (nodeList.getLength() == 1) {
            return nodeList.item(0).getFirstChild().getNodeValue();
        } else if (nodeList.getLength() > 1) {
            List<String> values = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) values.add(nodeList.item(i).getFirstChild().getNodeValue());
            return values;
        } else {
            return null;
        }
    }

    @FXML
    public void importCatalogo() {
        try {
            Document documentCatalogo = builderCatalogo.parse(getFile());
            List<Pelicula> catalogo = buildCatalogo(documentCatalogo.getDocumentElement());
            modelo.getListaPeliculas().addAll(catalogo);
        } catch (IOException | SAXException e) {
            System.out.println("Error de validacion de xml catalogo " + e);
        }

    }

    private List<Pelicula> buildCatalogo(Element catalogoElement) {
        List<Pelicula> catalogo = new ArrayList<>();
        NodeList nodeList = catalogoElement.getElementsByTagName("pelicula");
        for (int i = 0; i < nodeList.getLength(); i++) catalogo.add(buildPelicula((Element) nodeList.item(i)));
        return catalogo;
    }

}
