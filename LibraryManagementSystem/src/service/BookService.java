package service;

import dao.implement.BookDAOImpl;
import model.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BookService {
    Scanner input = new Scanner(System.in);
    BookDAOImpl bookDAOImpl = new BookDAOImpl();

    // Add Book
    public void addBook () {
        String title;
        String author;
        String category;
        System.out.println("\n📗 Add New Book");

        while (true) {
            System.out.print("Title: ");
            title = input.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("❌ Title cannot be empty.");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("Author: ");
            author = input.nextLine().trim();
            if (author.isEmpty()) {
                System.out.println("❌ Author cannot be empty.");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("Category: ");
            category = input.nextLine().trim();
            if (category.isEmpty()) {
                System.out.println("❌ Category cannot be empty.");
                continue;
            }
            break;
        }

        try {
            Book book = new Book(title, author, category);
            bookDAOImpl.addBook(book);
            System.out.println("✅ Book added successfully!");
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // Search Book
    public void searchBook() {
        String title;
        System.out.println("\n🔍 Search Book\n");

        while (true) {
            System.out.print("Search by Title: ");
            title = input.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("❌ Title cannot be empty.");
                continue;
            }
            break;
        }

        System.out.println("📚 Results:");
        try {
            bookDAOImpl.searchBook(title);
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // All Available Books
    public void allBookAvailable() {
        System.out.println("\n📚 All Available Book\n");

        try {
            List<Book> books = bookDAOImpl.allBooksAvailable();
            System.out.println("*".repeat(40));
            for (Book book : books) {
                System.out.println("\n    🔹 ID: " + book.getId_book() + " | TITLE: " + book.getTitle() + " | AUTHOR: " + book.getAuthor() + " | CATEGORY: " + book.getCategory());
            }
            System.out.println("*".repeat(40));
        } catch (SQLException e) {
            System.err.println ("❌ Error: " + e.getMessage());
        }
    }

    // All Unavailable Books
    public void allUnavailableBooks() {
        System.out.println("\n📚 All Book to Return\n");

        try {
            List<Book> books = bookDAOImpl.allUnavailableBooks();
            System.out.println("*".repeat(40));
            for (Book book : books) {
                System.out.println("    🔹 ID: " + book.getId_book() + " | TITLE: " + book.getTitle() + " | AUTHOR: " + book.getAuthor() + " | CATEGORY: " + book.getCategory());
            }
            System.out.println("*".repeat(40));
        } catch (SQLException e) {
            System.err.println ("❌ Error: " + e.getMessage());
        }
    }

}
