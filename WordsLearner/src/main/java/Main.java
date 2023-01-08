import DataBase.DbConnector;
import DataBase.Delete;
import DataBase.Insert;
import DataBase.Select;
import DataValidation.DataValidation;
import Excel.Excel;
import Others.Learn;
import Others.Others;
import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Main {
    public static void main(String[]args){
        byte choice=7;
        byte subChoice=1;
        try {
            DbConnector.connect();

            while (choice != 8) {
                Others.next();
                Others.displayMenu();
                System.out.print("Podaj numer operacji którą chcesz wykonać: ");
                choice = DataValidation.ByteValidation((byte) 1, (byte) 8);
                switch (choice) {
                    case 1:
                        Select.showAllWordsWithIncrementedID(Others.chooseTable());
                        break;
                    case 2:
                        Insert.insertRow(Others.chooseTable());
                        break;
                    case 3:
                        Others.displayDeleteSubMenu();
                        System.out.print("Podaj numer operacji którą chcesz wykonać: ");
                        subChoice = DataValidation.ByteValidation((byte) 1, (byte) 2);
                        if (subChoice == 1) {
                            Delete.deleteRow(Others.chooseTable());
                        }
                        if (subChoice == 2) {
                            Delete.deleteRowInRange(Others.chooseTable());
                        }
                        break;
                    case 4:
                        Learn.learn(Select.saveToList(Others.chooseTable()));
                        break;
                    case 5:
                        Learn.test(Select.saveToList(Others.chooseTable()));
                        break;
                    case 6:
                        Excel e = new Excel();
                        e.importFromXLS(Others.chooseTable());
                        break;
                    case 7:
                        Excel ex = new Excel();
                        ex.exportToXls(Others.chooseTable());
                        break;
                    default:
                }//choice
            }//while
        }catch (Exception e){
            System.out.println(colorize("Błąd krytyczny", Attribute.RED_TEXT()));
        }
    }
}
