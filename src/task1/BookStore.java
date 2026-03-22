package task1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookStore implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private List<Book> books;

    public BookStore() {
        books = new ArrayList<>();
    }

    public BookStore(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Книжкове сховище '").append(name).append("':\n");
        for (Book b : books) {
            sb.append("  - ").append(b.toString()).append("\n");
        }
        return sb.toString();
    }
}