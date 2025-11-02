package util;

import java.sql.Connection;

public class DatabaseInitializer {
    DatabaseConnection databaseConnection = new DatabaseConnection();

    public void initializeDatabase() {
        try (Connection conn = databaseConnection.getConnexion()) {
            if (conn != null) System.out.println("Database connected successfully ✅");
        } catch (Exception e) {
            System.err.println("❌ Database initialization failed: " + e.getMessage());
        }
    }
}
