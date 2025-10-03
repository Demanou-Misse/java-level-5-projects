package dao;

import java.sql.SQLException;

public interface LoanDAO {

    void borrowBook (int id_book, int id_member) throws SQLException;
    void returnBook (int id_book, int id_member) throws SQLException;
    void showLoansReport () throws SQLException;

}
