package DataBase;

import DataValidation.DataValidation;
import Exceptions.IncorrectValue;
import Others.Others;
import com.diogonunes.jcolor.Attribute;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Delete extends Select{

    //metoda do usuwania pojedynczego wiersza
    public static void deleteRow(String tableName){
        int Id=0;//ID wiersza
        HashMap<Integer,Integer> map=Select.IdToHashMapValue(tableName);
        showAllWordsWithIncrementedID(tableName);
        try {
            System.out.print("Podaj numer rekordu który chcesz usunąć: ");
            Id= DataValidation.IntValidation(0,9999);
            if (map.containsKey(Id)){
                String deleteStr = "DELETE FROM "+tableName+" WHERE rowid=?";
                PreparedStatement preparedStatement = conn.prepareStatement(deleteStr);
                preparedStatement.setInt(1,map.get(Id));
                preparedStatement.executeUpdate();
            }else {
                System.out.println(colorize("Nie ma rekordu o podanym ID", Attribute.RED_TEXT()));
            }
        }catch (SQLException e){
            try{
                conn.rollback();
                System.out.println(colorize("Wycofano wprowadzone zmiany",Attribute.RED_TEXT()));
            }catch (SQLException ignored){
            }
        }
    }//deleteRow

    //metoda do usuwania kilku rekordów na raz
    public static void deleteRowInRange(String tableName) {
        String In;//IN(In)
        try {
            HashMap<Integer, Integer> map = IdToHashMapValue(tableName);//Do Values zapisywane są ID tabeli a do Key wartość od 1 inkrementowana
            showAllWordsWithIncrementedID(tableName);//wyświetlane są wiersze:  i++. wordPL wordENG
            System.out.println("Podaj numery ID rekordów które chcesz usunąć oddzielając je przecinkiem");
            In = DataValidation.IntegersAndCommasValidation();//zapisywany i walidowany jest ciąg liczb odzielonych przecinkami
            String[] arrOfStr = In.split(",", 0);//liczby zapisywane są do tablicy
            if (Others.CheckIfMapContainsArray(arrOfStr, map)) {//jeśli HashMapa map zawiera w swoich Keys wszytkie wartości z tablicy arrOfStr
                arrOfStr = Others.keysToValues(arrOfStr, map);//w tablei arrOfStr klucze mapy podmieniane są na Values mapy
                In = Others.strArrayToStringSepByCommas(arrOfStr);//zapisywane są do string wartości z arrOfStr i oddzielane przecinkami
                String deleteStr = "DELETE FROM " + tableName + " WHERE rowid IN("+In+")";// tworzone jest zapytanie SQL
                PreparedStatement preparedStatement = conn.prepareStatement(deleteStr);
                preparedStatement.executeUpdate();
            } else throw new IncorrectValue();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(colorize("Błąd bazy danych",Attribute.RED_TEXT()));
            try{
                conn.rollback();
                System.out.println(colorize("Wycofano zmiany",Attribute.RED_TEXT()));
            }catch (SQLException ignored){}
        }catch (IncorrectValue e){
            System.out.println(colorize("Nie ma rekordu o podanym ID",Attribute.RED_TEXT()));
        }
    }//deleteRowInRange

}
