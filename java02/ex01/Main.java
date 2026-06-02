package ex01;

import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Please pass exactly two files");
            return;
        }

        String fileA = args[0];
        String fileB = args[1];
        Map<String, Integer> freqA = TextLoader.load(fileA);
        Map<String, Integer> freqB = TextLoader.load(fileB);

        Set<String> dictionary = DictionaryBuilder.build(freqA, freqB);
        DictionaryWriter.write(dictionary);

        double similarity = SimilarityCalculator.calculate(freqA, freqB, dictionary);
        System.out.println("Similarity = " + similarity);

    }
}
