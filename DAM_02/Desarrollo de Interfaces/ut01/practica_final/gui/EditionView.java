package ut01.practica_final.gui;

import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ut01.practica_final.dto.ComponentManager;
import ut01.practica_final.dto.Grid;

public class EditionView extends JDialog {

    private final Grid grid;                // Cuadrícula principal
    private final ComponentManager cm;      // Manejador de componentes
    
    private final int PANEL_WIDTH = 400, PANEL_HEIGHT = 35;
    private ArrayList<Integer> rows;
    private ArrayList<Integer> columns;
    private int currentMatch;

    // Componentes de la interfaz
    private JTextField searchField, numberField;
    private JComboBox<String> rowColumnComboBox, addRemoveComboBox;

    public EditionView(MainView mainView, Grid grid, ComponentManager cm) {
        super(mainView, "Edición", true);
        
        setSize(PANEL_WIDTH, PANEL_HEIGHT * 5);
        setLocationRelativeTo(mainView);
        setResizable(false);
        
        this.grid = grid;
        this.cm = cm;
        
        initComponents();
        setVisible(true);
    }

    private void initComponents() {

        searchField = new JTextField(24);
        numberField = new JTextField(5);
        rowColumnComboBox = new JComboBox<>(new String[]{"Filas", "Columnas"});
        addRemoveComboBox = new JComboBox<>(new String[]{"Añadir", "Quitar"});

        cm.setContainer(this)
            .addAndReplace(new JPanel(new FlowLayout())).setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT)
            .add(searchField)
            .add(new JButton("Buscar")).event(e -> searchAction())
            .setContainer(this)
            .addAndReplace(new JPanel(new FlowLayout())).setBounds(0, PANEL_HEIGHT, PANEL_WIDTH, PANEL_HEIGHT)
            .add(new JButton("Anterior")).event(e -> previousNextAction(currentMatch - 1))
            .add(new JButton("Siguiente")).event(e -> previousNextAction(currentMatch + 1))
            .setContainer(this)
            .addAndReplace(new JPanel(new FlowLayout())).setBounds(0, PANEL_HEIGHT * 2, PANEL_WIDTH, PANEL_HEIGHT)
            .add(numberField)
            .add(rowColumnComboBox)
            .add(addRemoveComboBox)
            .add(new JButton("Dimensionar")).event(e -> resizeAction())
            .setContainer(this)
            .addAndReplace(new JPanel(new FlowLayout())).setBounds(0, PANEL_HEIGHT * 3, PANEL_WIDTH, PANEL_HEIGHT)
            .add(new JButton("Ordenar")).event(e -> grid.sort())
            .setContainer(this)
            .addAndReplace(new JPanel());
    }

    // Actiones
    
    private void searchAction() {
        rows = new ArrayList<>();
        columns =  new ArrayList<>();
        if (searchField.getText() != null && !searchField.getText().isEmpty()) {
            for (int r = 0; r < grid.getRowCount(); r++) {
                for (int c = 0; c < grid.getColumnCount(); c++) {
                    Object cellValue = grid.getValueAt(r, c);
                    if (cellValue != null && cellValue.toString().contains(searchField.getText())) {
                        rows.add(r);
                        columns.add(c);
                    }
                }
            }
        }
        if (!rows.isEmpty()) {
            currentMatch = 0;
            grid.selectCell( rows.get(currentMatch), columns.get(currentMatch));
        }
    }

    private void resizeAction() {
        String s1 = (String) rowColumnComboBox.getSelectedItem();
        String s2 = (String) addRemoveComboBox.getSelectedItem();

        int number;
        
        try {
            number = Integer.parseInt(numberField.getText());
        } catch (NumberFormatException e) {
            return;
        }

        switch (s1) {
            case "Filas" -> {
                switch (s2) {
                    case "Quitar" -> { if (number <= grid.getRowCount())grid.removeRows(number); }
                    case "Añadir" -> grid.addRows(number);
                }
            }
            case "Columnas" -> {
                switch (s2) {
                    case "Quitar" -> { if (number <= grid.getColumnCount()) grid.removeColumns(number); }
                    case "Añadir" -> grid.addColumns(number);
                }
            }
        }
    }

    private void previousNextAction (int i) {
        try {
            grid.selectCell( rows.get(i), columns.get(i));
            currentMatch = i;
        } catch (NullPointerException | IndexOutOfBoundsException npe) {
            System.out.println("previousNextAction error "+npe);
        }
    }
}