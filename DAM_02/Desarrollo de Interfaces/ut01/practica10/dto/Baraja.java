
package ut01.practica10.dto;

import java.util.ArrayList;
import java.util.Collections;

public class Baraja {
    
    public static final int NUMERO = 1,
                            COLOR = 2,
                            SIMBOLO = 3;
   
    private static int parejaPor = NUMERO;
    
    private static ArrayList<Carta> cartas;
    
    public Baraja(int mano){
        cartas = new ArrayList<>();
        for(int i=0; i<(mano/2); i++) Baraja.cartas.add(tomarCarta() );
        verMano();
        tomarParejas(); 
        verMano();
        mezclar(); 
    }
    
    private void verMano(){
        System.out.print("CARTAS:  ");
        for(Carta c:cartas) System.out.print(c.toString()+" | ");
        System.out.println("");
    }
    
    private static Carta tomarCarta(){
        
        int numero = (int) (Math.random()* 13);
        int color = (int) (Math.random()* 2);
        int simbolo = (int) (Math.random()* 2);
        
        return new Carta(numero, color, simbolo);
    }
    
    private static Carta emparejar(Carta c1, Carta c2){
        
        if( Baraja.parejaPor == Baraja.NUMERO ) c2.setNumero(c1.getNumero());
        if( Baraja.parejaPor == Baraja.COLOR ) c2.setColor(c1.getColor());
        if( Baraja.parejaPor == Baraja.SIMBOLO ) {
            c2.setColor(c1.getColor());
            c2.setSimbolo(c1.getSimbolo());
        } 
        
        return c2;
    }

    private static Carta tomarPareja(Carta c){
        Carta carta;
        do{ carta = Baraja.emparejar(c, tomarCarta()); }
        while( c.equals(carta) );
        return carta;
    }
    
    private void tomarParejas(){
        ArrayList<Carta> parejas = new ArrayList<>();
        for(Carta c:Baraja.cartas) parejas.add(tomarPareja( c) );
        Baraja.cartas.addAll(parejas);
    }
    
    private void mezclar(){
        Collections.shuffle(Baraja.cartas);
    }
        
    public static boolean sonPareja(Carta c1, Carta c2){
        
        if( Baraja.parejaPor == Baraja.NUMERO ) return c1.getNumero() == c2.getNumero();
        if( Baraja.parejaPor == Baraja.COLOR ) return c1.getColor() == c2.getColor();
        if( Baraja.parejaPor == Baraja.SIMBOLO ) return c1.getSimbolo() == c2.getSimbolo() && c1.getColor() == c2.getColor();
        
        return false;
    }
    
    public ArrayList<Carta> getCartas() {
        return Baraja.cartas;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        Baraja.cartas = cartas;
    }
    
    public static void setParejasPor( int por){
        Baraja.parejaPor = por;
    }
    
    public static int getParejasPor(){
        return Baraja.parejaPor;
    }
}
