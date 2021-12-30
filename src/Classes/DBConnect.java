package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {


    public static Connection dataBaseLink;

    public static Connection getConnection(){
        if(dataBaseLink == null){
            String databaseName = "erik1";
            String databaseUser = "root";
            String databasePassword = "Mclaren123!!";
            String url = "jdbc:mysql://localhost/"+databaseName;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                dataBaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
            }catch (Exception e){
                e.printStackTrace();
            }
        }return dataBaseLink;
    }

}
