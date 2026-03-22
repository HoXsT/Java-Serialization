package task1.version3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class BookReader extends Human implements Externalizable {
    private int registrationNumber;
    private List<Book> borrowedBooks;

    public BookReader() { super(); borrowedBooks = new ArrayList<>(); }

    public BookReader(String firstName, String lastName, int registrationNumber) {
        super(firstName, lastName);
        this.registrationNumber = registrationNumber;
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book) { this.borrowedBooks.add(book); }
    public int getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(int registrationNumber) { this.registrationNumber = registrationNumber; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }
    public void setBorrowedBooks(List<Book> borrowedBooks) { this.borrowedBooks = borrowedBooks; }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeInt(registrationNumber);
        out.writeInt(borrowedBooks.size());
        for (Book b : borrowedBooks) {
            b.writeExternal(out);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        registrationNumber = in.readInt();
        int size = in.readInt();
        borrowedBooks = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Book b = new Book();
            b.readExternal(in);
            borrowedBooks.add(b);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Читач [№").append(registrationNumber).append("] ").append(super.toString()).append("\n");
        sb.append("  Взяті книги:\n");
        if (borrowedBooks.isEmpty()) { sb.append("    (немає)\n"); }
        else {
            for (Book b : borrowedBooks) sb.append("    - ").append(b.getName()).append("\n");
        }
        return sb.toString();
    }
}