module ut02.practica03 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ut02.practica03 to javafx.fxml;
    exports ut02.practica03;
}
