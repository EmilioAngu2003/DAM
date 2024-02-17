
package ut01.practica01;

public class fraVentana extends javax.swing.JFrame {
    
    private final java.awt.Toolkit tk;
    private final java.awt.Dimension dm;
    private int xMax;
    private int yMax;
    private String tamanio;
    private String posicion;
    
    public fraVentana() {
        
        initComponents();
        
        tk = java.awt.Toolkit.getDefaultToolkit();
        dm = tk.getScreenSize();
        
        posicion = "Centro";
        tamanio = "300x300";
        renameTitle();
        
        setBounds(400, 200, 300, 300);
        setMinimumSize(new java.awt.Dimension(100,100));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnArribaIzq = new javax.swing.JButton();
        btnArribaDer = new javax.swing.JButton();
        btnAbajoDer = new javax.swing.JButton();
        btnAbajoIzq = new javax.swing.JButton();
        tglDimension = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnArribaIzq.setText("Arriba Izquierda");
        btnArribaIzq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArribaIzqActionPerformed(evt);
            }
        });

        btnArribaDer.setText("Arriba Derecha");
        btnArribaDer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArribaDerActionPerformed(evt);
            }
        });

        btnAbajoDer.setText("Abajo Derecha");
        btnAbajoDer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbajoDerActionPerformed(evt);
            }
        });

        btnAbajoIzq.setText("Abajo Izquierda");
        btnAbajoIzq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbajoIzqActionPerformed(evt);
            }
        });

        tglDimension.setText("Aumenta Dimension");
        tglDimension.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglDimensionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnArribaIzq)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                .addComponent(btnArribaDer))
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnAbajoIzq)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAbajoDer))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tglDimension)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnArribaIzq)
                    .addComponent(btnArribaDer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addComponent(tglDimension)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbajoDer)
                    .addComponent(btnAbajoIzq)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnArribaIzqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArribaIzqActionPerformed
        setLocation(0,0);
        posicion = "Arriba Izquierda";
        renameTitle();
    }//GEN-LAST:event_btnArribaIzqActionPerformed

    private void btnArribaDerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArribaDerActionPerformed
        coordMax();
        setLocation( xMax,0);
        posicion = "Arriba Derecha";
        renameTitle();
    }//GEN-LAST:event_btnArribaDerActionPerformed

    private void tglDimensionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglDimensionActionPerformed
        if( tglDimension.isSelected()){
            setSize(600,600);
            tglDimension.setText("Disminuye Dimension");
            tamanio = "600x600";
        } else{ 
            setSize(300,300);
            tglDimension.setText("Aumenta Dimension");
            tamanio = "300x300";
        }
        renameTitle();
    }//GEN-LAST:event_tglDimensionActionPerformed

    private void btnAbajoIzqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbajoIzqActionPerformed
        coordMax();
        setLocation(0, yMax);
        posicion = "Abajo Izquierda";
        renameTitle();
    }//GEN-LAST:event_btnAbajoIzqActionPerformed

    private void btnAbajoDerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbajoDerActionPerformed
        coordMax();
        setLocation(xMax, yMax);
        posicion = "Abajo Derecha";
        renameTitle();
    }//GEN-LAST:event_btnAbajoDerActionPerformed

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
            java.util.logging.Logger.getLogger(fraVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fraVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fraVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fraVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fraVentana().setVisible(true);
            }
        });
    }
    
    private void coordMax(){
        xMax = (int)dm.getWidth()-getWidth(); 
        yMax = (int)dm.getHeight()-getHeight();
    }
    
    private void renameTitle(){
        setTitle(posicion +" - "+ tamanio);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbajoDer;
    private javax.swing.JButton btnAbajoIzq;
    private javax.swing.JButton btnArribaDer;
    private javax.swing.JButton btnArribaIzq;
    private javax.swing.JToggleButton tglDimension;
    // End of variables declaration//GEN-END:variables
}
