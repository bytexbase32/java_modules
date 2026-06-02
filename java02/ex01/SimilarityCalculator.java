package ex01;

import java.util.Map;
import java.util.Set;

public class SimilarityCalculator {

    public static double calculate(Map<String, Integer> A, Map<String, Integer> B, Set<String> dic) {
        return cosineSimilarity(A, B, dic);
    }

    private static double cosineSimilarity(Map<String, Integer> A, Map<String, Integer> B, Set<String> dic) {
        double dot = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (String word : dic) {

            int a = A.getOrDefault(word, 0);
            int b = B.getOrDefault(word, 0);

            dot += a * b;
            normA += a * a;
            normB += b * b;
        }

        if (normA == 0 || normB == 0) {
            return 0.0;
        }

        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}