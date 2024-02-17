
package ut02.practica01.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ut02.practica01.modelo.ContactoModelo;
import ut02.practica01.modelo.AgendaModelo;

public class ContactoVistaController implements Initializable {

    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelefono;
    @FXML
    private ComboBox<String> cmbxTipo;
    @FXML
    private ComboBox<String> cmbxTipoList;
    @FXML
    private ListView<String> listTelefonos;
    
    private AgendaModelo agendaModelo;
    private ContactoModelo contactoModelo;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbxTipoList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int selectedIndex = listTelefonos.getSelectionModel().getSelectedIndex();
            if(selectedIndex < 0 || contactoModelo.tiposProperty().size() <= selectedIndex) return;
            contactoModelo.tiposProperty().remove(selectedIndex);
            contactoModelo.tiposProperty().add(selectedIndex, newValue);
        });
        listTelefonos.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            String selectedTipo = contactoModelo.tiposProperty().get(newValue.intValue());
            cmbxTipoList.getSelectionModel().select(selectedTipo);
        });
    }
    
    public void setAgendaModelo(AgendaModelo agendaModelo){
        this.agendaModelo = agendaModelo;
        this.contactoModelo = agendaModelo.getContactoModelo();
        Bindings.bindBidirectional(txtNombre.textProperty(), contactoModelo.nombreProperty());
        Bindings.bindBidirectional(txtApellidos.textProperty(), contactoModelo.apellidosProperty());
        Bindings.bindContentBidirectional(cmbxTipo.getItems(), agendaModelo.getListaTipos());
        Bindings.bindContentBidirectional(cmbxTipoList.getItems(), agendaModelo.getListaTipos());
        listTelefonos.itemsProperty().bindBidirectional(contactoModelo.telefonosProperty());
    }
    
    @FXML
    private void anyadirContacto(ActionEvent event) {
        agendaModelo.add();
    }
    
    @FXML
    private void abrirTipoVista(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ut02/practica01/vista/TipoVista.fxml"));
            Parent root = loader.load();
            TipoVistaController controller = loader.getController();
            controller.setAgendaModelo(agendaModelo);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            System.out.println("Error al abrir la vista Tipo: " + e);
        }
    }
    
    @FXML
    private void anyadirTelefono(ActionEvent event) {
        contactoModelo.telefonosProperty().add(txtTelefono.getText());
        contactoModelo.tiposProperty().add(cmbxTipo.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void borrar(ActionEvent event) {
        int index = listTelefonos.getSelectionModel().getSelectedIndex();
        if(index < 0 || contactoModelo.telefonosProperty().size() <= index) return;
        contactoModelo.telefonosProperty().remove(index);
        contactoModelo.tiposProperty().remove(index);
    }
}