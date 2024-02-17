package com.dam2.accesoadatos.ut01.ejercicio01.gui;

import javax.swing.*;

public class Ventana extends JFrame{
    private JPanel formulario;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JComboBox<String> cbCiclo;
    private JSpinner spnFNacimiento;
    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnPrimero;
    private JButton btnAnterior;
    private JButton btnSiguiente;
    private JButton btnUltimo;
    private JList<String> lista;
    private JButton btnExportar;
    private JButton btnImportar;

    public Ventana(){
        super("Colegio Montessori");

        spnFNacimiento.setModel(new SpinnerDateModel());
        cbCiclo.addItem("Basico");
        cbCiclo.addItem("Medio");
        cbCiclo.addItem("Superior");
        lista.setEnabled(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(435, 240);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(formulario);
        setVisible(true);
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtApellidos() {
        return txtApellidos;
    }

    public JComboBox<String> getCbCiclo() {
        return cbCiclo;
    }

    public JSpinner getSpnFNacimiento() {
        return spnFNacimiento;
    }

    public JButton getBtnNuevo() {
        return btnNuevo;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnPrimero() {
        return btnPrimero;
    }

    public JButton getBtnAnterior() {
        return btnAnterior;
    }

    public JButton getBtnSiguiente() {
        return btnSiguiente;
    }

    public JButton getBtnUltimo() {
        return btnUltimo;
    }

    public JButton getBtnExportar() {
        return btnExportar;
    }

    public JButton getBtnImportar() {
        return btnImportar;
    }

    public JList<String> getLista() {
        return lista;
    }
}
