import java.io.*;
import java.util.*;

public class SignatureLoader {

    public static Map<String, byte[]> load(String filePath) {
        Map<String, byte[]> map = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0].trim();
                String[] hex = parts[1].trim().split(" ");

                byte[] signature = new byte[hex.length];

                for (int i = 0; i < hex.length; i++) {
                    signature[i] = (byte) Integer.parseInt(hex[i], 16);
                }

                map.put(type, signature);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}