package task1.version2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookStore implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;

    // Поле transient, оскільки Book не є Serializable
    private transient List<Book> books;

    public BookStore() { books = new ArrayList<>(); }
    public BookStore(String name) { this.name = name; this.books = new ArrayList<>(); }

    public void addBook(Book book) { this.books.add(book); }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }

    // Ручне збереження
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // Зберігаємо name
        out.writeInt(books.size()); // Зберігаємо кількість книг
        for (Book b : books) {
            out.writeObject(b.getName());
            out.writeInt(b.getPublishYear());
            out.writeInt(b.getEdition());
            out.writeInt(b.getAuthors().size());
            for (Author a : b.getAuthors()) {
                out.writeObject(a.getFirstName());
                out.writeObject(a.getLastName());
            }
        }
    }

    // Ручне відновлення
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Відновлюємо name
        int size = in.readInt();
        books = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Book b = new Book((String) in.readObject(), in.readInt(), in.readInt());
            int authorSize = in.readInt();
            for (int j = 0; j < authorSize; j++) {
                b.addAuthor(new Author((String) in.readObject(), (String) in.readObject()));
            }
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