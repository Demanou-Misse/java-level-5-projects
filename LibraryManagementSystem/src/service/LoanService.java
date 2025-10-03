package service;

import dao.implement.LoanDAOImpl;
import model.Book;
import model.Member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoanService {
    Scanner input = new Scanner(System.in);
    LoanDAOImpl loanDAOImpl = new LoanDAOImpl();
    BookService bookService = new BookService();
    MemberService memberService = new MemberService();

    // Borrow Book
    public void borrowBook() {
        int id_member;
        int id_book;

        System.out.println("\n******** 🧾 INFORMATIONS ********");
        bookService.allBookAvailable();
        memberService.showAllMember();

        System.out.println("\n--- 📖 Borrow a Book ---");
        while (true) {
            System.out.print("Enter Member ID: ");
            String userInput = input.nextLine().trim();
            if (userInput.isEmpty()) {
                System.out.println("❌ ID cannot be empty.");
                continue;
            }
            id_member = validNumber(userInput);
            if (id_member != 0) break;
        }

        while (true) {
            System.out.print("Enter Book ID: ");
            String userInput = input.nextLine().trim();
            if (userInput.isEmpty()) {
                System.out.println("❌ ID cannot be empty.");
                continue;
            }
            id_book= validNumber(userInput);
            if (id_book != 0) break;
        }

        try {
            loanDAOImpl.borrowBook(id_book, id_member);
            System.out.println("✅ Borrow successful!");
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }

    }

    // Valid Number
    public int validNumber (String userInput) {
        try {
            int number = Integer.parseInt(userInput);

            if (number > 0) return number;
            System.out.println("❌ ID must be greater than 0.");
        } catch (NumberFormatException e) {
            System.err.println("❌ Invalid Input");
        }
        return 0;
    }

    // Return Book
    public void returnBook() {
        int id_book;
        int id_member;
        List<Book> books = new ArrayList<>();
        List<Member> members = new ArrayList<>();

        try {
            books = bookService.bookDAOImpl.allUnavailableBooks();
            members = memberService.memberDAOImpl.allMembers();
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }


        System.out.println("\n******** 🧾 INFORMATIONS ********");
        bookService.allUnavailableBooks();
        memberService.showAllMember();


        System.out.println("\n--- 🔄 Return a Book ---");
        while (true) {
            System.out.print("Enter Member ID: ");
            String userInput = input.nextLine().trim();
            if (userInput.isEmpty()) {
                System.out.println("❌ ID cannot be empty.");
                continue;
            }
            id_member = validNumber(userInput);
            if (id_member != 0) break;
        }

        while (true) {
            System.out.print("Enter Book ID: ");
            String userInput = input.nextLine().trim();
            if (userInput.isEmpty()) {
                System.out.println("❌ ID cannot be empty.");
                continue;
            }
            id_book= validNumber(userInput);
            if (id_book != 0) break;
        }

        try {
            loanDAOImpl.returnBook(id_book, id_member);

            for (Book book : books) {
                for (Member member : members) {
                    if (book.getId_book() == id_book && member.getId_member() == id_member) {
                        System.out.println("✅ The book '" + book.getTitle() + "' has been successfully returned by " + member.getName());
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // Show Loans Report
    public void showLoansReport () {
        System.out.println("\n📊 Loans Report");
        try {
            loanDAOImpl.showLoansReport();
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

}
