package ex01;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DictionaryBuilder {

    public static Set<String> build(Map<String, Integer> freqA, Map<String, Integer> freqB) {
        Set<String> dictionary = new HashSet<>();

        dictionary.addAll(freqA.keySet());
        dictionary.addAll(freqB.keySet());
        return dictionary;
    }
}
