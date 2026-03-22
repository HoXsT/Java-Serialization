package task1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private List<Author> authors;
    private int publishYear;
    private int edition;

    public Book() {
        authors = new ArrayList<>();
    }

    public Book(String name, int publishYear, int edition) {
        this.name = name;
        this.publishYear = publishYear;
        this.edition = edition;
        this.authors = new ArrayList<>();
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Author> getAuthors() { return authors; }
    public void setAuthors(List<Author> authors) { this.authors = authors; }
    public int getPublishYear() { return publishYear; }
    public void setPublishYear(int publishYear) { this.publishYear = publishYear; }
    public int getEdition() { return edition; }
    public void setEdition(int edition) { this.edition = edition; }

    @Override
    public String toString() {
        return String.format("Книга '%s' (%d рік, видання %d), Автори: %s", name, publishYear, edition, authors);
    }
}