package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	String path;

    // Constructor
    public ExcelUtilities(String path) {
        this.path = path;
    }

    // Get total row count
    public int getRowCount(String sheetName) throws IOException {

        try (FileInputStream fi = new FileInputStream(path); //open the excel file 
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) { // Access the workbook

            XSSFSheet sheet = workbook.getSheet(sheetName); // Access the mentioned sheet from the excel. If user provides the sheet1, then this will read only sheet1

            return sheet.getLastRowNum(); // this will return the last row index of the sheet
        }
    }

    // Get total column count
    public int getCellCount(String sheetName, int rownum) throws IOException {

        try (FileInputStream fi = new FileInputStream(path);   //open the excel file 
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {   // Access the workbook

            XSSFSheet sheet = workbook.getSheet(sheetName); // Access the mentioned sheet from the excel. If user provides the sheet1, then this will read only sheet1
            XSSFRow row = sheet.getRow(rownum); //this method is to determine how many column positions are used in a particular row.

            return row == null ? 0 : row.getLastCellNum();
        }
    }

    // Read data from a particular cell
    public String getCellData(String sheetName,int rownum, int colnum) throws IOException {

        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(rownum);

            if (row == null) {
                return "";
            }

            XSSFCell cell = row.getCell(colnum);

            DataFormatter formatter = new DataFormatter();

            return formatter.formatCellValue(cell);
        }
    }

    // Write data into a particular cell
    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {

        XSSFWorkbook workbook;

        try (FileInputStream fi = new FileInputStream(path)) {
        	 workbook = new XSSFWorkbook(fi);
        }

        try (XSSFWorkbook wb = workbook) {

            XSSFSheet sheet = wb.getSheet(sheetName);

            XSSFRow row = sheet.getRow(rownum);

            if (row == null) {
                row = sheet.createRow(rownum);
            }

            XSSFCell cell = row.getCell(colnum);

            if (cell == null) {
                cell = row.createCell(colnum);
            }

            cell.setCellValue(data);

            try (FileOutputStream fo =
                    new FileOutputStream(path)) {

                wb.write(fo);
            }
        }
    }
}