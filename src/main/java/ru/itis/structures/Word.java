package ru.itis.structures;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Word {
    private List<Integer> wordInts;

    @Override
    public boolean equals(Object obj) {
        Word word2 = (Word) obj;
        List<Integer> wordInts2 = word2.wordInts;
        if (wordInts.size() != wordInts2.size()) {
            return false;
        }
        for (int i = 0; i < wordInts.size(); i++) {
            if (!wordInts.get(i).equals(wordInts2.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        for (int i = 0; i < wordInts.size(); i++) {
            hash = hash * 31 + wordInts.get(i);
        }
        return hash;
    }
}
