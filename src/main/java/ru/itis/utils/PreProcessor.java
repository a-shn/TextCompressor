package ru.itis.utils;

import java.util.ArrayList;
import java.util.List;

public class PreProcessor {
    public List<List<Integer>> divideSuffixes(List<List<Integer>> words, int m) {
        List<List<Integer>> newWords = new ArrayList<>();
        for (List<Integer> word : words) {
            if (word.size() > m * 2) {
                newWords.add(word.subList(0, word.size() - m));
                newWords.add(word.subList(word.size() - m, word.size()));
            } else {
                newWords.add(word);
            }
        }
        return newWords;
    }
}
