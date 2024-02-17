
package ut01.practica_final.dto;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Grid extends JTable {

    private final DefaultTableModel model;

    public Grid( ) {
        super();
        model = (DefaultTableModel) getModel();
    }

    private void addRow() {
        model.addRow(new Object[getColumnCount()]);
    }

    private void removeRow() {
        if (getRowCount() > 0) {
            model.removeRow(getRowCount() - 1);
        }
    }

    private void addColumn() {
        model.addColumn(getNameColumnAt(getColumnCount()));
    }

    // Obtiene un nombre de columna basado en un número
    private String getNameColumnAt(int columnCount) {
        StringBuilder name = new StringBuilder();
        while (columnCount >= 0) {
            char l = (char) ('A' + (columnCount % 26));
            name.insert(0, l);
            columnCount = (columnCount / 26) - 1;
        }
        return name.toString();
    }

    private void removeColumn() {
        if (getColumnCount() > 0) {
            model.setColumnCount(getColumnCount() - 1);
        }
    }

    public void addRows(int n) {
        for (int i = 0; i < n; i++) {
            addRow();
        }
    }

    public void removeRows(int n) {
        for (int i = 0; i < n; i++) {
            removeRow();
        }
    }

    public void addColumns(int n) {
        for (int i = 0; i < n; i++) {
            addColumn();
        }
    }

    public void removeColumns(int n) {
        for (int i = 0; i < n; i++) {
            removeColumn();
        }
    }

    // Elimina todos los datos
    public void remove() {
        model.setRowCount(0);
        model.setColumnCount(0);
    }

    public void sort() {
        
        Comparator<Object> customComparator = (o1, o2) -> {
            if (o1 == null && o2 == null) return 0;
            if (o1 == null) return 1;
            if (o2 == null) return -1;
            if (o1 instanceof Number && o2 instanceof Number) {
                double num1 = ((Number) o1).doubleValue();
                double num2 = ((Number) o2).doubleValue();
                return Double.compare(num1, num2);
            } 
            if (o1 instanceof Number) return -1;
            if (o2 instanceof Number) return 1;
            return ((String) o1).compareTo((String) o2);

        };

        for (int r = 0; r < getRowCount(); r++) {
            Object[] rowData = new Object[getColumnCount()];
            for (int c = 0; c < getColumnCount(); c++) rowData[c] = getValueAt(r, c);
            Arrays.sort(rowData, customComparator);
            for (int c = 0; c < getColumnCount(); c++)  setValueAt(rowData[c], r,c);
        }
    }

    public void applyFont(Font f, Color c){
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                rendererComponent.setFont(f);
                rendererComponent.setForeground(c);
                setVerticalAlignment(SwingConstants.CENTER);

                return rendererComponent;
            }
        };
        setDefaultRenderer(Object.class, cellRenderer);
        setRowHeight(getFontMetrics(f).getHeight());
    }
    
    public void selectCell(int r, int c) {
        setRowSelectionInterval(r, r);
        setColumnSelectionInterval(c, c);
        editCellAt(r, c);
    }
    
    public boolean cellExists(String c){
        if(!c.matches("[a-zA-Z]+\\d+")) return false;
        return getRowIndex(c) != -1 && getColumnIndex(c) != -1;
    }
    
    private int getRowIndex(String cell) {
        String regex = "\\d+$";
        String numbers = find( regex, cell);
        int row = Integer.parseInt(numbers) -1;
        if( row <  getRowCount()) return row;
        return -1;
    }
    
    private int getColumnIndex(String cell){
        String regex = "^[A-Za-z]+";
        String letters = find( regex, cell); 
        return model.findColumn(letters.toUpperCase());
    }
    
    private String find(String regex, String cell){
        try{
            Matcher matcher = Pattern.compile(regex).matcher(cell);
            if (matcher.find()) return matcher.group();
        }catch(PatternSyntaxException e){
            System.out.println(regex+" "+cell);
        }
        return null;
    }
    
    public void applyFunction(String result, String c1, String c2, String operation){
        Object op1 = getValueAt(getRowIndex(c1),getColumnIndex(c1));
        Object op2 = getValueAt(getRowIndex(c2),getColumnIndex(c2));
        Double d1, d2;
        try{
            d1 = Double.valueOf(op1.toString());
            d2 = Double.valueOf(op2.toString());
        }catch(NullPointerException | NumberFormatException e){
            return;
        }
        int r = getRowIndex(result);
        int c = getColumnIndex(result);
        applyFunction( r, c, d1, d2, operation);
    }
    
    private void applyFunction(int row, int column, Double d1, Double d2, String operation){
        switch (operation) {
            case "SUMA" -> setValueAt((d1+d2), row, column);
            case "RESTA"-> setValueAt((d1-d2), row, column);
            case "MULTIPLICACION"-> setValueAt((d1*d2), row, column);
            case "DIVISION"-> setValueAt((d1/d2), row, column);
        }
    }
}



