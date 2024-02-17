package ut01.practica_final.dto;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

// Clase responsable de la gestión de archivos Excel en la aplicación.
public class ExcelFileManager {

    // Referencia a la cuadrícula principal y archivo Excel actual
    private final Grid grid;
    private File excel;
    private final String EXTENSION = "xls";

    public ExcelFileManager(Grid grid) {
        this.grid = grid;
        this.excel = null;
    }
    
    public File getExcelFile() {
        return excel;
    }

    public void setExcelFile(File excel) {
        this.excel = excel;
    }

    // Crea un nuevo archivo Excel con una cuadrícula vacía
    public void newFile() {
        grid.remove();
        grid.addColumns(6);
        grid.addRows(10);
        excel = null;
    }
    
    
    // Seleccion de File Path
    
    private String getFileChooserPath(String dialogTitle) {
        JFileChooser fileChooser = new JFileChooser("src/ut01/practica_Final/archivos");

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de Excel (."+EXTENSION+")", EXTENSION));
        fileChooser.setDialogTitle(dialogTitle);
        fileChooser.setAcceptAllFileFilterUsed(false);

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getPath();
        }
        return null;
    }

    // Abre un archivo Excel existente y carga su contenido en la cuadrícula
    @SuppressWarnings("empty-statement")
    public void open() {
        try {
            
            String path = getFileChooserPath("Seleccionar Archivo Excel");
            
            if(path == null)return;
            
            excel = new File(path);
            grid.remove();
            
            try (FileInputStream inputStream = new FileInputStream(excel)) {
                HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
                Sheet sheet = workbook.getSheetAt(0);
                
                grid.addColumns(sheet.getRow(0).getLastCellNum());
                
                for (Row row : sheet) {
                    Object[] rowData = new Object[grid.getColumnCount()];
                    int cellCount = Math.min(grid.getColumnCount(), row.getLastCellNum());
                    
                    for (int c = 0; c < cellCount; c++) {
                        Cell cell = row.getCell(c);
                        
                        if (cell != null) {
                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_STRING:
                                    rowData[c] = cell.getStringCellValue();
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    rowData[c] = cell.getNumericCellValue();
                                    break;
                                    // Agrega más casos según los tipos de datos que necesitas manejar
                                default:
                                    rowData[c] = null; // O el valor predeterminado que desees
                                    break;
                            }
                        } else {
                            rowData[c] = null; // O el valor predeterminado que desees
                        }
                    }
                    ((DefaultTableModel) grid.getModel()).addRow(rowData);
                }
            }
        } catch (NullPointerException | IOException e) {
            System.out.println("open error "+e);
        }
    }

    // Guarda el archivo Excel actual
    public void save() {
        if (excel == null) {
            saveAs();
        }else{
            saveFile(excel.getPath());
        }
    }

    // Guarda el archivo Excel en una ubicación específica
    public void saveAs() {
        excel = new File(getFileChooserPath("Guardar Archivo Excel") + "."+EXTENSION);
        saveFile(excel.getPath());
    }
    
    private void saveFile(String filePath){
        try {
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("Hoja1");
                
                TableModel model = grid.getModel();
                for (int r = 0; r < model.getRowCount(); r++) {
                    HSSFRow excelRow = sheet.createRow(r);
                    for (int c = 0; c < model.getColumnCount(); c++) {
                        Object value = model.getValueAt(r, c);
                        if (value != null) {
                            excelRow.createCell(c).setCellValue(value.toString());
                        }
                    }
                }
                workbook.write(outputStream);
            }
        } catch (NullPointerException | IOException e) {
            System.out.println("saveFile error "+e);
        }
    }
    
    public String getExcelName(){
        if( excel == null) return "error";
        return excel.getName();
    }
}

