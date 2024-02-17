package ut01.practica_final.gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.function.Consumer;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelListener;
import ut01.practica_final.dto.ComponentManager;
import ut01.practica_final.dto.ExcelFileManager;
import ut01.practica_final.dto.Grid;

public class MainView extends JFrame {

    private final Grid grid;  // La cuadrícula principal para mostrar los datos.
    private final ComponentManager cm;  // Manejador de componentes para la interfaz de usuario.
    private final ExcelFileManager efm;  // Manejador para cargar y guardar archivos Excel.
    
    private final ArrayList<String> functions;
    private boolean menuEnabled; 
    private Object clipboard;
    private final TableModelListener updateCells;
            
    // Componentes de la interfaz
    private JPopupMenu popupMenu;

    public MainView() {
        super("Hoja de Cálculo al Estilo Excel");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        cm = new ComponentManager(this);
        grid = new Grid();
        efm = new ExcelFileManager(grid);
        functions = new ArrayList<>();
        menuEnabled = false;
        updateCells = e -> updateCells();

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        add(new JScrollPane(grid), BorderLayout.CENTER);
        cm.setComponent(grid).event(showPopUpMenu());
        grid.getModel().addTableModelListener( updateCells);
        
        createPopupMenu();
        createMenuBar();
    }

    private void createPopupMenu() {
        popupMenu = new JPopupMenu();

        cm.setContainer(popupMenu)
            .add(new JMenuItem("Cortar")).event(e -> cutAction())
            .add(new JMenuItem("Copiar")).event(e -> copyAction())
            .add(new JMenuItem("Pegar")).event(e -> pasteAction());
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        cm.setContainer(menuBar)
            .addAndReplace(new JMenu("Archivo"))
            .add(new JMenuItem("Nuevo")).event(e -> newFileAction())
            .add(new JMenuItem("Abrir")).event(e -> openAction())
            .add(new JMenuItem("Guardar")).event(e -> saveAction())
            .add(new JMenuItem("Guardar como")).event(e -> saveAsAction())
            .add(new JMenuItem("Salir")).event(e -> dispose())
            .setContainer(menuBar)
            .add(new JMenu("Edición")).event(showEditionView())
            .add(new JMenu("Fuente")).event(showFontView())
            .add(new JMenu("Formulas")).event(showFunctionView());

        setJMenuBar(menuBar);
    }

    // Acciones

    private void cutAction() {
        int row = grid.getSelectedRow();
        int column = grid.getSelectedColumn();
        clipboard = grid.getValueAt(row, column);
        grid.setValueAt(null, row, column);
    }

    private void copyAction() {
        clipboard = grid.getValueAt(grid.getSelectedRow(), grid.getSelectedColumn());
    }

    private void pasteAction() {
        grid.setValueAt(clipboard, grid.getSelectedRow(), grid.getSelectedColumn());
    }

    private void newFileAction() {
        efm.newFile();
        setTitle("Hoja de Cálculo al Estilo Excel - (Nuevo Archivo)");
        menuEnabled = true;
    }

    private void openAction() {
        efm.open();
        setTitle("Hoja de Cálculo al Estilo Excel - " + efm.getExcelName());
        menuEnabled = true;
    }

    private void saveAction() {
        if (menuEnabled) {
            efm.save();
            setTitle("Hoja de Cálculo al Estilo Excel - " + efm.getExcelName());
        }
    }

    private void saveAsAction() {
        if (menuEnabled) {
            efm.saveAs();
            setTitle("Hoja de Cálculo al Estilo Excel - " + efm.getExcelFile().getName());
        }
    }
    
    public void updateCells() {
        
        grid.getModel().removeTableModelListener(updateCells);
        
        for (String function : functions) {
            String[] parts = function.split("=");
            String result = parts[0];
            String operation = parts[1].substring(0, parts[1].indexOf("("));
            String op1 = parts[1].substring(parts[1].indexOf("(") + 1, parts[1].indexOf(":"));
            String op2 = parts[1].substring(parts[1].indexOf(":") + 1, parts[1].indexOf(")"));
            grid.applyFunction(result, op1, op2, operation);
        }
        
        grid.getModel().addTableModelListener( updateCells);
    }

    // Inicio de Vistas
   
    private MouseAdapter showEditionView() {
        return createMouseAdapter(e -> new EditionView(null, grid, cm));
    }

    private MouseAdapter showFontView() {
        return createMouseAdapter(e -> new FontView(null, grid, cm));
    }
    
    private MouseAdapter showFunctionView() {
        return createMouseAdapter(e -> new FunctionView(null, grid, cm, functions));
    }
 
    private MouseAdapter showPopUpMenu() {
        return createMouseAdapter(e -> {
            if (SwingUtilities.isRightMouseButton(e)) {
                int row = grid.rowAtPoint(e.getPoint());
                int column = grid.columnAtPoint(e.getPoint());
                grid.setRowSelectionInterval(row, row);
                grid.setColumnSelectionInterval(column, column);
                popupMenu.show(grid, e.getX(), e.getY());
            }
        });
    }

    // MouseAdapter personalizado
    
    private MouseAdapter createMouseAdapter(Consumer<MouseEvent> action) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && menuEnabled) {
                    action.accept(e);
                }
            }
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView mainView = new MainView();
        });
    }
}
