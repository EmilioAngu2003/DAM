module dam.accesoadatos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.commons.annotations;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;

    opens dam02.accesoadatos to org.hibernate.orm.core, javafx.fxml;
    exports dam02.accesoadatos;
}