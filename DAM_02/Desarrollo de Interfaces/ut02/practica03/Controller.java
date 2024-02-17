
package ut02.practica03;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller implements Initializable {

    @FXML
    private TextField NombreTxt;
    @FXML
    private TextField PaisTxt;
    @FXML
    private TextField CiudadTxt;
    @FXML
    private TextField Calletxt;
    @FXML
    private TextField NumeroTxt;
    @FXML
    private TextField AnyoTxt;
    @FXML
    private TextField CapacidadTxt;
    @FXML
    private RadioButton isPublico;
    @FXML
    private RadioButton isPrivado;
    @FXML
    private TextField FinanciacionTxt;
    @FXML
    private TextField TrabDiscapacitadosTxt;
    @FXML
    private TextField NumSociosTxt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void Guardar(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos de Texto", "*.txt"));
        try{
            File selectedFile = fileChooser.showSaveDialog(null);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile))) {
                bw.write(getInfo());
            }
        }catch(NullPointerException | IOException e){
            System.out.println("Exception: " + e);
        }
    }

    @FXML
    private void Cancelar(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close(); 
    }
    
    private String getInfo(){
        return  "Nombre:"               + NombreTxt.getText()+"\n"+
                "Pais:"                 + PaisTxt.getText()+"\n"+
                "Ciudad:"               + CiudadTxt.getText()+"\n"+
                "Calle:"                + Calletxt.getText()+"\n"+
                "Numero:"               + NumeroTxt.getText()+"\n"+
                "Año Inauguracion:"     + AnyoTxt.getText()+"\n"+
                "Capacidad:"            + CapacidadTxt.getText()+"\n"+
                "Publico:"              + isPublico.isSelected()+"\n"+
                "Financiacion:"         + FinanciacionTxt.getText()+"\n"+
                "Trab. Discapacitados:" + TrabDiscapacitadosTxt.getText()+"\n"+
                "Privado:"              + isPrivado.isSelected()+"\n"+
                "Num. Socios:"          + NumSociosTxt.getText();
    }
    
}
