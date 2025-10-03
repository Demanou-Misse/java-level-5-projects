package model;

import java.util.Date;

public class Loan {

    private final int id_loan;
    private final int id_member;
    private final int id_book;
    private final Date borrowDate;
    private Date returnDate;

    public Loan (int id_loan, int id_member, int id_book, Date borrowDate) {
        this.id_loan = id_loan;
        this.id_member = id_member;
        this.id_book = id_book;
        this.borrowDate = borrowDate;
        this.returnDate = null;
    }

    public int getId_loan() {
        return id_loan;
    }
    public int getId_member() {
        return id_member;
    }
    public int getId_book() {
        return id_book;
    }
    public Date getBorrowDate() {
        return borrowDate;
    }
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }


}
