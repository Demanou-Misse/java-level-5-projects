package dao.implement;

import dao.BookDAO;
import model.Book;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    DatabaseConnection databaseConnection = new DatabaseConnection();

    @Override
    public void addBook (Book book) throws SQLException {
        String sql = "INSERT INTO books (title, author, category) VALUES (?, ?, ?)";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getCategory());
            stmt.executeUpdate();
        }
    }

    @Override
    public void searchBook (String title) throws SQLException {
        String sql = "SELECT * FROM books WHERE title LIKE ?";

        try (Connection conn = databaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + title + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.printf("    🔹 ID: %d | Title: %s | Author: %s | Available: %b%n", rs.getInt("id_book"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available"));
            }
        }
    }


    @Override
    public List<Book> allBooksAvailable() throws SQLException {
        String sql = "SELECT * FROM books WHERE available = TRUE";
        List<Book> books = new ArrayList<>();

        try (Connection conn = databaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Book book = new Book(rs.getInt("id_book"), rs.getString("title"), rs.getString("author"), rs.getString("category"));
                books.add(book);
            }
        }
        return books;
    }

    @Override
    public List<Book> allUnavailableBooks() throws SQLException {
        String sql = "SELECT * FROM books WHERE available = FALSE";
        List<Book> books = new ArrayList<>();

        try (Connection conn = databaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Book book = new Book(rs.getInt("id_book"), rs.getString("title"), rs.getString("author"), rs.getString("category"));
                books.add(book);
            }
        }
        return books;
    }

}
