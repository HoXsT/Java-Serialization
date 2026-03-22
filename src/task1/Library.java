package task1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private List<BookStore> stores;
    private List<BookReader> readers;

    public Library() {
        stores = new ArrayList<>();
        readers = new ArrayList<>();
    }

    public Library(String name) {
        this.name = name;
        this.stores = new ArrayList<>();
        this.readers = new ArrayList<>();
    }

    public void addStore(BookStore store) { this.stores.add(store); }
    public void addReader(BookReader reader) { this.readers.add(reader); }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<BookStore> getStores() { return stores; }
    public void setStores(List<BookStore> stores) { this.stores = stores; }
    public List<BookReader> getReaders() { return readers; }
    public void setReaders(List<BookReader> readers) { this.readers = readers; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Бібліотека '").append(name).append("' ===\n\n");
        sb.append("СХОВИЩА:\n");
        for (BookStore store : stores) {
            sb.append(store.toString());
        }
        sb.append("\nЧИТАЧІ:\n");
        for (BookReader reader : readers) {
            sb.append(reader.toString());
        }
        return sb.toString();
    }
}