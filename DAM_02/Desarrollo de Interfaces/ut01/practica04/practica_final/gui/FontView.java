package ut01.practica_final.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ut01.practica_final.dto.ComponentManager;
import ut01.practica_final.dto.Grid;

public class FontView extends JDialog {

    private final Grid grid;                // Cuadrícula principal
    private final ComponentManager cm;      // Manejador de componentes
    
    private final int PANEL_WIDTH = 400, PANEL_HEIGHT = 35;
    private Font selectedFont;
    private Color selectedColor;

    // Componentes de la interfaz
    private JComboBox<String> fontFamilyComboBox;
    private JComboBox<Integer> fontSizeComboBox;
    private JLabel previewLabel;
    
    public FontView(MainView mainView, Grid grid, ComponentManager cm) {
        super(mainView, "Configurar Fuente", true);
        
        setSize(PANEL_WIDTH, PANEL_HEIGHT * 7);
        setLocationRelativeTo(mainView);
        setResizable(false);
        
        this.cm = cm;
        this.grid = grid;
        selectedFont = null;
        selectedColor = Color.BLACK;
        
        initComponents();
        setVisible(true);
    }

    private void initComponents() {

        fontFamilyComboBox = new JComboBox<>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        fontSizeComboBox = new JComboBox<>(new Integer[] { 8, 10, 12, 14, 16, 18, 20, 24, 28, 32, 36, 48, 72 });
        previewLabel = new JLabel("Texto Muestra");
        
        cm.setContainer(this)
            .addAndReplace(new JPanel(new FlowLayout())).setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT * 2)
            .add(previewLabel)
            .setContainer(this)
            .addAndReplace(new JPanel(new FlowLayout())).setBounds(0, PANEL_HEIGHT * 2, PANEL_WIDTH, PANEL_HEIGHT)
            .add(new JLabel("Tipo"))
            .add(fontFamilyComboBox).event(e -> previewAction())
            .setContainer(this)
            .addAndReplace(new JPanel(new FlowLayout())).setBounds(0, PANEL_HEIGHT * 3, PANEL_WIDTH, PANEL_HEIGHT)
            .add(new JLabel("Tamaño"))
            .add(fontSizeComboBox).event(e -> previewAction())
            .setContainer(this)
            .addAndReplace(new JPanel(new FlowLayout())).setBounds(0, PANEL_HEIGHT * 4, PANEL_WIDTH, PANEL_HEIGHT)
            .add(new JLabel("Color"))
            .add(new JButton("Seleccionar")).event(e -> chooseColorAction())
            .setContainer(this)
            .addAndReplace(new JPanel(new FlowLayout())).setBounds(0, PANEL_HEIGHT * 5, PANEL_WIDTH, PANEL_HEIGHT)
            .add(new JButton("Aplicar")).event(e -> applyFontAction())
            .add(new JButton("Cancelar")).event(e -> dispose())
            .setContainer(this)
            .addAndReplace(new JPanel());
    }
    
    // Acciones

    private void previewAction() {
        selectedFont = new Font((String) fontFamilyComboBox.getSelectedItem(), Font.PLAIN, (int) fontSizeComboBox.getSelectedItem());
        
        previewLabel.setFont(selectedFont);
        previewLabel.setForeground(selectedColor);
    }

    private void chooseColorAction() {
        Color color = JColorChooser.showDialog(this, "Elegir Color", selectedColor);
        if (color != null) selectedColor = color;
        previewAction();
    }

    private void applyFontAction() {
        grid.applyFont(selectedFont, selectedColor);
        dispose();
    }
}
