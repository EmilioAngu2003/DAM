package dam02.accesoadatos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Granjero {

    public Integer id;
    public String nombre;
    public String descripcion;
    public Float dinero;
    public Integer puntos;
    public Integer nivel;

    public Granjero(){
        this.id = null;
        this.nombre = null;
        this.descripcion = null;
        this.dinero = null;
        this.puntos = null;
        this.nivel = null;
    }

    public Granjero(ResultSet resultSet){
        try {
            this.id = resultSet.getInt(1);
            this.nombre = resultSet.getString(2);
            this.descripcion = resultSet.getString(3);
            this.dinero = resultSet.getFloat(4);
            this.puntos = resultSet.getInt(5);
            this.nivel = resultSet.getInt(6);
        }
        catch (NumberFormatException numberFormatException)
        {
            String alert = "Numero no acceptable";
            System.out.println(alert);
        }
        catch (SQLException sqlException)
        {
            String alert = "Campo de texto Vacio";
            System.out.println(alert);
        }
    }

    @Override
    public String toString() {
        return " id = " + id + ", nombre = " + nombre + ", descripcion = " + descripcion + ", dinero = " + dinero + ", puntos = " + puntos + ", nivel = " + nivel;
    }
}
