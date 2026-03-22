package task1.version2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private List<BookStore> stores; // BookStore є Serializable, тому збережеться автоматично

    // Поле transient, оскільки BookReader не є Serializable
    private transient List<BookReader> readers;

    public Library(String name) {
        this.name = name;
        this.stores = new ArrayList<>();
        this.readers = new ArrayList<>();
    }

    public void addStore(BookStore store) { this.stores.add(store); }
    public void addReader(BookReader reader) { this.readers.add(reader); }

    // Ручне збереження
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // Зберігає name та stores
        out.writeInt(readers.size());
        for (BookReader r : readers) {
            out.writeObject(r.getFirstName());
            out.writeObject(r.getLastName());
            out.writeInt(r.getRegistrationNumber());

            // Зберігаємо книги, що взяті читачем
            out.writeInt(r.getBorrowedBooks().size());
            for (Book b : r.getBorrowedBooks()) {
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
    }

    // Ручне відновлення
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Відновлює name та stores
        int size = in.readInt();
        readers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            BookReader r = new BookReader((String) in.readObject(), (String) in.readObject(), in.readInt());
            int borrowedSize = in.readInt();
            for (int j = 0; j < borrowedSize; j++) {
                Book b = new Book((String) in.readObject(), in.readInt(), in.readInt());
                int authorSize = in.readInt();
                for (int k = 0; k < authorSize; k++) {
                    b.addAuthor(new Author((String) in.readObject(), (String) in.readObject()));
                }
                r.borrowBook(b);
            }
            readers.add(r);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Бібліотека '").append(name).append("' ===\n\nСХОВИЩА:\n");
        for (BookStore store : stores) sb.append(store.toString());
        sb.append("\nЧИТАЧІ:\n");
        for (BookReader reader : readers) sb.append(reader.toString());
        return sb.toString();
    }
}