package ex01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TextLoader {

    public static Map<String, Integer> load(String file) {
        Map<String, Integer> freq = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] split = line.trim().split("\\s+");

                for (String word : split) {
                    if (word.isEmpty())
                        continue;
                    freq.put(word, freq.getOrDefault(word, 0) + 1);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return freq;
    }
}