package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String DB_URL = "jdbc:mysql://ibatech.online:3306/";
    private static final String USERNAME = "dbadmin";
    private static final String PASSWORD = "ibatech2019";

    private static Connection connection;

    public DbConnection() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
