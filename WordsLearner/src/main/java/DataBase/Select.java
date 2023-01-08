package DataBase;

import Row.Row;
import com.diogonunes.jcolor.Attribute;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.diogonunes.jcolor.Ansi.colorize;

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
            if(i==1) System.out.println("Brak słówek w zestawie");
        }catch (SQLException e){
            System.out.println(colorize("Błąd bazy danych", Attribute.RED_TEXT()));
        }
        System.out.println();
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
        System.out.println(colorize("Bład bazy danych",Attribute.RED_TEXT()));
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
            System.out.println(colorize("Błąd bazy danych",Attribute.RED_TEXT()));
        }
    }//showAllWordsWithRealID

    public static ArrayList<Row> saveToList(String tableName){
        ArrayList<Row> list= new ArrayList<>();
        try {
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM " +tableName);
            while (result.next()){
             Row obj=new Row();
                obj.setWordPl(result.getString("wordPL"));
                obj.setWordEng(result.getString("wordENG"));
                list.add(obj);
            }
        }catch (SQLException e){
            System.out.printf(colorize("Błąd bazy danych",Attribute.RED_TEXT()));
        }
        return list;
    }//saveToList


}
