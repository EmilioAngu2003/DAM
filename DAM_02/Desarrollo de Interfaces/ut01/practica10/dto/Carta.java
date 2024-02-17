
package ut01.practica10.dto;

public class Carta {
    
    private int numero;
    private int color;
    private int simbolo;
    
    public Carta(int numero, int color, int simbolo){
        this.numero = numero;
        this.color = color;
        this.simbolo = simbolo;
    }

    public boolean equals(Carta c){
        if( color != c.getColor()) return false;
        if( simbolo != c.getSimbolo()) return false;
        return numero == c.getNumero();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(int simbolo) {
        this.simbolo = simbolo;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    
    @Override
    public String toString(){
        return numero +"_"+ simbolo+color;
    }
}
