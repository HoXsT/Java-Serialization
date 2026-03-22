package task1.version3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class Book implements Externalizable {
    private String name;
    private List<Author> authors;
    private int publishYear;
    private int edition;

    public Book() { this.authors = new ArrayList<>(); }

    public Book(String name, int publishYear, int edition) {
        this.name = name;
        this.publishYear = publishYear;
        this.edition = edition;
        this.authors = new ArrayList<>();
    }

    public void addAuthor(Author author) { this.authors.add(author); }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Author> getAuthors() { return authors; }
    public void setAuthors(List<Author> authors) { this.authors = authors; }
    public int getPublishYear() { return publishYear; }
    public void setPublishYear(int publishYear) { this.publishYear = publishYear; }
    public int getEdition() { return edition; }
    public void setEdition(int edition) { this.edition = edition; }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(publishYear);
        out.writeInt(edition);
        out.writeInt(authors.size());
        for (Author a : authors) {
            a.writeExternal(out);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        publishYear = in.readInt();
        edition = in.readInt();
        int size = in.readInt();
        authors = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Author a = new Author();
            a.readExternal(in);
            authors.add(a);
        }
    }

    @Override
    public String toString() {
        return String.format("Книга '%s' (%d рік, видання %d), Автори: %s", name, publishYear, edition, authors);
    }
}