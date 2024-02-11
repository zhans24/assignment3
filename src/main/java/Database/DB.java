package Database;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    private static final String dbname ="oop3";
    private static final String name = "postgres";
    private static final String pass="1234";

    public static Connection getconnection() throws Exception{
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,name,pass);
    }
}
