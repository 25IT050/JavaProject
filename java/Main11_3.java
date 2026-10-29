import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main11_3 {
    public static void main(String[] args) {
        Path folder = Paths.get("lab-NN");
        Path report = Paths.get("folder_report.txt");

        try {
            if (!Files.exists(folder)) {
                Files.createDirectories(folder);
                Files.write(folder.resolve("sample.txt"),
                    java.util.Arrays.asList(
                        "This is a sample file."));
            }

            try (BufferedWriter writer =
                    new BufferedWriter(
                        new FileWriter(report.toFile()))) {

                writer.write("FOLDER TREE REPORT");
                writer.newLine();
                writer.write("==================");
                writer.newLine();

                Files.walkFileTree(folder,
                    new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult visitFile(
                                Path file,
                                BasicFileAttributes attrs)
                                throws IOException {

                            String name = file.getFileName().toString();
                            long size = attrs.size();
                            String modified =
                                attrs.lastModifiedTime().toString();

                            String details =
                                "Name: " + name
                                + " | Size: " + size + " bytes"
                                + " | Modified: " + modified;

                            writer.write(details);
                            writer.newLine();

                            System.out.println(details);

                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult visitFileFailed(
                                Path file, IOException exc)
                                throws IOException {

                            writer.write("Cannot access: " + file);
                            writer.newLine();

                            return FileVisitResult.CONTINUE;
                        }
                    });

                System.out.println(
                    "\nReport saved to folder_report.txt");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
