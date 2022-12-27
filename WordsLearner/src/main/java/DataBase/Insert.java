package DataBase;

import DataValidation.DataValidation;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert extends Select implements DataValidation {
    public static void insertRow(String tableName) {
        String wordPL="";
        String wordENG="";
        byte howMuchToInsert=0;

        System.out.println("Ile słówek chcesz wstawić: ");
        howMuchToInsert=DataValidation.ByteValidation((byte) 1, (byte) 99);
        try {
            while (howMuchToInsert > 0) {
                System.out.println("Podaj słowo po Polsku: ");
                wordPL = DataValidation.StringPlValidation();
                System.out.println("Podaj słowo po Angielsku: ");
                wordENG = DataValidation.StringEngValidation();
                String insertStr = "INSERT INTO " + tableName + " VALUES (?,?)";
                Statement stmt = conn.createStatement();
                PreparedStatement preparedStatement = conn.prepareStatement(insertStr);
                preparedStatement.setString(1, wordPL);
                preparedStatement.setString(2, wordENG);
                preparedStatement.executeUpdate();

                howMuchToInsert--;
            }//while
        }catch (SQLException e){
            System.out.println("Error");
            try {
                conn.rollback();
                System.out.println("Wycofano wprowadzone zmiany");
            }catch (SQLException ignored){}
        }
    }//insertRow


}
