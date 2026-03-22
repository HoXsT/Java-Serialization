package task1;

import java.io.*;

public class LibraryDriver {

    public static void serializeObject(String fileName, Object obj) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName))) {
            os.writeObject(obj);
            System.out.println("-> Успішна серіалізація у файл: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deSerializeObject(String fileName) {
        Object obj = null;
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName))) {
            obj = is.readObject();
            System.out.println("-> Успішна десеріалізація з файлу: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {
        // 1. Створення даних бібліотеки
        Library library = new Library("Наукова бібліотека ХНУ");

        BookStore store1 = new BookStore("Історія");
        Book b1 = new Book("Ми - з УНСО", 2021, 1);
        b1.addAuthor(new Author("Геннадій", "Ключиков"));

        Book b2 = new Book("Націократія", 1935, 1);
            b2.addAuthor(new Author("Микола", "Сціборський"));

        store1.addBook(b1);
        store1.addBook(b2);
        library.addStore(store1);

        BookReader reader1 = new BookReader("Гліб", "Бежан", 101);
        BookReader reader2 = new BookReader("Олександра", "Малолєтова", 102);

        // Видача книг
        reader1.borrowBook(b1);
        reader2.borrowBook(b2);

        library.addReader(reader1);
        library.addReader(reader2);

        // 2. Вивід початкового стану
        System.out.println("ПОЧАТКОВИЙ СТАН СИСТЕМИ:");
        System.out.println(library);

        // 3. Серіалізація
        String filename = "library_v1.dat";
        System.out.println("Виконується серіалізація...");
        serializeObject(filename, library);

        // 4. Десеріалізація
        System.out.println("\nВиконується десеріалізація...");
        Library restoredLibrary = (Library) deSerializeObject(filename);

        // 5. Вивід відновленого стану
        System.out.println("\nВІДНОВЛЕНИЙ СТАН СИСТЕМИ:");
        if (restoredLibrary != null) {
            System.out.println(restoredLibrary);
        }
    }
}