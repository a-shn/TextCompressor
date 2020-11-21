package ru.itis.utils;

import ru.itis.structures.Word;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileReader {
    public List<Integer> getIntArrayOfFile(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.FileReader(filepath));
        int symbol;
        List<Integer> intList = new ArrayList<>();
        while ((symbol = reader.read()) != -1) {
            intList.add(symbol);
        }
        intList.add(-1);
        return intList;
    }

    public List<List<Integer>> getIntsOfWords(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.FileReader(filepath));
        int symbol;
        List<List<Integer>> words = new ArrayList<>();
        List<Integer> word = new ArrayList<>();
        while ((symbol = reader.read()) != -1) {
            if (symbol >= 32 && symbol <= 64) {
                words.add(word);
                words.add(Collections.singletonList(symbol));
                word = new ArrayList<>();
            }
            word.add(symbol);
        }
//        int2List.add(-1);
        return words;
    }

    public List<Word> getWords(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.FileReader(filepath));
        int symbol;
        List<Word> words = new ArrayList<>();
        List<Integer> word = new ArrayList<>();
        while ((symbol = reader.read()) != -1) {
            if (symbol >= 32 && symbol <= 64) {
                words.add(Word.builder().wordInts(word).build());
                words.add(Word.builder().wordInts(Collections.singletonList(symbol)).build());
                word = new ArrayList<>();
            }
            word.add(symbol);
        }
        return words;
    }
}
