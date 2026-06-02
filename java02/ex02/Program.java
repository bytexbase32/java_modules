package ex02;

import java.nio.file.*;
import java.io.IOException;
import java.util.*;

public class Program {

    public static void main(String[] args) throws IOException {

        if (args.length == 0 || !args[0].startsWith("--current-folder=")) {
            System.out.println("Usage: --current-folder=PATH");
            return;
        }

        Path currentDir = Paths.get(args[0].split("=")[1]).toAbsolutePath().normalize();

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println(currentDir);
            System.out.println("->");

            String input = scanner.nextLine().trim();

            if (input.equals("exit")) {
                break;
            }

            if (input.equals("ls")) {
                list(currentDir);
            }

            else if (input.startsWith("cd ")) {
                String target = input.substring(3).trim();

                Path newPath = currentDir.resolve(target).normalize();

                if (Files.isDirectory(newPath)) {
                    currentDir = newPath;
                }
            }

            else if (input.startsWith("mv ")) {

                String[] parts = input.split("\\s+");

                if (parts.length != 3)
                    continue;

                Path source = currentDir.resolve(parts[1]).normalize();
                Path target = currentDir.resolve(parts[2]).normalize();

                try {
                    Files.move(source, target);
                } catch (IOException e) {
                    System.out.println("Move failed");
                }
            }
        }

        scanner.close();
    }

    private static void list(Path dir) throws IOException {

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {

            for (Path p : stream) {

                long size = 0;

                if (Files.isDirectory(p)) {
                    size = folderSize(p);
                } else {
                    size = Files.size(p);
                }

                System.out.println(
                        p.getFileName() + " " + (size / 1024) + " KB");
            }
        }
    }

    private static long folderSize(Path path) throws IOException {

        long total = 0;

        if (Files.isDirectory(path)) {

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {

                for (Path p : stream) {
                    total += folderSize(p);
                }
            }
        } else {
            total += Files.size(path);
        }

        return total;
    }
}