package ex01;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class DictionaryWriter {

    public static void write(Set<String> dictionary) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dictionary.txt"))) {
            Set<String> sortedDictionary = new TreeSet<>(dictionary);
            for (String word : sortedDictionary) {
                writer.write(word);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
