
package ut03.propuestos.ejercicio09;

import javax.swing.*;
import java.awt.*;

public class View {

    private final boolean oponente;
    private final DatagramUtils datagram;

    private Integer ANCHO;
    private Integer ALTO;
    private Integer BARCOS;

    public View(boolean oponente, DatagramUtils datagram) {
        this.oponente = oponente;
        this.datagram = datagram;
        interfaz();
    }
    
    private void interfaz(){
        try {
            if(oponente)
            {
                int[] settings = (int[]) datagram.receiveObject();
                ALTO = settings[0];
                ANCHO = settings[1];
                BARCOS = settings[2];
            }
            else
            {
                ANCHO = inputInt("Introduce el ancho del área:");
                ALTO = inputInt("Introduce el alto del área:");
                BARCOS = inputInt("Introduce el numero de barcos por jugador:");
                int[] settings = { ALTO, ANCHO, BARCOS};
                datagram.sendObject(settings);
            }
            SwingUtilities.invokeLater(this::ventana);
        } catch (Exception ex) {
            message("No se pudo iniciar la batalla");
        }
    }
    
    private int inputInt(String text){
        do{
            try{
                String value = JOptionPane.showInputDialog(text);
                int n = Integer.parseInt(value);
                if (ANCHO != null && ALTO != null){
                    if (n < (ANCHO*ALTO) && n > 0){
                        return n;
                    } else {
                        message("Introduce un numero entre 0 y " + (ANCHO*ALTO));
                    }
                } else {
                    if ( n > 0 ) {
                        return n;
                    } else {
                        message("Introduce un numero mayor a 0");
                    }
                }
            }
            catch (NumberFormatException e) 
            {
                message("Introduce un numero valido");
            }
        }while(true);
    }
    
    private JFrame ventana;
    private Container contenedor;
    private JPanel panelOponente;
    private JPanel panelPropio;
    private JPanel panelAtacable;
    private JPanel panelNoAtacable;
    private JLabel labelOponente;
    private JLabel labelPropio;

    private void ventana() {
        ventana = new JFrame("Hundir la Flota");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventana.setUndecorated(true);
        
        panelOponente = new JPanel(new BorderLayout());
        
        labelOponente = new JLabel("Mar del Oponente", SwingConstants.CENTER);
        labelOponente.setBackground(Color.BLUE);
        labelOponente.setForeground(Color.WHITE);
        labelOponente.setOpaque(true);
        panelOponente.add(labelOponente, BorderLayout.NORTH);
        
        panelAtacable = new JPanel(new GridLayout(ALTO, ANCHO));
        panelOponente.add(panelAtacable, BorderLayout.CENTER);

        panelPropio = new JPanel(new BorderLayout());
        
        labelPropio = new JLabel("Mar Propio", SwingConstants.CENTER);
        labelPropio.setForeground(Color.WHITE);
        labelPropio.setBackground(Color.BLUE);
        labelPropio.setOpaque(true);
        panelPropio.add(labelPropio, BorderLayout.NORTH);
        
        panelNoAtacable = new JPanel(new GridLayout(ALTO, ANCHO));
        panelPropio.add(panelNoAtacable, BorderLayout.CENTER);
        
        contenedor = ventana.getContentPane();
        contenedor.setLayout(new GridLayout(2, 1));
        contenedor.add(panelOponente);
        contenedor.add(panelPropio);

        for (int f = 0; f < ALTO; f++) {
            for (int c = 0; c < ANCHO; c++) {
                panelNoAtacable.add(new BotonCustom( true, f, c));
                panelAtacable.add(new BotonCustom( false, f, c));
            }
        }

        ventana.setVisible(true);
        
        MI_TURNO = oponente;
        esperarBarcosUbicados();
        message("¡Bienvenido a Hundir la Flota!\nPrimero, selecciona las posiciones de tus " + BARCOS + " barcos.");
    }
    
    boolean esperando;
    boolean listo;
    
    private void esperarBarcosUbicados(){
        esperando = true;
        listo = false;
        new Thread(() -> {
            try {
                esperando = !(boolean)datagram.receiveObject();
                iniciarJuego();
            } catch (Exception ex) {
                message("No se pudo iniciar la batalla");
                ventana.dispose();
            }
        }).start();
    }
    
