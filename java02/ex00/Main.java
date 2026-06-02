
import java.util.Map;

public class Main {
        public static void main(String[] args) {

                Map<String, byte[]> signatures = SignatureLoader.load("signatures.txt");

                FileSignatureMatcher matcher = new FileSignatureMatcher(signatures);

                SignatureService service = new SignatureService(matcher);

                service.run();
        }
}

/** ------------------ */
/** Old approach */
/** ------------------ */

// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.FileInputStream;
// import java.io.FileNotFoundException;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.util.Arrays;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.Scanner;

// public class Main {

// public static void main(String[] args) {
// Scanner scanner = new Scanner(System.in);
// Map<String, String> formats = new HashMap<String, String>();

// loadFormats(formats);
// try (BufferedWriter result = new BufferedWriter(new
// FileWriter("result.txt"))) {

// String file = scanner.nextLine();
// while (!file.equals("42")) {
// String type = getFileType(file, formats);
// if (type != null) {
// result.write(type);
// result.write("\n");
// System.out.println("PROCESSED");
// } else {
// System.out.println("UNDEFINED");
// }
// file = scanner.nextLine();
// }

// } catch (IOException e) {
// e.printStackTrace();
// }
// scanner.close();
// }

// private static String getFileType(String file, Map<String, String> formats) {

// try (FileInputStream reader = new FileInputStream(file)) {
// byte[] buffer = new byte[16];
// int readBytes = reader.read(buffer);

// if (readBytes == -1)
// return null;

// for (Map.Entry<String, String> entry : formats.entrySet()) {

// String[] parts = entry.getValue().split(" ");
// byte[] signature = new byte[parts.length];

// for (int i = 0; i < signature.length; i++) {
// signature[i] = (byte) Integer.parseInt(parts[i], 16);
// }

// if (readBytes >= signature.length
// && Arrays.equals(signature, Arrays.copyOf(buffer, signature.length))) {
// return entry.getKey();
// }
// }

// } catch (FileNotFoundException e) {
// e.printStackTrace();
// } catch (IOException e1) {
// e1.printStackTrace();
// }

// return null;
// }

// private static void loadFormats(Map<String, String> map) {
// try (BufferedReader reader = new BufferedReader(new
// FileReader("signatures.txt"))) {
// String line = reader.readLine();

// while (line != null) {
// String[] parts = line.split(",");
// map.put(parts[0], parts[1]);
// line = reader.readLine();
// }
// } catch (FileNotFoundException e) {
// e.printStackTrace();
// } catch (IOException e1) {
// e1.printStackTrace();
// }
// ;
// }
// }
