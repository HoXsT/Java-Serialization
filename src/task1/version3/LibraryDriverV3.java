package task1.version3;

import java.io.*;

public class LibraryDriverV3 {

    public static void serializeObject(String fileName, Object obj) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName))) {
            os.writeObject(obj);
            System.out.println("-> Успішна серіалізація (Externalizable) у файл: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deSerializeObject(String fileName) {
        Object obj = null;
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName))) {
            obj = is.readObject();
            System.out.println("-> Успішна десеріалізація (Externalizable) з файлу: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {
        Library library = new Library("Наукова бібліотека ХНУ (Версія 3)");

        BookStore store1 = new BookStore("Філософія");
        Book b1 = new Book("Мистецтво війни", 514, 2);
        b1.addAuthor(new Author("Сунь", "Цзи"));

        store1.addBook(b1);
        library.addStore(store1);

        BookReader reader1 = new BookReader("Володимир", "Зеленський", 777);
        reader1.borrowBook(b1);
        library.addReader(reader1);

        System.out.println("ПОЧАТКОВИЙ СТАН СИСТЕМИ (V3):");
        System.out.println(library);

        String filename = "library_v3.dat";
        System.out.println("Виконується Externalizable серіалізація...");
        serializeObject(filename, library);

        System.out.println("\nВиконується Externalizable десеріалізація...");
        Library restoredLibrary = (Library) deSerializeObject(filename);

        System.out.println("\nВІДНОВЛЕНИЙ СТАН СИСТЕМИ (V3):");
        if (restoredLibrary != null) {
            System.out.println(restoredLibrary);
        }
    }
}