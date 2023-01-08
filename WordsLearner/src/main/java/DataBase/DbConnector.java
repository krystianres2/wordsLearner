package DataBase;
import Row.Row;
import com.diogonunes.jcolor.Attribute;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.diogonunes.jcolor.Ansi.colorize;

public class DbConnector extends Row {
    private final static String DB_URL="jdbc:sqlite:Words";//adres URL bazy

    public static Connection conn;//do przechwytywania connection

    //metoda do nawiązywania połączenia z bazą danych
    public static Connection connect(){
        Connection connection=null;
        try {
            connection= DriverManager.getConnection(DB_URL);
            conn=connection;
          //  System.out.println("Połączono z bazą danych");

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(colorize("Połączenie z bazą danych nie powiodło się", Attribute.RED_TEXT()));
            checkIfDbExist("Words");
        }
        return connection;
    }//connect

    //metoda sprawdza czy plik z bazą o podanej nazwie istnieje
    private static void checkIfDbExist(String fileName){
        File file=new File(fileName);
        if (file.exists()){
            System.out.println(colorize("Znaleziono plik z bazą danych",Attribute.RED_TEXT()));
        }else {
            System.out.println(colorize("Nie znaleziono pliku z bazą danych",Attribute.RED_TEXT()));
        }
    }//checkIfDbExist


}
