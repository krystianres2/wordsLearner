package Excel;

import DataBase.DbConnector;
import DataValidation.DataValidation;
import com.diogonunes.jcolor.Attribute;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import static com.diogonunes.jcolor.Ansi.colorize;


public class Excel extends DbConnector implements DataValidation ,ExcelOther {

    public void importFromXLS(String tableName) {
        try {
            System.out.println("Podaj ścieżkę dostępu do pliku. Pamiętaj aby podfoldery rodzielać za pomocą \\");
            String path=DataValidation.xlsFilePathValidation();//ścieżka do pliku xls
            FileInputStream inputStream = new FileInputStream(path);

            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = firstSheet.iterator();
            ExcelOther.checkSheet(firstSheet,path);//sprawdza poprawność arkusza

            String sql = "INSERT INTO " +tableName+ " VALUES (?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();

                    int columnIndex = nextCell.getColumnIndex();
                    switch (columnIndex) {
                        case 0:
                            String name1 = nextCell.getStringCellValue();
                            statement.setString(1, name1);
                            break;
                        case 1:
                            String name2 = nextCell.getStringCellValue();
                            statement.setString(2, name2);
                    }//switch
                }
                statement.addBatch();
            }
            workbook.close();
            statement.executeBatch();
            System.out.println("Pomyślnie zaimportowano");

        }catch (IOException e){
            System.out.println(colorize("Błąd w trakcie wcztywania pliku", Attribute.RED_TEXT()));

        }catch (SQLException e){
            System.out.println(colorize("Błąd bazy danych",Attribute.RED_TEXT()));
            try{
                conn.rollback();
            }catch (SQLException ignored){
            }
        }

    }//importFromXLS


    //export to XLS
    public void exportToXls(@NotNull String tableName){
        String excelFilePath=getFileName(tableName.concat("_Export"));
        try{
            String sql="SELECT rowid, * FROM ".concat(tableName);

            Statement statement=conn.createStatement();

            ResultSet result = statement.executeQuery(sql);

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet(tableName);

            writeHeaderLine(result, sheet);

            writeDataLines(result, workbook, sheet);

            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();

            statement.close();
            System.out.println("Pomyślnie wyeksportowano");

        }catch (SQLException e){
            System.out.println(colorize("Błąd bazy danych",Attribute.RED_TEXT()));
        }catch (IOException e){
            System.out.println(colorize("Błąd pliku",Attribute.RED_TEXT()));
        }
    }//export

}
