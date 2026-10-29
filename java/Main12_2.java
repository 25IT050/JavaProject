import java.util.*;

public class Main12_2 {
    public static void main(String[] args) {
        String[] logins = {
            "Bhargavi", "Rutvi", "Janki",
            "Bhargavi", "Priya", "Rutvi", "Amit"
        };

        HashSet<String> uniqueUsers = new HashSet<>();
        LinkedHashSet<String> orderedUsers =
            new LinkedHashSet<>();

        for (String name : logins) {
            uniqueUsers.add(name);
            orderedUsers.add(name);
        }

        System.out.println("Unique visitor count: "
            + uniqueUsers.size());

        System.out.println("First three distinct visitors:");

        int count = 0;

        for (String name : orderedUsers) {
            if (count == 3) {
                break;
            }

            System.out.println(name);
            count++;
        }
    }
}
