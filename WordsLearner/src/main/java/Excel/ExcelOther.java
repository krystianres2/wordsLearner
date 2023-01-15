package Excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;

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

     default String getFileName(@NotNull String baseName){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String dateTimeInfo = dateFormat.format(new Date());
        return baseName.concat(String.format("_%s.xlsx", dateTimeInfo));
    }//getFileName

     default void writeHeaderLine(@NotNull ResultSet result, @NotNull XSSFSheet sheet) throws SQLException {
         // wypisywanie header line z nazwami kolumn
        ResultSetMetaData metaData = result.getMetaData();
        int numberOfColumns = metaData.getColumnCount();

        Row headerRow = sheet.createRow(0);

         //pomiń kolumne z ID
        for (int i = 2; i <= numberOfColumns; i++) {
            String columnName = metaData.getColumnName(i);
            Cell headerCell = headerRow.createCell(i - 2);
            headerCell.setCellValue(columnName);
        }
    }//writeHeaderLine
     default void writeDataLines(@NotNull ResultSet result, XSSFWorkbook workbook, XSSFSheet sheet) throws SQLException{
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

     default void formatDateCell(@NotNull XSSFWorkbook workbook, @NotNull Cell cell){
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        cell.setCellStyle(cellStyle);
    }//formatDateCell


    private static int @NotNull [] rowsInEachColumn(String excelFilePath) throws IOException {
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
    }//rowsInEachColumn

     private static boolean checkIfNumOfRowsEqual(String excelFilePath) throws IOException {
        int[]array=rowsInEachColumn(excelFilePath);
        Set<Integer> Set=new HashSet<>();
        for(int element : array){
            Set.add(element);
        }
         return Set.size() == 1;
    }//checkIfNumOfRowsEqual

    //metoda sprawdza poprawność pliku xls
     static void checkSheet(@NotNull Sheet sheet, String path) throws IOException {
         int correctNumOfColumns=2;
         int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
         if (noOfColumns!=correctNumOfColumns||!checkIfNumOfRowsEqual(path)){
             throw new IOException();
         }
    }//checkSheet

}
