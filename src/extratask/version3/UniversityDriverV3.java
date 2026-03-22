package extratask.version3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Teacher implements Externalizable {
    private String name;
    public Teacher() {} // Обов'язково для Externalizable
    public Teacher(String name) { this.name = name; }

    @Override public void writeExternal(ObjectOutput out) throws IOException { out.writeObject(name); }
    @Override public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException { name = (String) in.readObject(); }
    @Override public String toString() { return name; }
}

class Subject implements Externalizable {
    private String title;
    private List<Teacher> teachers = new ArrayList<>();

    public Subject() {}
    public Subject(String title) { this.title = title; }
    public void addTeacher(Teacher t) { teachers.add(t); }

    @Override public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(title);
        out.writeInt(teachers.size());
        for (Teacher t : teachers) t.writeExternal(out);
    }

    @Override public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        title = (String) in.readObject();
        int size = in.readInt();
        teachers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Teacher t = new Teacher();
            t.readExternal(in);
            teachers.add(t);
        }
    }
    @Override public String toString() { return title + " (Викладачі: " + teachers + ")"; }
}

class Student implements Externalizable {
    private String name;
    private List<Subject> subjects = new ArrayList<>();
    private int score;

    public Student() {}
    public Student(String name, int score) { this.name = name; this.score = score; }
    public void addSubject(Subject s) { subjects.add(s); }

    @Override public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(score);
        out.writeInt(subjects.size());
        for (Subject s : subjects) s.writeExternal(out);
    }

    @Override public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        score = in.readInt();
        int size = in.readInt();
        subjects = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Subject s = new Subject();
            s.readExternal(in);
            subjects.add(s);
        }
    }
    @Override public String toString() { return "Студент " + name + " | Бали: " + score + " | Вивчає: " + subjects; }
}

public class UniversityDriverV3 {
    public static void main(String[] args) throws Exception {
        Teacher t1 = new Teacher("Лілія Вікторівна");
        Subject s1 = new Subject("Філософія");
        s1.addTeacher(t1);

        Student student = new Student("Вова", 100);
        student.addSubject(s1);

        System.out.println("ПОЧАТКОВИЙ СТАН (V3):");
        System.out.println(student);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("univ_v3.dat"))) {
            student.writeExternal(out);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("univ_v3.dat"))) {
            Student restoredStudent = new Student();
            restoredStudent.readExternal(in);
            System.out.println("\nВІДНОВЛЕНИЙ СТАН (V3):");
            System.out.println(restoredStudent);
        }
    }
}