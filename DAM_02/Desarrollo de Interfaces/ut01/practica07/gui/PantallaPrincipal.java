
package ut01.practica07.gui;

import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;
import ut01.practica07.dto.Cliente;

public class PantallaPrincipal extends javax.swing.JFrame {
    
    private static final String[] columnIdentifiers = {"Nombre","Apellidos","Fecha de Alta","Provincia"};
    private final DialogoCliente dialogoCliente;
    
    public PantallaPrincipal() {
        initComponents();
        iniciarTabla();
        setLocationRelativeTo(null);
        dialogoCliente = new DialogoCliente(this, true);
    }
    
    private void iniciarTabla(){
        DefaultTableModel dtb = new DefaultTableModel();
        dtb.setColumnIdentifiers(columnIdentifiers);
        jTableClientes.setModel(dtb);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuAniadir = new javax.swing.JMenuItem();
        jMenuEliminar = new javax.swing.JMenuItem();
        jMenuModificar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableClientes);

        jMenu1.setText("Cliente");

        jMenuAniadir.setText("Añadir");
        jMenuAniadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAniadirActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuAniadir);

        jMenuEliminar.setText("Eliminar");
        jMenuEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEliminarActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuEliminar);

        jMenuModificar.setText("Modificar");
        jMenuModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuModificarActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuModificar);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuAniadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAniadirActionPerformed
        dialogoCliente.setCliente(new Cliente(null, null, new GregorianCalendar(), "Barcelona"));
        dialogoCliente.leerCliente();
        dialogoCliente.setVisible(true);
        
         if(dialogoCliente.isAccept()){
            DefaultTableModel dtb = (DefaultTableModel) jTableClientes.getModel();
            dtb.addRow(dialogoCliente.getCliente().toArrayString());
         }
    }//GEN-LAST:event_jMenuAniadirActionPerformed

    private void jMenuEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEliminarActionPerformed
        DefaultTableModel dtb = (DefaultTableModel) jTableClientes.getModel();
        dtb.removeRow(jTableClientes.getSelectedRow());
    }//GEN-LAST:event_jMenuEliminarActionPerformed

    private void jMenuModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuModificarActionPerformed
        
        int row = jTableClientes.getSelectedRow();
        
        String nombre = jTableClientes.getValueAt(row, 0).toString();
        String apellidos = jTableClientes.getValueAt(row, 1).toString();
        
        String[] sFechas = jTableClientes.getValueAt(row, 2).toString().split("/");

        int anio = Integer.parseInt(sFechas[0]);
        int mes = Integer.parseInt(sFechas[1]);
        int dia = Integer.parseInt(sFechas[2]);
        
        GregorianCalendar fechaAlta = new GregorianCalendar(anio, mes, dia);
        
        String provincia = jTableClientes.getValueAt(row, 3).toString();
        
        Cliente c = new Cliente(nombre, apellidos, fechaAlta, provincia);
        
        dialogoCliente.setCliente(c);
        
        dialogoCliente.leerCliente();
        
        dialogoCliente.setVisible(true);
        
        if(dialogoCliente.isAccept()){
            DefaultTableModel dtb = (DefaultTableModel) jTableClientes.getModel();
            dtb.removeRow(jTableClientes.getSelectedRow());
            dtb.insertRow(row,dialogoCliente.getCliente().toArrayString());
        }
    }//GEN-LAST:event_jMenuModificarActionPerformed

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
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaPrincipal().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuAniadir;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuEliminar;
    private javax.swing.JMenuItem jMenuModificar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableClientes;
    // End of variables declaration//GEN-END:variables
}
