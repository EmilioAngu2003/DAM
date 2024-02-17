
package ut01.practica04;

public class casa extends javax.swing.JFrame {

    private int habitacion1, 
                salon,
                cocina,
                banio,
                garaje,
                trastero;

    private String texto;
    
    public casa() {
        initComponents();
        salon = 0;
        texto = "";
        setLocationRelativeTo(null);
    }
    
    public void anotar(String linea){
    
        texto += linea + "\n";
        
        txtPanel.setText(texto);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPanel = new javax.swing.JTextPane();
        mnb = new javax.swing.JMenuBar();
        mnuCasa = new javax.swing.JMenu();
        mnuHabitaciones = new javax.swing.JMenu();
        mniHabitacion1 = new javax.swing.JMenuItem();
        mniHabitacion2 = new javax.swing.JMenuItem();
        mniCheckBoxSalon = new javax.swing.JCheckBoxMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mniRadioButtonCocina = new javax.swing.JRadioButtonMenuItem();
        mniRadioButtonBanio = new javax.swing.JRadioButtonMenuItem();
        mnuExtras = new javax.swing.JMenu();
        mniGaraje = new javax.swing.JMenuItem();
        mniTrastero = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(txtPanel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnuCasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ut01/practica04/img/casa4.png"))); // NOI18N
        mnuCasa.setText("Casa");

        mnuHabitaciones.setText("Habitaciones");

        mniHabitacion1.setText("Habitacion 1");
        mniHabitacion1.setSelected(true);
        mniHabitacion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniHabitacion1ActionPerformed(evt);
            }
        });
        mnuHabitaciones.add(mniHabitacion1);

        mniHabitacion2.setText("Habitacion 2");
        mniHabitacion2.setEnabled(false);
        mnuHabitaciones.add(mniHabitacion2);

        mnuCasa.add(mnuHabitaciones);

        mniCheckBoxSalon.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniCheckBoxSalon.setSelected(true);
        mniCheckBoxSalon.setText("Salón");
        mniCheckBoxSalon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCheckBoxSalonActionPerformed(evt);
            }
        });
        mnuCasa.add(mniCheckBoxSalon);
        mnuCasa.add(jSeparator1);

        mniRadioButtonCocina.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniRadioButtonCocina.setSelected(true);
        mniRadioButtonCocina.setText("Cocina");
        mniRadioButtonCocina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRadioButtonCocinaActionPerformed(evt);
            }
        });
        mnuCasa.add(mniRadioButtonCocina);

        mniRadioButtonBanio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniRadioButtonBanio.setText("Baño");
        mniRadioButtonBanio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRadioButtonBanioActionPerformed(evt);
            }
        });
        mnuCasa.add(mniRadioButtonBanio);

        mnb.add(mnuCasa);

        mnuExtras.setText("Extras");

        mniGaraje.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_DOWN_MASK));
        mniGaraje.setText("Garaje");
        mniGaraje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGarajeActionPerformed(evt);
            }
        });
        mnuExtras.add(mniGaraje);

        mniTrastero.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_DOWN_MASK));
        mniTrastero.setText("Trastero");
        mniTrastero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTrasteroActionPerformed(evt);
            }
        });
        mnuExtras.add(mniTrastero);

        mnb.add(mnuExtras);

        setJMenuBar(mnb);

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

    private void mniCheckBoxSalonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCheckBoxSalonActionPerformed
        
        salon++;
        
        String linea =  "Entraste al salon " + salon + 
                        ( salon == 1 ? " vez.": " veces.") + 
                        " Estado " + 
                        ( mniCheckBoxSalon.isSelected() ?" activado.":" desactivado.");
        
        anotar(linea);
    }//GEN-LAST:event_mniCheckBoxSalonActionPerformed

    private void mniRadioButtonCocinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRadioButtonCocinaActionPerformed
        
        cocina++;
        
        String linea =  "Entraste a la cocina " + cocina + 
                        ( cocina == 1 ? " vez.": " veces.") + 
                        " Estado " + 
                        ( mniRadioButtonCocina.isSelected() ?" activado.":" desactivado.");
        
        anotar(linea);
    }//GEN-LAST:event_mniRadioButtonCocinaActionPerformed

    private void mniRadioButtonBanioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRadioButtonBanioActionPerformed
        
        banio++;
        
        String linea =  "Entraste al baño " + banio + 
                        ( banio == 1 ? " vez.": " veces.") + 
                        " Estado " + 
                        ( mniRadioButtonBanio.isSelected() ?" activado.":" desactivado.");
        
        anotar(linea);
    }//GEN-LAST:event_mniRadioButtonBanioActionPerformed

    private void mniHabitacion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniHabitacion1ActionPerformed
        
        habitacion1++;
        
        String linea =  "Entraste a la habitacion " + habitacion1 + ( habitacion1 == 1 ? " vez.": " veces.");
        
        anotar(linea);
    }//GEN-LAST:event_mniHabitacion1ActionPerformed

    private void mniGarajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGarajeActionPerformed
        
        garaje++;
        
        String linea =  "Entraste al garaje " + garaje + ( garaje == 1 ? " vez.": " veces.");
        
        anotar(linea);
    }//GEN-LAST:event_mniGarajeActionPerformed

    private void mniTrasteroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTrasteroActionPerformed
        
        trastero++;
        
        String linea =  "Entraste al trastero " + trastero + ( trastero == 1 ? " vez.": " veces.");
        
        anotar(linea);
    }//GEN-LAST:event_mniTrasteroActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(casa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(casa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(casa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(casa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new casa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuBar mnb;
    private javax.swing.JCheckBoxMenuItem mniCheckBoxSalon;
    private javax.swing.JMenuItem mniGaraje;
    private javax.swing.JMenuItem mniHabitacion1;
    private javax.swing.JMenuItem mniHabitacion2;
    private javax.swing.JRadioButtonMenuItem mniRadioButtonBanio;
    private javax.swing.JRadioButtonMenuItem mniRadioButtonCocina;
    private javax.swing.JMenuItem mniTrastero;
    private javax.swing.JMenu mnuCasa;
    private javax.swing.JMenu mnuExtras;
    private javax.swing.JMenu mnuHabitaciones;
    private javax.swing.JTextPane txtPanel;
    // End of variables declaration//GEN-END:variables
}
