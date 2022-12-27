package DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Select extends DbConnector implements QueryExecutor{
    //metoda wyświetla zawartość bazy z ID jako wartość inkrementowana
    public static void showAllWordsWithIncrementedID(String tableName){
        int i=1;
        try {
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM "+tableName);
            while (result.next()) {
                System.out.println(i+". "+result.getString("wordPL") + " - " + result.getString("wordENG"));
                i++;
            }
        }catch (SQLException e){
            System.out.println("Błąd bazy danych");
        }
    }//showAllWords
protected static HashMap<Integer,Integer> IdToHashMap(String tableName)  {//używane w delete
    int i=1;//klucz HashMap
    HashMap<Integer,Integer> map=new HashMap<>();
    try {
        ResultSet result = QueryExecutor.executeSelect("SELECT rowid FROM " + tableName);
        while (result.next()) {
            map.put(i, result.getInt("rowid"));
            i++;
        }//while
    }catch (SQLException e){
        System.out.println("Bład zapisu do HashMapy");
    }
    return map;
}//IdToHashMap
    public static void showAllWordsWithRealID(String tableName) {
        try {
            ResultSet result = QueryExecutor.executeSelect("SELECT rowid, * FROM " +tableName);
            while (result.next()) {
                System.out.println(result.getInt("rowid") + ". " + result.getString("wordPL") + " - " + result.getString("wordENG"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Błąd bazy danych");
        }
    }//showAllWordsWithRealID
}
