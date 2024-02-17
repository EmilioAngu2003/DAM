
package ut02.practica01.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ut02.practica01.base.AgendaSerializable;
import ut02.practica01.modelo.ContactoModelo;
import ut02.practica01.modelo.AgendaModelo;

public class AgendaVistaController implements Initializable {

    
    @FXML
    private TableView<ContactoModelo> tablaContactos;
    @FXML
    private TableColumn<ContactoModelo, Integer> columnaID;
    @FXML
    private TableColumn<ContactoModelo, String> columnaNombre;
    @FXML
    private TableColumn<ContactoModelo, String> columnaApellidos;
    @FXML
    private TableColumn<ContactoModelo, ObservableList<String>> columnaTelefonos;
    @FXML
    private TableColumn<ContactoModelo, Button> columnaEliminar;
    @FXML
    private TableColumn<ContactoModelo, Button> columnaEditar;

    private AgendaModelo agendaModelo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        agendaModelo = new AgendaModelo(0, new ArrayList<>(), Arrays.asList("Personal"));

        columnaID.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().idProperty().get(),cellData.getValue().idProperty()));
        columnaNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        columnaApellidos.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());
        columnaTelefonos.setCellValueFactory(cellData -> {
            cellData.getValue().telefonosProperty().addListener((ListChangeListener.Change<? extends String> change) -> {
                tablaContactos.getColumns().get(3).setVisible(false);
                tablaContactos.getColumns().get(3).setVisible(true);
            });
            return cellData.getValue().telefonosProperty();
        });
        columnaEliminar.setCellValueFactory(cellData -> {
            Button btnEliminar = new Button("Eliminar");
            btnEliminar.setOnAction(event -> agendaModelo.remove(cellData.getValue()));
            return new SimpleObjectProperty<>(btnEliminar);
        });
        columnaEditar.setCellValueFactory(cellData -> {
            Button btnEditar = new Button("Editar");
            btnEditar.setOnAction(event -> {
                abrirContactoVista(cellData.getValue());
            });
            return new SimpleObjectProperty<>(btnEditar);
        });
        tablaContactos.setItems(agendaModelo.getListaContactos());
    }

    @FXML
    private void nuevoContacto(ActionEvent event) {
        abrirContactoVista(new ContactoModelo(agendaModelo.nextID(),"", "", new ArrayList<>(), new ArrayList<>()));
    }
    
    @FXML
    private void guardarAgenda(ActionEvent event) {

        File selectedFile = getFile("Guardar Agenda de Contactos", 1);
        
        try{
            String filePath = selectedFile.getAbsolutePath();
            if (!filePath.endsWith(".dat")) selectedFile = new File(filePath + ".dat");
        } catch(NullPointerException e){
            System.out.println("Error de creadcion de archivo");
        }
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(selectedFile))) {
            AgendaSerializable agendaSerializable = agendaModelo.toSerializable();
            oos.writeObject(agendaSerializable);
        } catch (IOException | NullPointerException e) {
            System.out.println("Error al guardar el archivo " + e);
        }
    }
    
    @FXML
    private void abrirAgenda(ActionEvent event) {
        
        File selectedFile = getFile("Abrir Agenda de Contactos", 2);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selectedFile))) {
            AgendaSerializable agendaSerializable = (AgendaSerializable) ois.readObject();
            agendaModelo = agendaSerializable.toModelo();
            tablaContactos.setItems(agendaModelo.getListaContactos());
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            System.out.println("Error al abrir el archivo " + e);
        }
    }

    private void abrirContactoVista(ContactoModelo contacto) {
        try {
            agendaModelo.setContactoModelo(contacto);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ut02/practica01/vista/ContactoVista.fxml"));
            Parent root = loader.load();
            ContactoVistaController controller = loader.getController();
            controller.setAgendaModelo(agendaModelo);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            System.out.println("Error al abrir la vista Contacto: " + e);
        }
    }
    
    private File getFile(String title, int action){
        try{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle(title);
            fileChooser.setInitialDirectory(new File("src/ut02/practica01/fichero"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos DAT", "*.dat"));
            if(action == 1) return fileChooser.showSaveDialog(null);
            if(action == 2) return fileChooser.showOpenDialog(null);
        }catch(RuntimeException e){
            System.out.println("Error de Interrupcion del Explorador");
        }
        return null;
    }
}
