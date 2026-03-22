package task1.version2;

import java.util.ArrayList;
import java.util.List;

// НЕ реалізує Serializable
public abstract class Human {
    private String firstName;
    private String lastName;

    public Human() {}
    public Human(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    @Override
    public String toString() { return firstName + " " + lastName; }
}

// НЕ реалізує Serializable
class Author extends Human {
    public Author() { super(); }
    public Author(String firstName, String lastName) { super(firstName, lastName); }
    @Override
    public String toString() { return "Автор: " + super.toString(); }
}

// НЕ реалізує Serializable
class Book {
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
    public String toString() {
        return String.format("Книга '%s' (%d рік, видання %d), Автори: %s", name, publishYear, edition, authors);
    }
}

// НЕ реалізує Serializable
class BookReader extends Human {
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