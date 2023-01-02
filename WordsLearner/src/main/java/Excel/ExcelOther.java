package Excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public interface ExcelOther {

     default String getFileName(String baseName){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String dateTimeInfo = dateFormat.format(new Date());
        return baseName.concat(String.format("_%s.xlsx", dateTimeInfo));
    }//getFileName

     default void writeHeaderLine(ResultSet result, XSSFSheet sheet) throws SQLException {
        // write header line containing column names
        ResultSetMetaData metaData = result.getMetaData();
        int numberOfColumns = metaData.getColumnCount();

        Row headerRow = sheet.createRow(0);

        // exclude the first column which is the ID field
        for (int i = 2; i <= numberOfColumns; i++) {
            String columnName = metaData.getColumnName(i);
            Cell headerCell = headerRow.createCell(i - 2);
            headerCell.setCellValue(columnName);
        }
    }//writeHeaderLine
     default void writeDataLines(ResultSet result, XSSFWorkbook workbook, XSSFSheet sheet) throws SQLException{
        ResultSetMetaData metaData= result.getMetaData();
        int numberOfColumns = metaData.getColumnCount();

        int rowCount = 1;

        while (result.next()) {
            Row row = sheet.createRow(rowCount++);

            for (int i = 2; i <= numberOfColumns; i++) {
                Object valueObject = result.getObject(i);

                Cell cell = row.createCell(i - 2);

                if (valueObject instanceof Boolean)
                    cell.setCellValue((Boolean) valueObject);
                else if (valueObject instanceof Double)
                    cell.setCellValue((double) valueObject);
                else if (valueObject instanceof Float)
                    cell.setCellValue((float) valueObject);
                else if (valueObject instanceof Date) {
                    cell.setCellValue((Date) valueObject);
                    formatDateCell(workbook, cell);
                } else cell.setCellValue((String) valueObject);
            }//if
        }//while
    }//writeDataLines

    //ustanawia format daty
     default void formatDateCell(XSSFWorkbook workbook, Cell cell){
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        cell.setCellStyle(cellStyle);
    }//formatDateCell


    //zwraca liczbe wierszy w każdej kolumnie
    private static int[] rowsInEachColumn(String excelFilePath) throws IOException {

        InputStream is = new FileInputStream(excelFilePath);
        Workbook wb = WorkbookFactory.create(is);
        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> rowIter = sheet.rowIterator();
        Row r = rowIter.next();
        short lastCellNum = r.getLastCellNum();
        int[] dataCount = new int[lastCellNum];
        int col = 0;
        rowIter = sheet.rowIterator();
        while(rowIter.hasNext()) {
            Iterator<Cell> cellIter = rowIter.next().cellIterator();
            while(cellIter.hasNext()) {
                Cell cell = cellIter.next();
                col = cell.getColumnIndex();
                dataCount[col] += 1;
            }
        }
        is.close();
        return dataCount;
    }//excelReader

    //zwraca prawdę jeśli wszytkie elementy są sobie równe
     private static boolean checkIfNumOfRowsEqual(String excelFilePath) throws IOException {
        int[]array=rowsInEachColumn(excelFilePath);
        Set<Integer> Set=new HashSet<>();
        for(int element : array){
            Set.add(element);
        }
         return Set.size() == 1;
    }//checkIfAllElementsEqual

     static void checkSheet(Sheet sheet,String path) throws IOException {
         int correctNumOfColumns=2;
         int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
         if (noOfColumns!=correctNumOfColumns||!checkIfNumOfRowsEqual(path)){
             throw new IOException();
         }
    }//checkSheet

}
