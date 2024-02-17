package ut01.practica_final.dto;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.AbstractButton;
import javax.swing.JComboBox;

public class ComponentManager {

    private Container container; // Contenedor actual
    private Component component; // Componente actual

    // Constructor que recibe el contenedor con el que se trabajará.
    public ComponentManager(Container container) {
        this.container = container;
    }

    // Establece el contenedor actual
    public ComponentManager setContainer(Container container) {
        this.container = container;
        return this;
    }

    // Establece el componente actual
    public ComponentManager setComponent(Component component) {
        this.component = component;
        return this;
    }

    // Agrega un componente al contenedor y lo establece como el componente actual.
    public ComponentManager add(Component c) {
        container.add(c);
        component = c;
        return this;
    }

    // Agrega un componente al contenedor y reemplaza el contenedor actual con ese componente.
    public ComponentManager addAndReplace(Component c) {
        container.add(c);
        container = (Container) c;
        return this;
    }

    // Agrega un ActionListener al componente actual
    public ComponentManager event(ActionListener e) {
        if (component instanceof AbstractButton) {
            AbstractButton button = (AbstractButton) component;
            button.addActionListener(e);
        } else if (component instanceof JComboBox<?>) {
            JComboBox<?> comboBox = (JComboBox<?>) component;
            comboBox.addActionListener(e);
        }
        return this;
    }

    // Agrega un MouseListener al componente actual
    public ComponentManager event(MouseListener e) {
        component.addMouseListener(e);
        return this;
    }

    // Establece los límites (posición y tamaño) del contenedor actual
    public ComponentManager setBounds(int x, int y, int w, int h) {
        container.setBounds(x, y, w, h);
        return this;
    }
}
