package task1.version3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class BookStore implements Externalizable {
    private String name;
    private List<Book> books;

    public BookStore() { books = new ArrayList<>(); }

    public BookStore(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) { this.books.add(book); }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(books.size());
        for (Book b : books) {
            b.writeExternal(out);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        int count = in.readInt();
        books = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Book b = new Book();
            b.readExternal(in);
            books.add(b);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Книжкове сховище '").append(name).append("':\n");
        for (Book b : books) sb.append("  - ").append(b.toString()).append("\n");
        return sb.toString();
    }
}