
package ut02.practica02;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class Controller implements Initializable {

    private final List<String> notas = List.of("DO", "RE", "MI", "FA", "SOL", "LA", "SI");
    private final List<String> alteraciones = List.of("b", "", "#");
    private final List<String> octavas = List.of("CUARTA", "TERCERA", "SEGUNDA", "PRIMERA");
    
    @FXML
    private TextField TextField;
    @FXML
    private Slider SliderNota;
    @FXML
    private ScrollBar ScrollBarAlteracion;
    @FXML
    private Spinner<String> SpinnerOctava;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(
                FXCollections.observableArrayList(octavas)
        );
        valueFactory.setValue(octavas.get(octavas.size()-1));
        SpinnerOctava.setValueFactory(valueFactory);
        SpinnerOctava.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            TextField.setText(getNota());
        });
        TextField.setText(getNota());
    }    

    @FXML
    private void updateNota(MouseEvent event) {
        TextField.setText(getNota());
    }

    @FXML
    private void updateAlteracion(ScrollEvent event) {
        TextField.setText(getNota());
    }
    
    private String getNota(){
        
        int iNota = (int)SliderNota.getValue();
        int iAlteracion = (int)ScrollBarAlteracion.getValue();
        
        return notas.get(iNota) + alteraciones.get(iAlteracion) + " " + SpinnerOctava.getValue() + " OCTAVA";
    }
}
