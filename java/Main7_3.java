import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
}

class Student {
    @Column(name = "student_name")
    String name;

    @Column(name = "student_age")
    int age;

    @Column(name = "student_city")
    String city;

    public String toString() {
        return "Name: " + name
            + ", Age: " + age
            + ", City: " + city;
    }
}

public class Main7_3 {

    public static void populate(
            Object obj, String[] header, String[] data)
            throws Exception {

        Class<?> cls = obj.getClass();

        for (Field field : cls.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Column.class)) {
                continue;
            }

            Column column = field.getAnnotation(Column.class);
            String columnName = column.name();

            for (int i = 0; i < header.length; i++) {
                if (header[i].equals(columnName)) {
                    if (i >= data.length) {
                        break;
                    }

                    field.setAccessible(true);

                    if (field.getType() == String.class) {
                        field.set(obj, data[i]);
                    } else if (field.getType() == int.class) {
                        field.setInt(obj, Integer.parseInt(data[i]));
                    }

                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String[] header = {
            "student_name", "student_age"
        };

        String[] data = {
            "Bhargavi", "20"
        };

        Student s = new Student();
        populate(s, header, data);

        System.out.println(s);
    }
}
