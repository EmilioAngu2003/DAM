
package ut01.practica07.dto;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Cliente {
    
    private String nombre;
    private String apellidos;
    private GregorianCalendar fechaAlta;
    private String provincia;
    
    public Cliente(String nombre, String apellido, GregorianCalendar fechaAlta, String provincia){
        this.nombre = nombre;
        this.apellidos = apellido;
        this.fechaAlta = fechaAlta;
        this.provincia = provincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public GregorianCalendar getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(GregorianCalendar fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
    public Date getDate(){
        return new Date(
                fechaAlta.get(Calendar.YEAR)-1900,
                fechaAlta.get(Calendar.MONTH)-1,
                fechaAlta.get(Calendar.DAY_OF_MONTH)
        );
    }
    
    public String getStringFechaAlta(){
        return  fechaAlta.get(Calendar.YEAR)+"/"+
                fechaAlta.get(Calendar.MONTH)+"/"+
                fechaAlta.get(Calendar.DAY_OF_MONTH);
    }
    
    public String[] toArrayString(){
    
        String[] s = {
            nombre, 
            apellidos, 
            getStringFechaAlta(),
            provincia
        };
        
        return s;
    }
    
    @Override
    public String toString(){
        return nombre+"-"+apellidos+"-"+getStringFechaAlta()+"-"+provincia;
    }
}
