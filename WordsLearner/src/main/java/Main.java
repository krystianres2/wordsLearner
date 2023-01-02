import DataBase.DbConnector;
import DataBase.Delete;
import DataBase.Insert;
import DataBase.Select;
import Excel.Excel;
import Others.Learn;

public class Main {
    public static void main(String[]args){
        DbConnector.connect();
       // System.out.println("\033[31mRed text\033[0m");
        Excel e=new Excel();
        //e.importFromXLS("Tab2");
        Select.showAllWordsWithRealID("Tab2");

    }
}
