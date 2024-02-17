package dam02.accesoadatos;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Button btnAnyadir;
    @FXML
    Button btnBuscar;
    @FXML
    Button btnGuardar;
    @FXML
    Button btnCancelar;
    @FXML
    TextField fieldId;
    @FXML
    TextField fieldNombre;
    @FXML
    TextField fieldDescripcion;
    @FXML
    TextField fieldDinero;
    @FXML
    TextField fieldPuntos;
    @FXML
    TextField fieldNivel;
    @FXML
    TableColumn<Granjero, Integer> columnId;
    @FXML
    TableColumn<Granjero, String> columnNombre;
    @FXML
    TableColumn<Granjero, String> columnDescripcion;
    @FXML
    TableColumn<Granjero, Float> columnDinero;
    @FXML
    TableColumn<Granjero, Integer> columnPuntos;
    @FXML
    TableColumn<Granjero, Integer> columnNivel;
    @FXML
    TableColumn<Granjero, Button> columnEditar;
    @FXML
    TableColumn<Granjero, Button> columnEliminar;
    @FXML
    TableView<Granjero> tableView;

    GranjerosService granjerosService = new GranjerosService();
    Granjero filtro = new Granjero();

    @Override
    public void initialize(URL Url, ResourceBundle rb){

        columnId.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getId()));
        columnNombre.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getNombre()));
        columnDescripcion.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getDescripcion()));
        columnDinero.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getDinero()));
        columnPuntos.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getPuntos()));
        columnNivel.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getNivel()));
        columnEliminar.setCellValueFactory(cellData -> {
            Button btnEliminar = new Button("Eliminar");
            btnEliminar.setOnAction(event -> eliminar(cellData.getValue().getId()));
            return new SimpleObjectProperty<>(btnEliminar);
        });
        columnEditar.setCellValueFactory(cellData -> {
            Button btnEditar = new Button("Editar");
            btnEditar.setOnAction(event -> editar(cellData.getValue().getId()));
            return new SimpleObjectProperty<>(btnEditar);
        });

        loadTable(filtro);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void loadTable(Granjero filtro){
        try {
            List listGranjeros = granjerosService.getGranjeros(filtro);
            ObservableList<Granjero> observableList = FXCollections.observableList(listGranjeros);
            tableView.setItems(observableList);
        } catch (Exception e) {
            System.out.println("Error en loadTable | " + e);
        }
    }

    private Granjero buildGranjero() {

        Granjero g = new Granjero();

        try {
            g.setId(Integer.parseInt(fieldId.getText()));
        } catch (NumberFormatException ignored) {}
        try {
            if (!fieldNombre.getText().isEmpty()) g.setNombre(fieldNombre.getText());
        } catch (NullPointerException ignored) {}
        try {
            if (!fieldDescripcion.getText().isEmpty()) g.setDescripcion(fieldDescripcion.getText());
        } catch (NullPointerException ignored) {}
        try {
            g.setDinero(Float.parseFloat(fieldDinero.getText()));
        } catch (NumberFormatException ignored) {}
        try {
            g.setPuntos(Integer.parseInt(fieldPuntos.getText()));
        } catch (NumberFormatException ignored) {}
        try {
            g.setNivel(Integer.parseInt(fieldNivel.getText()));
        } catch (NumberFormatException ignored) {}

        System.out.println("Build Granjero | " + g);

        return g;
    }

    private void fixGranjero(Granjero g){
        if (g.getDinero() == null)  g.setDinero(0);
        if (g.getPuntos() == null)  g.setPuntos(0);
        if (g.getNivel() == null)   g.setNivel(0);
    }

    @FXML
    private void anyadir() {
        Granjero g = buildGranjero();
        fixGranjero(g);
        if(granjerosService.addGranjero(g)){
            loadTable(filtro);
        } else {
            alerta("Error al Añadir", "No se pudo realizar la operción de añadir");
        }
    }

    private void editar(int id) {

        Granjero g = granjerosService.getGranjero(id);

        fieldId.setText(String.valueOf(g.getId()));
        fieldNombre.setText(g.getNombre());
        fieldDescripcion.setText(g.getDescripcion());
        fieldDinero.setText(String.valueOf(g.getDinero()));
        fieldPuntos.setText(String.valueOf(g.getPuntos()));
        fieldNivel.setText(String.valueOf(g.getNivel()));

        editing(true);
    }

    private void eliminar(Integer id) {
        if(granjerosService.removeGranjero(id)){
            loadTable(filtro);
        } else {
            alerta("Error al Elimnar", "No se pudo realizar la operación de eliminar");
        }
    }

    @FXML
    public void guardar() {
        Granjero g = buildGranjero();
        fixGranjero(g);
        if(granjerosService.setGranjero(g)){
            loadTable(filtro);
            editing(false);
        } else{
            alerta("Error al Guardar","No se pudo realizar la operación de guardado");
        }
    }

    @FXML
    public void cancelar() {
        editing(false);
    }

    @FXML
    public void buscar() {
        filtro = buildGranjero();
        loadTable(filtro);
    }

    private void editing(boolean editing){

        fieldId.setEditable(!editing);
        btnAnyadir.setDisable(editing);
        btnBuscar.setDisable(editing);
        btnGuardar.setDisable(!editing);
        btnCancelar.setDisable(!editing);

        if(!editing)
        {
            fieldId.setText(         filtro.getId() != null ?            String.valueOf(filtro.getId()) : "");
            fieldNombre.setText(     filtro.getNombre() != null ?        filtro.getNombre() : "");
            fieldDescripcion.setText(filtro.getDescripcion() != null ?   filtro.getDescripcion() : "");
            fieldDinero.setText(     filtro.getDinero() != null ?        String.valueOf(filtro.getDinero()) : "");
            fieldPuntos.setText(     filtro.getPuntos() != null ?        String.valueOf(filtro.getPuntos()) : "");
            fieldNivel.setText(      filtro.getNivel() != null ?         String.valueOf(filtro.getNivel()) : "");
        }
    }

    private void alerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}