import java.util.Scanner;

public class Driver2 {

    public static void main(String[] args) {
                String[] logs = {
                "10:05 alice Hello there",
                "10:06 bob",
                "10:07 charlie General Kenobi",
                "10:08 alice HELLO AGAIN!"
        };
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine().trim().toLowerCase();
        scanner.close();

        StringBuilder report = new StringBuilder();
        int matchesCount = 0;
        for (String line : logs) {
            String[] parts = line.split(" ", 3);
            if (parts.length < 3) {
                continue;
            }

            String time = parts[0];
            String user = parts[1];
            String message = parts[2];
            if (message.toLowerCase().contains(keyword)) {
                matchesCount++;
                report.append(time)
                        .append(" ")
                        .append(user)
                        .append(": ")
                        .append(message)
                        .append("\n");
            }
        }
        System.out.println("\nMatches: " + matchesCount);
        if (matchesCount > 0) {
            System.out.print(report.toString());
        }
    }
}