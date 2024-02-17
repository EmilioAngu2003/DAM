module dam02.accesoadatos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens dam02.accesoadatos to javafx.fxml;
    exports dam02.accesoadatos;
}