package DataBase;

import DataValidation.DataValidation;
import Exceptions.IncorrectValue;
import Others.Others;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Delete extends Select{

    //metoda do usuwania pojedynczego wiersza
    public static void deleteRow(String tableName){
        int Id=0;//ID wiersza
        int i=1;//klucz HashMap
        HashMap<Integer,Integer> map=new HashMap<>();
        map=Select.IdToHashMap(tableName);
        showAllWordsWithIncrementedID(tableName);
        try {
            System.out.println("Podaj numer rekordu który chcesz usunąć");
            Id= DataValidation.IntValidation(0,9999);
            if (map.containsKey(Id)){
                String deleteStr = "DELETE FROM "+tableName+" WHERE rowid=?";
                Statement stmt = conn.createStatement();
                PreparedStatement preparedStatement = conn.prepareStatement(deleteStr);
                preparedStatement.setInt(1,map.get(Id));
                preparedStatement.executeUpdate();
            }else {
                System.out.println("Nie ma rekordu o podanym ID");
            }
        }catch (SQLException e){
            try{
                conn.rollback();
                System.out.println("Wycofano wprowadzone zmiany");
            }catch (SQLException ignored){
            }
        }
    }//deleteRow

    public static void deleteRowInRange(String tableName) {//dopracować//
        String In;//IN(In)
        try {
            HashMap<Integer, Integer> map = IdToHashMap(tableName);//Do Values zapisywane są ID tabeli a do Key wartość od 1 inkrementowana
            showAllWordsWithIncrementedID(tableName);//wyświetlane są wiersze:  i++. wordPL wordENG
            System.out.println("Podaj numery ID rekordów które chcesz usunąć oddzielając je przecinkiem");
            In = DataValidation.IntegersAndCommas();//zapisywany i walidowany jest ciąg liczb odzielonych przecinkami
            String[] arrOfStr = In.split(",", 0);//liczby zapisywane są do tablicy
            if (Others.MapContainsArray(arrOfStr, map)) {//jeśli HashMapa map zawiera w swoich Keys wszytkie wartości z tablicy arrOfStr
                arrOfStr = Others.keysToValues(arrOfStr, map);//w tablei arrOfStr klucze mapy podmieniane są na Values mapy
                In = Others.strArrayToStringSepByCommas(arrOfStr);//zapisywane są do string wartości z arrOfStr i oddzielane przecinkami
                String deleteStr = "DELETE FROM " + tableName + " WHERE rowid IN("+In+")";// tworzone jest zapytanie SQL
                Statement stmt = conn.createStatement();
                PreparedStatement preparedStatement = conn.prepareStatement(deleteStr);
               // preparedStatement.setString(0, In);
                preparedStatement.executeUpdate();
            } else throw new IncorrectValue();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Błąd bazy danych");
            try{
                conn.rollback();
                System.out.println("Wycofano zmiany");
            }catch (SQLException ignored){}
        }catch (IncorrectValue e){
            System.out.println("Nie ma rekordu o podanym ID");
        }
    }//deleteRowInRange

}
