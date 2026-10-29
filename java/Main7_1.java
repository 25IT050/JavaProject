import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class SignupForm {
    @NotBlank
    String name;

    @NotBlank
    @MaxLength(30)
    String email;

    @NotBlank
    @MaxLength(10)
    String username;

    SignupForm(String name, String email, String username) {
        this.name = name;
        this.email = email;
        this.username = username;
    }
}

public class Main7_1 {

    public static List<String> validate(Object obj)
            throws IllegalAccessException {
        List<String> errors = new ArrayList<>();

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(obj);
            String text = value == null ? "" : value.toString();

            if (field.isAnnotationPresent(NotBlank.class)) {
                if (text.trim().isEmpty()) {
                    errors.add(field.getName() + " must not be blank");
                }
            }

            if (field.isAnnotationPresent(MaxLength.class)) {
                MaxLength max =
                    field.getAnnotation(MaxLength.class);

                if (text.length() > max.value()) {
                    errors.add(field.getName()
                        + " must not exceed "
                        + max.value() + " characters");
                }
            }
        }

        return errors;
    }

    public static void main(String[] args)
            throws IllegalAccessException {
        SignupForm form =
            new SignupForm("", "bhargavi@example.com",
                           "verylongusername");

        List<String> errors = validate(form);

        if (errors.isEmpty()) {
            System.out.println("Form is valid");
        } else {
            System.out.println("Validation Errors:");

            for (String error : errors) {
                System.out.println(error);
            }
        }
    }
}
