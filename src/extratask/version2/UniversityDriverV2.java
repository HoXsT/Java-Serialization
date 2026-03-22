package extratask.version2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Teacher implements Serializable {
    private String name;
    public Teacher(String name) { this.name = name; }
    public String getName() { return name; }
    @Override public String toString() { return name; }
}

// НЕ реалізує Serializable
class Subject {
    private String title;
    private List<Teacher> teachers = new ArrayList<>();

    public Subject(String title) { this.title = title; }
    public void addTeacher(Teacher t) { teachers.add(t); }
    public String getTitle() { return title; }
    public List<Teacher> getTeachers() { return teachers; }
    @Override public String toString() { return title + " (Викладачі: " + teachers + ")"; }
}

class Student implements Serializable {
    private String name;
    private transient List<Subject> subjects = new ArrayList<>(); // transient, бо Subject не Serializable
    private transient int score; // transient для ручного шифрування

    public Student(String name, int score) { this.name = name; this.score = score; }
    public void addSubject(Subject s) { subjects.add(s); }

    // Ручне збереження + ШИФРУВАННЯ
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // Зберігає name

        // 1. Шифруємо бали (XOR)
        int encryptedScore = this.score ^ 12345;
        out.writeInt(encryptedScore);

        //Вручну зберігаємо несеріалізований Subject
        out.writeInt(subjects.size());
        for (Subject s : subjects) {
            out.writeObject(s.getTitle());
            out.writeInt(s.getTeachers().size());
            for (Teacher t : s.getTeachers()) {
                out.writeObject(t); // Teacher є Serializable
            }
        }
    }

    // Ручне відновлення + ДЕШИФРУВАННЯ
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        //Дешифруємо бали (XOR з тим самим ключем повертає оригінал)
        int encryptedScore = in.readInt();
        this.score = encryptedScore ^ 12345;

        //Вручну відновлюємо Subject
        int size = in.readInt();
        subjects = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Subject s = new Subject((String) in.readObject());
            int tSize = in.readInt();
            for (int j = 0; j < tSize; j++) {
                s.addTeacher((Teacher) in.readObject());
            }
            subjects.add(s);
        }
    }

    @Override public String toString() {
        return "Студент " + name + " | Бали: " + score + " | Вивчає: " + subjects;
    }
}

public class UniversityDriverV2 {
    public static void main(String[] args) throws Exception {
        Teacher t1 = new Teacher("Олексій Павлович");
        Subject s1 = new Subject("Мат. методи та технології");
        s1.addTeacher(t1);

        Student student = new Student("Олександра", 88);
        student.addSubject(s1);

        System.out.println("ПОЧАТКОВИЙ СТАН (V2):");
        System.out.println(student);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("univ_v2.dat"))) {
            out.writeObject(student);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("univ_v2.dat"))) {
            Student restoredStudent = (Student) in.readObject();
            System.out.println("\nВІДНОВЛЕНИЙ СТАН (V2 - Бали успішно розшифровано!):");
            System.out.println(restoredStudent);
        }
    }
}