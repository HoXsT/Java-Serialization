package task1;

import java.util.ArrayList;
import java.util.List;

public class BookReader extends Human {
    private static final long serialVersionUID = 1L;
    private int registrationNumber;
    private List<Book> borrowedBooks;

    public BookReader() {
        super();
        borrowedBooks = new ArrayList<>();
    }

    public BookReader(String firstName, String lastName, int registrationNumber) {
        super(firstName, lastName);
        this.registrationNumber = registrationNumber;
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        this.borrowedBooks.add(book);
    }

    public int getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(int registrationNumber) { this.registrationNumber = registrationNumber; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }
    public void setBorrowedBooks(List<Book> borrowedBooks) { this.borrowedBooks = borrowedBooks; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Читач [№").append(registrationNumber).append("] ").append(super.toString()).append("\n");
        sb.append("  Взяті книги:\n");
        if (borrowedBooks.isEmpty()) {
            sb.append("    (немає)\n");
        } else {
            for (Book b : borrowedBooks) {
                sb.append("    - ").append(b.getName()).append("\n");
            }
        }
        return sb.toString();
    }
}