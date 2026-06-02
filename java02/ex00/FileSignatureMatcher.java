import java.io.*;
import java.util.*;

public class FileSignatureMatcher {

    private final Map<String, byte[]> signatures;
    private final int maxLen;

    public FileSignatureMatcher(Map<String, byte[]> signatures) {
        this.signatures = signatures;

        int max = 0;
        for (byte[] sig : signatures.values()) {
            max = Math.max(max, sig.length);
        }
        this.maxLen = max;
    }

    public String match(String filePath) {

        try (FileInputStream fis = new FileInputStream(filePath)) {

            byte[] buffer = new byte[maxLen];
            int read = fis.read(buffer);

            if (read <= 0)
                return null;

            for (Map.Entry<String, byte[]> entry : signatures.entrySet()) {

                byte[] sig = entry.getValue();

                if (read >= sig.length) {
                    byte[] head = Arrays.copyOf(buffer, sig.length);

                    if (Arrays.equals(sig, head)) {
                        return entry.getKey();
                    }
                }
            }

        } catch (IOException e) {
            // System.out.println(e.getMessage());
        }

        return null;
    }
}   