module com.dam.accesoadatos {
    requires javafx.controls;
    requires javafx.fxml;
    requires jgoodies.forms;
    requires java.desktop;

    opens com.dam2.accesoadatos.ut01.ejercicio04 to javafx.fxml;
    exports com.dam2.accesoadatos.ut01.ejercicio04;
    exports com.dam2.accesoadatos.ut01.ejercicio05;
    exports com.dam2.accesoadatos.ut01.ejercicio06;
}