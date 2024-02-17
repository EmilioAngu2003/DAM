package dam02.accesoadatos;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
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
    Connection connection;

    @Override
    public void initialize(URL Url, ResourceBundle rb){

        String url = "jdbc:mysql://localhost:3306/farmville";
        String user = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Exception " + e);
        }

        columnId.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().id));
        columnNombre.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().nombre));
        columnDescripcion.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().descripcion));
        columnDinero.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().dinero));
        columnPuntos.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().puntos));
        columnNivel.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().nivel));
        columnEliminar.setCellValueFactory(cellData -> {
            Button btnEliminar = new Button("Eliminar");
            btnEliminar.setOnAction(event -> eliminar(cellData.getValue().id));
            return new SimpleObjectProperty<>(btnEliminar);
        });
        columnEditar.setCellValueFactory(cellData -> {
            Button btnEditar = new Button("Editar");
            btnEditar.setOnAction(event -> editar(cellData.getValue().id));
            return new SimpleObjectProperty<>(btnEditar);
        });

        querySelect();
    }

    private void querySelect(){

        String sql = "SELECT * FROM granjeros WHERE 1=1";
        Granjero g = buildGranjero();

        if(g.id != null) sql += " AND id = ?";
        if(g.nombre != null) sql += " AND nombre = ?";
        if(g.descripcion != null) sql += " AND descripcion = ?";
        if(g.dinero != null) sql += " AND dinero = ?";
        if(g.puntos != null) sql += " AND puntos = ?";
        if(g.nivel != null) sql += " AND nivel = ?";

        try(
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            int order = 1;

            if(g.id != null) preparedStatement.setInt(order++, g.id);
            if(g.nombre != null) preparedStatement.setString(order++, g.nombre);
            if(g.descripcion != null) preparedStatement.setString(order++, g.descripcion);
            if(g.dinero != null) preparedStatement.setFloat(order++, g.dinero);
            if(g.puntos != null) preparedStatement.setInt(order++, g.puntos);
            if(g.nivel != null) preparedStatement.setInt(order, g.nivel);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Granjero> ListGranjeros = new ArrayList<>();
            while(resultSet.next())
            {
                ListGranjeros.add(new Granjero(resultSet));
            }
            ObservableList<Granjero> observableList = FXCollections.observableList(ListGranjeros);
            tableView.setItems(observableList);
            resultSet.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error SELECT " + e);
        }
    }

    private Granjero buildGranjero(){

        Granjero g = new Granjero();

        try {
            g.id = Integer.parseInt(fieldId.getText());
        } catch (NumberFormatException ignored){}
        if (!fieldNombre.getText().isEmpty()) {
            g.nombre = fieldNombre.getText();
        }
        if (!fieldDescripcion.getText().isEmpty()) {
            g.descripcion = fieldDescripcion.getText();
        }
        try {
            g.dinero = Float.parseFloat(fieldDinero.getText());
        } catch (NumberFormatException ignored){}
        try {
            g.puntos = Integer.parseInt(fieldPuntos.getText());
        } catch (NumberFormatException ignored){}
        try {
            g.nivel = Integer.parseInt(fieldNivel.getText());
        } catch (NumberFormatException ignored){}

        System.out.println("Build Granjero | " + g);

        return g;
    }

    @FXML
    public void anyadir() {

        String sql = "INSERT INTO granjeros (nombre, descripcion, dinero, puntos, nivel) VALUES (?, ?, ?, ?, ?)";

        try(
            PreparedStatement prepareStatement = connection.prepareStatement(sql)
        ) {
            Granjero g = buildGranjero();

            prepareStatement.setString(1,g.nombre);
            prepareStatement.setString(2,g.descripcion);
            prepareStatement.setFloat(3,g.dinero);
            prepareStatement.setInt(4,g.puntos);
            prepareStatement.setInt(5,g.nivel);
            prepareStatement.executeUpdate();

            editing(false);
            querySelect();
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error INSERT " + sqlException);
        }
        catch (NullPointerException nullPointerException)
        {
            System.out.println("Error INSERT null " + nullPointerException);
        }
    }

    private void editar(Integer id) {

        String sql = "SELECT * FROM granjeros WHERE id = ?";

        try(
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
            {
                fieldId.setText(rs.getString(1));
                fieldNombre.setText(rs.getString(2));
                fieldDescripcion.setText(rs.getString(3));
                fieldDinero.setText(rs.getString(4));
                fieldPuntos.setText(rs.getString(5));
                fieldNivel.setText(rs.getString(6));
            }
            editing(true);
            rs.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error SELECT editar " + e);
        }
    }

    private void eliminar(Integer id) {

        String sql = "DELETE FROM granjeros WHERE id = ?";

        try(
            PreparedStatement prepareStatement = connection.prepareStatement(sql)
        ) {
            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();

            querySelect();
        }
        catch (SQLException e)
        {
            System.out.println("Error DELETE " + e);
        }

    }

    @FXML
    public void guardar() {

        String sql = "UPDATE granjeros SET nombre = ?, descripcion = ?, dinero = ?, puntos = ?, nivel = ? WHERE id = ?";

        try (
            PreparedStatement prepareStatement = connection.prepareStatement(sql)
        ) {

            Granjero g = buildGranjero();

            prepareStatement.setString(1,g.nombre);
            prepareStatement.setString(2,g.descripcion);
            prepareStatement.setFloat(3,g.dinero);
            prepareStatement.setInt(4,g.puntos);
            prepareStatement.setInt(5,g.nivel);
            prepareStatement.setInt(6,g.id);

            prepareStatement.executeUpdate();

            editing(false);

            querySelect();

        } catch (SQLException e) {
            System.out.println("Error UPDATE " + e);
        }
    }

    @FXML
    public void cancelar() {
        editing(false);
    }

    @FXML
    public void buscar() {
        querySelect();
    }

    private void editing(boolean editing){

        fieldId.setEditable(!editing);
        btnAnyadir.setDisable(editing);
        btnBuscar.setDisable(editing);
        btnGuardar.setDisable(!editing);
        btnCancelar.setDisable(!editing);

        if(!editing)
        {
            fieldId.setText("");
            fieldNombre.setText("");
            fieldDescripcion.setText("");
            fieldDinero.setText("");
            fieldPuntos.setText("");
            fieldNivel.setText("");
        }
    }
}