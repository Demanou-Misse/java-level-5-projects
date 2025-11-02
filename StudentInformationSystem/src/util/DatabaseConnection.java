package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/school";
    private static final String USER = "postgres";
    private static final String PASSWORD = "2468BbjbB";

    private static Connection connection;

    public Connection getConnexion() throws SQLException {
        if (connection == null || connection.isClosed()) connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

}
