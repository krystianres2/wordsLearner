package DataBase;

import DataValidation.DataValidation;
import com.diogonunes.jcolor.Attribute;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Insert extends Select implements DataValidation {

    public static void insertRow(String tableName) {
        String wordPL="";
        String wordENG="";
        byte howMuchToInsert=0;

        System.out.print("Ile słówek chcesz wstawić: ");
        howMuchToInsert=DataValidation.ByteValidation((byte) 1, (byte) 99);
        try {
            while (howMuchToInsert > 0) {
                System.out.println("Podaj słowo po Polsku: ");
                wordPL = DataValidation.StringPlValidation();
                System.out.println("Podaj słowo po Angielsku: ");
                wordENG = DataValidation.StringEngValidation();
                String insertStr = "INSERT INTO " + tableName + " VALUES (?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(insertStr);
                preparedStatement.setString(1, wordPL);
                preparedStatement.setString(2, wordENG);
                preparedStatement.executeUpdate();

                howMuchToInsert--;
            }//while
            System.out.println("Pomyślnie dodano słówka");
        }catch (SQLException e){
            System.out.println(colorize("Błąd bazy danych", Attribute.RED_TEXT()));
            try {
                conn.rollback();
                System.out.println(colorize("Wycofano wprowadzone zmiany",Attribute.RED_TEXT()));
            }catch (SQLException ignored){}
        }
    }//insertRow


}
