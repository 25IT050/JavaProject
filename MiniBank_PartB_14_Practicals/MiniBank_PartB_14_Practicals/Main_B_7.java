import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Main_B_7 {
    @Target(ElementType.FIELD) @Retention(RetentionPolicy.RUNTIME) @interface Id {}
    @Target(ElementType.FIELD) @Retention(RetentionPolicy.RUNTIME) @interface Positive { String message() default "must be > 0"; }
    @Target(ElementType.FIELD) @Retention(RetentionPolicy.RUNTIME) @interface MaxLength { int value(); }
    static class Account {
        @Id private String accountNumber;
        @MaxLength(30) private String ownerName;
        @Positive private long balance;
        Account(String n,String owner,long b) { accountNumber=n; ownerName=owner; balance=b; }
    }
    static class AnnotationValidator {
        static String[] validate(Object obj) {
            ArrayList<String> errors = new ArrayList<>();
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    if (field.isAnnotationPresent(Positive.class) && value instanceof Number n && n.longValue() <= 0)
                        errors.add(field.getName() + ": " + field.getAnnotation(Positive.class).message());
                    if (field.isAnnotationPresent(MaxLength.class) && value instanceof String s && s.length() > field.getAnnotation(MaxLength.class).value())
                        errors.add(field.getName() + ": maximum length is " + field.getAnnotation(MaxLength.class).value());
                } catch (IllegalAccessException e) { errors.add("Could not read " + field.getName()); }
            }
            return errors.toArray(new String[0]);
        }
    }
    public static void main(String[] args) {
        Account invalid = new Account("AC0001", "Riya", -100);
        String[] errors = AnnotationValidator.validate(invalid);
        for (String error : errors) System.out.println(error);
        Account valid = new Account("AC0002", "Aarav", 5000);
        System.out.println("Valid account error count: " + AnnotationValidator.validate(valid).length);
    }
}
