import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;
import java.util.*;

public class Main11_2 {
    public static void main(String[] args) {
        String[] files = {"log1.txt", "log2.txt", "log3.txt"};
        String keyword = "ERROR";

        int totalLines = 0;
        int matchingLines = 0;

        try {
            for (String file : files) {
                Path path = Paths.get(file);

                if (!Files.exists(path)) {
                    Files.write(path, Arrays.asList(
                        "Application started",
                        "ERROR: Something went wrong",
                        "Application running"
                    ));
                }

                int fileLines = 0;
                int fileMatches = 0;

                System.out.println("\nFile: " + file);

                try (Scanner sc = new Scanner(path)) {
                    while (sc.hasNextLine()) {
                        String line = sc.nextLine();
                        fileLines++;

                        if (line.contains(keyword)) {
                            fileMatches++;
                        }
                    }
                }

                BasicFileAttributes attr =
                    Files.readAttributes(
                        path, BasicFileAttributes.class);

                System.out.println("File size: "
                    + attr.size() + " bytes");
                System.out.println("Lines: " + fileLines);
                System.out.println("Matching lines: "
                    + fileMatches);

                totalLines += fileLines;
                matchingLines += fileMatches;
            }

            System.out.println("\nTotal lines: " + totalLines);
            System.out.println("Total lines containing "
                + keyword + ": " + matchingLines);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
