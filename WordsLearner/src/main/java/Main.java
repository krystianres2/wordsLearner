import DataBase.DbConnector;
import DataBase.Delete;
import DataBase.Insert;
import DataBase.Select;

public class Main {
    public static void main(String[]args){
        DbConnector.connect();
   //     Insert.insertRow("tab1");
//Select.showAllWordsWithIncrementedID("tab1");
//Insert.insertRow("tab1");
//Select.showAllWordsWithIncrementedID("tab1");
Delete.deleteRowInRange("tab1");
Select.showAllWordsWithIncrementedID("tab1");

    }
}
