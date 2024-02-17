
package ut02.semaforos_v3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class Interfaz extends JFrame {
    
    // Declaración de variables
    int lectores = 0, escritores = 0, ficheros = 0, maxLectoresDefecto;
    boolean aleatorio = false;
    List<Integer> ficherosCustom = new ArrayList<>();
    private final int field_width = 3; // Ancho de los campos de texto

    public Interfaz() {
        // Crear un diálogo modal
        JDialog dialog = new JDialog(this, "Configuración", true);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Comportamiento al cerrar
        dialog.setSize(350, 260); // Tamaño del diálogo
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null); // Ubicación central

        // Etiquetas y campos de texto para la configuración
        JLabel labelLectores = new JLabel("Lectores:");
        JTextField lectoresTextField = new JTextField(field_width);

        JLabel labelEscritores = new JLabel("Escritores:");
        JTextField escritoresTextField = new JTextField(field_width);

        JLabel labelFicheros = new JLabel("Ficheros:");
        JTextField ficherosTextField = new JTextField(field_width);

        JLabel labelLectoresSimultaneos = new JLabel("Lectores Simultáneos por Defecto:");
        JTextField lectoresSimultaneosField = new JTextField(field_width);
        
        JLabel labelPreField = new JLabel("Fichero con");
        JTextField lectoresCustom = new JTextField(field_width);
        JLabel labelPostField = new JLabel("lectores simultaneos");
        
        // Botones para añadir y empezar
        JButton btnAnyadir = new JButton("Añadir");
        JButton btnIniciar = new JButton("Lanzar");

        // Lista desplegable para los lectores simultáneos
        JComboBox<Integer> ficherosCustomDropdown = new JComboBox<>();

        // Acción para el botón "Añadir"
        btnAnyadir.addActionListener((ActionEvent e) -> {
            try {
                int n = Integer.parseInt(lectoresCustom.getText());
                ficherosCustom.add(n);
                ficherosCustomDropdown.addItem(n);
                ficherosCustomDropdown.setSelectedIndex(ficherosCustomDropdown.getItemCount()-1);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Ingrese un número entero");
            }
        });

        // Acción para el botón "Lanzar"
        btnIniciar.addActionListener((ActionEvent e) -> {
            try {
                lectores = Integer.parseInt(lectoresTextField.getText());
                escritores = Integer.parseInt(escritoresTextField.getText());
                ficheros = Integer.parseInt(ficherosTextField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Ingrese valores válidos");
                return;
            }
            try {
                maxLectoresDefecto = Integer.parseInt(lectoresSimultaneosField.getText());
                aleatorio = false;
            } catch (NumberFormatException ex) {
                aleatorio = true;
            }
            
            dialog.dispose(); // Cerrar el diálogo
        });

        // Creación de paneles para organizar los componentes
        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.add(labelLectores);
        panel1.add(lectoresTextField);
        panel1.add(labelEscritores);
        panel1.add(escritoresTextField);
        panel1.add(labelFicheros);
        panel1.add(ficherosTextField);

        JPanel panel2 = new JPanel(new FlowLayout());
        panel2.add(labelLectoresSimultaneos);
        panel2.add(lectoresSimultaneosField);

        JPanel panel3 = new JPanel(new FlowLayout());
        panel3.add(labelPreField);
        panel3.add(lectoresCustom);
        panel3.add(labelPostField);

        JPanel panel4 = new JPanel(new FlowLayout());
        panel4.add(btnAnyadir);
        panel4.add(ficherosCustomDropdown);
        
        JPanel panel5 = new JPanel(new FlowLayout());
        panel5.add(btnIniciar);

        // Configuración del diseño del diálogo y adición de componentes
        dialog.setLayout(new GridLayout(5,1));
        dialog.add(panel1);
        dialog.add(panel2);
        dialog.add(panel3);
        dialog.add(panel4);
        dialog.add(panel5);

        dialog.setVisible(true); // Mostrar el diálogo
    }
}