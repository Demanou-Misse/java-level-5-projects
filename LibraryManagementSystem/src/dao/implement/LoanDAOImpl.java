package dao.implement;

import dao.LoanDAO;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanDAOImpl implements LoanDAO {
    DatabaseConnection databaseConnection = new DatabaseConnection();

    @Override
    public void borrowBook (int id_book, int id_member) throws SQLException {
        String sqlInsert = "INSERT INTO loans (id_book, id_member) VALUES (?, ?)";
        String sqlUpdate = "UPDATE books SET available = 'FALSE' WHERE id_book = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt1 = conn.prepareStatement(sqlInsert);
             PreparedStatement stmt2 = conn.prepareStatement(sqlUpdate)) {

            stmt1.setInt(1, id_book);
            stmt1.setInt(2, id_member);
            stmt1.executeUpdate();

            stmt2.setInt(1, id_book);
            stmt2.executeUpdate();
        }
    }

    @Override
    public void returnBook (int id_book, int id_member) throws SQLException {
        String sql = "UPDATE loans SET return_date = CURRENT_DATE WHERE id_book = ? AND id_member = ? AND return_date IS NULL";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_book);
            stmt.setInt(2, id_member);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                String updateBook = "UPDATE books SET available = TRUE WHERE id_book = ?";
                try (PreparedStatement stmtBook = conn.prepareStatement(updateBook)) {
                    stmtBook.setInt(1, id_book);
                    stmtBook.executeUpdate();
                }
            } else {
                System.out.println("⚠️ No active loan found for this book and member.");
            }
        }
    }

    @Override
    public void showLoansReport () throws SQLException {
        String sql = "SELECT b.id_book, b.title, COUNT(l.id_loan) AS borrow_count " +
                "FROM books b " +
                "LEFT JOIN loans l ON b.id_book = l.id_book " +
                "GROUP BY b.id_book, b.title " +
                "ORDER BY borrow_count DESC " +
                "LIMIT 4";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n📊 Top 4 Most Borrowed Books:");
            System.out.println("====================================");

            int rank = 1;
            while (rs.next()) {
                String title = rs.getString("title");
                int count = rs.getInt("borrow_count");

                System.out.println("    🔹 " +rank +  ". Title: " + title + " (Borrowed " + count + " times)");
                rank++;
            }
            System.out.println("====================================\n");
        }
    }

}
