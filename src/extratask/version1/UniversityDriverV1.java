package extratask.version1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Teacher implements Serializable {
    private String name;
    public Teacher(String name) { this.name = name; }
    public String getName() { return name; }
    @Override public String toString() { return name; }
}

class Subject implements Serializable {
    private String title;
    private List<Teacher> teachers = new ArrayList<>();

    public Subject(String title) { this.title = title; }
    public void addTeacher(Teacher t) { teachers.add(t); }
    public String getTitle() { return title; }
    @Override public String toString() { return title + " (Викладачі: " + teachers + ")"; }
}

class Student implements Serializable {
    private String name;
    private List<Subject> subjects = new ArrayList<>();
    private int score;

    public Student(String name, int score) { this.name = name; this.score = score; }
    public void addSubject(Subject s) { subjects.add(s); }
    public int getScore() { return score; }

    @Override public String toString() {
        return "Студент " + name + " | Бали: " + score + " | Вивчає: " + subjects;
    }
}

class Curriculum implements Serializable {
    private String planName;
    private List<Subject> subjects = new ArrayList<>();

    public Curriculum(String planName) { this.planName = planName; }
    public void addSubject(Subject s) { subjects.add(s); }

    @Override public String toString() {
        return "План '" + planName + "':\n  Предмети: " + subjects;
    }
}

public class UniversityDriverV1 {
    public static void main(String[] args) throws Exception {
        Teacher t1 = new Teacher("Олександр 'Євгенович'");
        Subject s1 = new Subject("Крос-платформне програмування");
        s1.addTeacher(t1);

        Curriculum curriculum = new Curriculum("КБ-2026");
        curriculum.addSubject(s1);

        Student student = new Student("Гліб", 95);
        student.addSubject(s1);

        System.out.println("--- ПОЧАТКОВИЙ СТАН ---");
        System.out.println(curriculum);
        System.out.println(student);

        // Серіалізація
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("univ_v1.dat"))) {
            out.writeObject(curriculum);
            out.writeObject(student);
        }

        // Десеріалізація
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("univ_v1.dat"))) {
            Curriculum restoredPlan = (Curriculum) in.readObject();
            Student restoredStudent = (Student) in.readObject();
            System.out.println("\n--- ВІДНОВЛЕНИЙ СТАН ---");
            System.out.println(restoredPlan);
            System.out.println(restoredStudent);
        }
    }
}