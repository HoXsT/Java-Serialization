package task1.version3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Author extends Human implements Externalizable {

    public Author() { super(); }

    public Author(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out); // Зберігаємо поля базового класу
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in); // Відновлюємо поля базового класу
    }

    @Override
    public String toString() { return "Автор: " + super.toString(); }
}