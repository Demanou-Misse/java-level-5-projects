package dao;

import model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO {

    void addBook (Book book) throws SQLException;
    void searchBook (String title) throws SQLException;
    List<Book> allBooksAvailable() throws SQLException;
    List<Book> allUnavailableBooks() throws SQLException;

}
