import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Driver3 {
    public static void main(String[] args) {
        String template = "Dear {name}, order {id} ships {date}.";
        String[] names = {"name", "id"};
        String[] values = {"Riya", "A07"};
        Pattern pattern = Pattern.compile("\\{(\\w+)\\}");
        Matcher matcher = pattern.matcher(template);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String placeholderKey = matcher.group(1);
            String replacement = "[?]";
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(placeholderKey)) {
                    replacement = values[i];
                    break;
                }
            }
            matcher.appendReplacement(result, replacement);
        }
        matcher.appendTail(result);
        System.out.println("Original Template:");
        System.out.println(template);
        System.out.println("\nFilled Template:");
        System.out.println(result.toString());
    }
}