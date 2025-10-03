package model;

public class Book {

    private int id_book;
    private final String title;
    private final String author;
    private final String category;
    private Boolean available;

    public Book (String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = true;

    }

    public Book (int id_book, String title, String author, String category) {
        this.id_book = id_book;
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = true;

    }

    public int getId_book() {
        return id_book;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "ID: " + id_book + " ; Title: " + title + " ; Author: " + author + " ; Category: " + category;
    }

}
