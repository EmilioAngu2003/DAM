package ut01.practica_final.gui;

import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ut01.practica_final.dto.ComponentManager;
import ut01.practica_final.dto.Grid;

public class FunctionView extends JDialog {

    private final Grid grid;                // Cuadrícula principal
    private final ComponentManager cm;      // Manejador de componentes

    private final int PANEL_WIDTH = 400, PANEL_HEIGHT = 35;
    private final ArrayList<String> functions;

    // Componentes de la interfaz
    private JTextField operator1, operator2, result;
    private JComboBox<String> operations, comboBox;

    public FunctionView(MainView mainView, Grid grid, ComponentManager cm, ArrayList<String> functions) {
        super(mainView, "Formula", true);

        setSize(PANEL_WIDTH, PANEL_HEIGHT * 5);
        setLocationRelativeTo(mainView);
        setResizable(false);

        this.grid = grid;
        this.functions = functions;
        this.cm = cm;

        initComponents();
        setVisible(true);
    }

    private void initComponents() {

        operator1 = new JTextField(5);
        operator2 = new JTextField(5);
        result = new JTextField(5);
        operations = new JComboBox<>(new String[]{"SUMA", "RESTA", "MULTIPLICACION", "DIVISION"});
        comboBox = new JComboBox<>(functions.toArray(String[]::new));

        cm.setContainer(this)
                .addAndReplace(new JPanel(new FlowLayout())).setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT)
                .add(new JLabel("Seleccion de Celdas"))
                .setContainer(this)
                .addAndReplace(new JPanel(new FlowLayout())).setBounds(0, PANEL_HEIGHT, PANEL_WIDTH, PANEL_HEIGHT)
                .add(new JLabel("Operable"))
                .add(operator1)
                .add(new JLabel("Operable"))
                .add(operator2)
                .add(new JLabel("Resultante"))
                .add(result)
                .setContainer(this)
                .addAndReplace(new JPanel(new FlowLayout())).setBounds(0, PANEL_HEIGHT * 2, PANEL_WIDTH, PANEL_HEIGHT)
                .add(operations)
                .add(new JButton("Añadir Formula")).event(e -> addFunctionAction())
                .setContainer(this)
                .addAndReplace(new JPanel(new FlowLayout())).setBounds(0, PANEL_HEIGHT * 3, PANEL_WIDTH, PANEL_HEIGHT)
                .add(comboBox)
                .add(new JButton("Borrar Formula")).event(e -> removeFunction())
                .setContainer(this)
                .addAndReplace(new JPanel());
    }
    
    // Acciones

    private void addFunctionAction() {
        if (!grid.cellExists(result.getText())) return;
        if (!grid.cellExists(operator1.getText())) return;
        if (!grid.cellExists(operator2.getText())) return;

        String operation = operations.getItemAt(operations.getSelectedIndex());
        String nameFunction = result.getText() + "=" + operation + "(" + operator1.getText() + ":" + operator2.getText() + ")";
        
        functions.add(nameFunction);
        comboBox.addItem(nameFunction);
        
        grid.applyFunction(result.getText(), operator1.getText() , operator2.getText(), operation);
    }

    private void removeFunction() {
        try {
            functions.remove(comboBox.getSelectedIndex());
            comboBox.removeItemAt(comboBox.getSelectedIndex());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }
}
