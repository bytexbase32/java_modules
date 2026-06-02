import java.io.*;
import java.util.*;

public class SignatureService {

    private final FileSignatureMatcher matcher;
    private static final String EXIT = "42";

    public SignatureService(FileSignatureMatcher matcher) {
        this.matcher = matcher;
    }

    public void run() {

        try (Scanner scanner = new Scanner(System.in);
                OutputStream out = new FileOutputStream("result.txt");) {

            while (true) {
                String input = scanner.nextLine();

                if (input.equals(EXIT))
                    break;

                String type = matcher.match(input);

                if (type != null) {
                    out.write(type.getBytes());
                    out.write('\n');
                    System.out.println("PROCESSED");
                } else {
                    System.out.println("UNDEFINED");
                }
            }

        } catch (IOException e) {
            // System.out.println(e.getMessage());
        }
    }
}