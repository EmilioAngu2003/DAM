
package ut01.practica06;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;


public class Practica06 extends javax.swing.JFrame {
    
    private static File fichero;
    
    public Practica06() {
        initComponents();
        setSize(500, 500);
        setLocationRelativeTo(null);
        txtArea.setVisible(false);
        fichero = null;
        
    }
    
    private void setFont(){
        
        Font fuente = new Font( listFuente.getSelectedItem().toString(),
                                0,
                                Integer.parseInt(listTamanio.getSelectedItem().toString()));
    
        txtArea.setFont(fuente);  
    }
    
    private String getPath(int mode, String currentDirectoryPath){
        
        JFileChooser explorador = new JFileChooser(currentDirectoryPath);
        
        explorador.setFileSelectionMode(mode);
        
        explorador.showOpenDialog(this);

        return explorador.getSelectedFile().getPath();

        
    }
    
    private String leerFichero(){
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(fichero));
            StringBuilder sb = new StringBuilder();
            
            String linea;
            while( (linea = br.readLine())!=null ){
                sb.append(linea);
                sb.append(System.lineSeparator());
            }
            
            br.close();
            return sb.toString();
            
        } catch (IOException ex) { return null; }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        brrHerramientas = new javax.swing.JToolBar();
        btnNuevo = new javax.swing.JButton();
        btnAbrir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnColorTxt = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        listFuente = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        listTamanio = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        brrHerramientas.setRollover(true);
        brrHerramientas.setPreferredSize(new java.awt.Dimension(500, 25));

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ut01/practica06/img/nuevo1.png"))); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        brrHerramientas.add(btnNuevo);

        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ut01/practica06/img/abrir1.png"))); // NOI18N
        btnAbrir.setFocusable(false);
        btnAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });
        brrHerramientas.add(btnAbrir);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ut01/practica06/img/guardar1.png"))); // NOI18N
        btnGuardar.setFocusable(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        brrHerramientas.add(btnGuardar);

        btnColorTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ut01/practica06/img/color_de_texto1.png"))); // NOI18N
        btnColorTxt.setFocusable(false);
        btnColorTxt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnColorTxt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnColorTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorTxtActionPerformed(evt);
            }
        });
        brrHerramientas.add(btnColorTxt);

        jLabel1.setText("Fuente");
        brrHerramientas.add(jLabel1);

        listFuente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Arial", "Berlin Sans FB", "Cooper Black" }));
        listFuente.setPreferredSize(new java.awt.Dimension(200, 20));
        listFuente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listFuenteActionPerformed(evt);
            }
        });
        brrHerramientas.add(listFuente);

        jLabel2.setText("Tamaño");
        brrHerramientas.add(jLabel2);

        listTamanio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8", "10", "12", "14", "15", "16", "18", "20", "24", "28", "32" }));
        listTamanio.setPreferredSize(new java.awt.Dimension(60, 20));
        listTamanio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listTamanioActionPerformed(evt);
            }
        });
        brrHerramientas.add(listTamanio);

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(brrHerramientas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(brrHerramientas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
        );

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

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        
        try {
            
            String pathname = getPath(JFileChooser.DIRECTORIES_ONLY, "src/ut01/practica06/ficheros") +
                              "\\"+
                              JOptionPane.showInputDialog("Nombre del Archivo de Texto")+".txt";
            fichero = new File(pathname);
            fichero.createNewFile();
            txtArea.setText(null);
            txtArea.setVisible(true);
        } 
        catch (IOException ex) {    System.out.println("Error de creacion del fichero");    }
        
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        
        fichero = new File(getPath(JFileChooser.FILES_ONLY, "src/ut01/practica06/ficheros"));
        txtArea.setText(leerFichero());
        txtArea.setVisible(true);
        
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fichero)));
            pw.write(txtArea.getText());
            pw.flush();
            pw.close();

        } catch (IOException ex ) {
            System.out.println("Error de escritura del fichero");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnColorTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorTxtActionPerformed
        txtArea.setForeground(JColorChooser.showDialog(this,"Color de Texto",Color.BLACK));
    }//GEN-LAST:event_btnColorTxtActionPerformed

    private void listFuenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listFuenteActionPerformed
        setFont();
    }//GEN-LAST:event_listFuenteActionPerformed

    private void listTamanioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listTamanioActionPerformed
        setFont();
    }//GEN-LAST:event_listTamanioActionPerformed

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
            java.util.logging.Logger.getLogger(Practica06.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Practica06.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Practica06.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Practica06.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Practica06().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar brrHerramientas;
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnColorTxt;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> listFuente;
    private javax.swing.JComboBox<String> listTamanio;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}
