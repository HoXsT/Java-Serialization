package task1.version2;

import java.io.*;

public class LibraryDriverV2 {

    public static void serializeObject(String fileName, Object obj) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName))) {
            os.writeObject(obj);
            System.out.println("-> Успішна серіалізація (Custom) у файл: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deSerializeObject(String fileName) {
        Object obj = null;
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName))) {
            obj = is.readObject();
            System.out.println("-> Успішна десеріалізація (Custom) з файлу: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {
        Library library = new Library("Наукова бібліотека ХНУ (Версія 2)");

        BookStore store1 = new BookStore("Програмування");
        Book b1 = new Book("Clean Code", 2008, 1);
        b1.addAuthor(new Author("Robert", "Martin"));

        store1.addBook(b1);
        library.addStore(store1);

        BookReader reader1 = new BookReader("Олександр", "Споров", 999);
        reader1.borrowBook(b1);
        library.addReader(reader1);

        System.out.println("ПОЧАТКОВИЙ СТАН СИСТЕМИ (V2):");
        System.out.println(library);

        String filename = "library_v2.dat";
        System.out.println("Виконується ручна серіалізація...");
        serializeObject(filename, library);

        System.out.println("\nВиконується ручна десеріалізація...");
        Library restoredLibrary = (Library) deSerializeObject(filename);

        System.out.println("\nВІДНОВЛЕНИЙ СТАН СИСТЕМИ (V2):");
        if (restoredLibrary != null) {
            System.out.println(restoredLibrary);
        }
    }
}