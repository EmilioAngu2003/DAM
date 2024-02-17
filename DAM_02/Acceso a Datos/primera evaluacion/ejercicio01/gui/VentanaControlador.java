package com.dam2.accesoadatos.ut01.ejercicio01.gui;

import com.dam2.accesoadatos.ut01.ejercicio01.base.Alumno;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class VentanaControlador implements ActionListener {

    private final VentanaModelo model;
    private final Ventana ventana;
    private final DefaultListModel<String> listModel;

    public VentanaControlador(VentanaModelo model, Ventana ventana) {
        this.model = model;
        this.ventana = ventana;
        this.listModel = new DefaultListModel<>();
        this.ventana.getLista().setModel(listModel);
        anadirActionListener(this);
    }

    private void anadirActionListener(ActionListener listener) {
        ventana.getBtnNuevo().addActionListener(listener);
        ventana.getBtnGuardar().addActionListener(listener);
        ventana.getBtnPrimero().addActionListener(listener);
        ventana.getBtnAnterior().addActionListener(listener);
        ventana.getBtnSiguiente().addActionListener(listener);
        ventana.getBtnUltimo().addActionListener(listener);
        ventana.getBtnImportar().addActionListener(listener);
        ventana.getBtnExportar().addActionListener(listener);
    }

    private Alumno getAlumnoFormulario() {
        Alumno alumno = new Alumno();
        alumno.setNombre(ventana.getTxtNombre().getText());
        alumno.setApellidos(ventana.getTxtApellidos().getText());
        alumno.setFechaNacimiento(localDateFrom((Date) ventana.getSpnFNacimiento().getValue()));
        alumno.setCiclo(Objects.requireNonNull(ventana.getCbCiclo().getSelectedItem()).toString());
        return alumno;
    }

    private void setAlumnoFormulario(Alumno alumno) {
        ventana.getTxtNombre().setText(alumno.getNombre());
        ventana.getTxtApellidos().setText(alumno.getApellidos());
        ventana.getSpnFNacimiento().setValue(dateFrom(alumno.getFechaNacimiento()));
        ventana.getCbCiclo().setSelectedItem(String.valueOf(alumno.getCiclo()));
    }

    private Date dateFrom(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private LocalDate localDateFrom(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Nuevo":
                nuevo();
                break;
            case "Guardar":
                guardar();
                break;
            case "<<":
                model.primerIndice();
                break;
            case "<":
                model.anteriorIndice();
                break;
            case ">":
                model.siguienteIndice();
                break;
            case ">>":
                model.ultimoIndice();
                break;
            case "Importar":
                importar();
                break;
            case "Exportar":
                exportar();
                break;
            default:
                break;
        }

        try{
            ventana.getLista().setSelectedIndex(model.getPosicion());
            setAlumnoFormulario(model.getAlumno());
        }catch(IndexOutOfBoundsException e) {
            System.out.println("Exception: "+e);
        }
    }

    private boolean campoVacio(){
        return cadenaVacia(ventana.getTxtNombre().getText()) || cadenaVacia(ventana.getTxtApellidos().getText());
    }

    private boolean cadenaVacia(String cadena) {
        return cadena == null || cadena.trim().isEmpty();
    }

    private void mensajeError(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, " Mensaje de Error", JOptionPane.ERROR_MESSAGE);
    }

    private void nuevo() {
        if (campoVacio()) {
            mensajeError("Los campos son obligatorios");
            return;
        }
        Alumno alumno = getAlumnoFormulario();
        listModel.addElement(alumno.toString());
        model.nuevo(alumno);
    }

    private void guardar() {
        if (campoVacio()) {
            mensajeError("Los campos son obligatorios");
            return;
        }
        Alumno alumno = getAlumnoFormulario();
        listModel.set(model.getPosicion(), alumno.toString());
        model.guardar(alumno);
    }

    private void exportar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Objects.requireNonNull(getPath("Exportar archivo"))))) {
            oos.writeObject(model.getListaAlumnos());
        } catch (IOException | NullPointerException e) {
            mensajeError("Exportacion no realizada");
        }
    }

    private void importar() {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Objects.requireNonNull(getPath("Importar archivo"))))) {
            ArrayList<Alumno> lista = verificarObjeto(ois.readObject());
            model.getListaAlumnos().clear();
            listModel.clear();
            for (Alumno a : lista) {
                listModel.addElement(a.toString());
                model.nuevo(a);
            }
        } catch (IOException | ClassNotFoundException | ClassCastException | NullPointerException e) {
            mensajeError("Importacion no realizada");
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Alumno> verificarObjeto(Object objeto) {
        if (objeto instanceof ArrayList<?> lista) {
            for (Object elemento : lista) if (!(elemento instanceof Alumno)) throw new ClassCastException();
            return (ArrayList<Alumno>) lista;
        } else {
            throw new ClassCastException();
        }
    }

    private Path getPath(String dialogTitle) {
        JFileChooser fileChooser = new JFileChooser("src/main/java/com/dam2/accesoadatos/ut01/ejercicio01/ficheros");

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos (.dat)", "dat"));
        fileChooser.setDialogTitle(dialogTitle);
        fileChooser.setAcceptAllFileFilterUsed(false);

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            Path selectedPath = fileChooser.getSelectedFile().toPath();
            if (!selectedPath.toString().endsWith(".dat")) selectedPath = Paths.get(selectedPath + ".dat");
            return selectedPath;
        }
        return null;
    }
}