    private void barcosUbicados(){
        try {
            listo = true;
            datagram.sendObject(listo);
            iniciarJuego();
        } catch (Exception ex) {
            message("No se pudo iniciar la batalla");
            ventana.dispose();
        }
    }
    
    private void iniciarJuego(){
        if(listo && esperando){
            message("Esperando al otro jugador");
        }
        if(!listo && !esperando){
            message("Oponente listo para iniciar");
        }
        if(listo && !esperando){
            JOptionPane.getRootFrame().dispose();
            MODO_ACTUAL = MODO_JUGAR;
            String turno;
            if(MI_TURNO){
                turno = "Empieza tu turno";
            }else{
                turno = "Espera tu turno";
                recibirAtaque();
            }
            message("El juego ha iniciado, " + turno);
        }
    }
    
    int ataquesRealizados = 0;
    int ataquesRecibidos = 0;

    private void recibirAtaque() {
        new Thread(() -> {
            try {
                int[] coords = (int[]) datagram.receiveObject();
                BotonCustom boton = getBoton(coords[0],coords[1]);
                if(boton.getBackground() == Color.GRAY){
                    ataquesRecibidos++;
                    boton.setBackground(Color.RED);
                    datagram.sendObject(true);
                    if(ataquesRecibidos == BARCOS){
                        message("Perdiste la batalla");
                        ventana.dispose();
                    }else{
                        recibirAtaque();
                    }
                }else{
                    boton.setBackground(Color.LIGHT_GRAY);
                    MI_TURNO = true;
                    datagram.sendObject(false);
                }
                
            } catch (Exception ex) {
                System.out.println("Error Recibir Ataque " + ex);
            }
        }).start();
    }
    
    private boolean MI_TURNO;
    private int MODO_ACTUAL = 0;
    private final int MODO_UBICAR = 0;
    private final int MODO_JUGAR = 1;

    class BotonCustom extends JButton{
        
        static int barcos = 0;
        final int fila;
        final int columna;
        final boolean propio;
        
        BotonCustom(boolean propio, int fila, int columna){
            this.propio = propio;
            this.fila = fila;
            this.columna = columna;
            setBackground(Color.BLUE);
            addActionListener(e -> click());
        }
        
        private void click(){
            if(MODO_ACTUAL == MODO_UBICAR && propio){
                ubicarBarco();
            }else if (MODO_ACTUAL == MODO_JUGAR && !propio){
                atacar();
            }
        }

        private void ubicarBarco() {
            if(barcos < BARCOS && this.getBackground() != Color.GRAY)
            {
                barcos++;
                setBackground(Color.GRAY);
                message("Barco nº " + barcos + " de " + BARCOS + " ubicado en (" + columna + ", " + fila + ")");
                if(barcos == BARCOS)
                {
                    barcosUbicados();
                }
            }
        }

        private void atacar() {
            try {
                if(MI_TURNO){
                    int[] coords = {fila, columna};
                    datagram.sendObject(coords);
                    String ataque;
                    if((boolean) datagram.receiveObject()){
                        ataquesRealizados++;
                        ataque = "Ataque Exitoso";
                        setBackground(Color.RED);
                    } else {
                        setBackground(Color.LIGHT_GRAY);
                        ataque = "Ataque Fallido";
                        MI_TURNO = false;
                        recibirAtaque();
                    }
                    if(ataquesRealizados == BARCOS){
                        message("Ganaste la batalla");
                        ventana.dispose();
                    }else{
                        message("Coordenadas (" + columna + ", " + fila + ") " + ataque);
                    }
                }else{
                    message("Espera tu turno");
                }
            } catch (Exception ex) {
                message("No se pudo iniciar la batalla");
                ventana.dispose();
            }
        }
    }
    
    private BotonCustom getBoton(int fila, int columna){
        for (Component componente : panelNoAtacable.getComponents())
        {
            if (componente instanceof JButton)
            {
                GridLayout layout = (GridLayout) panelNoAtacable.getLayout();
                int c = layout.getColumns();
                int i = panelNoAtacable.getComponentZOrder(componente);

                int filaComponente = i / c;
                int columnaComponente = i % c;

                if (filaComponente == fila && columnaComponente == columna) {
                    return (BotonCustom) componente;
                }
            }
        }
        return null;
    }
    
    private void message(String message){
        JOptionPane.showMessageDialog(null, message);
    }
}
