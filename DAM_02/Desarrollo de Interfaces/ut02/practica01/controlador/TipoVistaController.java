
package ut02.practica01.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.KeyCode;
import ut02.practica01.modelo.AgendaModelo;

public class TipoVistaController implements Initializable {

    @FXML
    private ListView<String> listTiposContacto;
    
    public void setAgendaModelo(AgendaModelo agendaModelo){
        listTiposContacto.itemsProperty().bindBidirectional(agendaModelo.getListaTipos());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listTiposContacto.setEditable(true);
        listTiposContacto.setCellFactory(TextFieldListCell.forListView());
        listTiposContacto.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                listTiposContacto.getItems().add(listTiposContacto.getEditingIndex()+1, "");
                listTiposContacto.getSelectionModel().selectNext();
            } else if (event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.BACK_SPACE) {
                if (listTiposContacto.getSelectionModel().getSelectedItem() != null) {
                    String selectedItem = listTiposContacto.getSelectionModel().getSelectedItem();
                    if (selectedItem.isEmpty()) {
                        listTiposContacto.getItems().remove(selectedItem);
                    }
                }
            }
        });
    }

}
