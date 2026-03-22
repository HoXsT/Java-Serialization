package task1.version3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class Library implements Externalizable {
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);

        out.writeInt(stores.size());
        for (BookStore store : stores) {
            store.writeExternal(out);
        }

        out.writeInt(readers.size());
        for (BookReader reader : readers) {
            reader.writeExternal(out);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();

        int storesSize = in.readInt();
        stores = new ArrayList<>();
        for (int i = 0; i < storesSize; i++) {
            BookStore store = new BookStore();
            store.readExternal(in);
            stores.add(store);
        }

        int readersSize = in.readInt();
        readers = new ArrayList<>();
        for (int i = 0; i < readersSize; i++) {
            BookReader reader = new BookReader();
            reader.readExternal(in);
            readers.add(reader);
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