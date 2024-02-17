
package ut01.practica10.gui;

import java.util.ArrayList;
import ut01.practica10.dto.Baraja;

public class VistaBaraja extends javax.swing.JFrame{
    
    private static Thread thread;
    private static int intentos;
    private final Baraja baraja;
    private final ArrayList<VistaCarta> vistaCartas;
    private static VistaCarta anterior;
    
    public VistaBaraja(int mano) {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        thread = new Thread();
        baraja = new Baraja(mano);
        System.out.println("N Cartas:  "+baraja.getCartas().size());
        vistaCartas = new ArrayList<>();
        intentos = 0;
        anterior = null;
        agregarCartas();
    }
    
    private void agregarCartas(){
        for(int i=0; i<baraja.getCartas().size(); i++) {
            vistaCartas.add(new VistaCarta( baraja.getCartas().get(i)));
            vistaCartas.get(i).addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                     for(int i=0; i<vistaCartas.size(); i++) if( evt.getSource() == vistaCartas.get(i) ) startThread(vistaCartas.get(i));
                }
            });
            jPanel1.add(vistaCartas.get(i));
        }
    }
    
    private void startThread(VistaCarta vs){
        try {
            thread.join();
            if(vs.getReverse().isVisible()){
                thread = new Thread(() -> { selected(vs); });
                thread.start();
            }
        } catch (InterruptedException ex ) {
            System.out.println("Interrupcion de hilo");
        }
    }
    
    public void selected(VistaCarta vs){
        vs.voltear();
        if(anterior == null){
            anterior = vs;
        }else{
            if(!Baraja.sonPareja(anterior.getCarta(), vs.getCarta())){
                try { Thread.sleep(1000); } 
                catch (InterruptedException ex) { System.out.println("Interrupcion de espera"); }
                anterior.voltear(); 
                vs.voltear();
            }
            intentos++;
            System.out.println("Intento "+intentos+":  "+anterior.getCarta().toString()+" | "+vs.getCarta().toString());
            anterior = null;
            verificarFinal();
        }
    }
   
    public void verificarFinal(){
        
        boolean fin = true;
        for(int i=0; i<vistaCartas.size() && fin; i++) if(vistaCartas.get(i).getReverse().isVisible()) fin = false;
        if(fin){
            dispose();
            javax.swing.JOptionPane.showMessageDialog(null,"Felicidades!!! Conseguiste ganar en " + intentos + " intentos.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setLayout(new java.awt.GridLayout(4, 12, 1, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
