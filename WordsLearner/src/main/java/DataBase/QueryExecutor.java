package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface QueryExecutor {
    static ResultSet executeSelect(String selectQuery){
        try{
            Connection connection=DbConnector.connect();
            Statement statement=connection.createStatement();
            return statement.executeQuery(selectQuery);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }//executeSelect
}
