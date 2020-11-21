package ru.itis;

import ru.itis.structures.Word;
import ru.itis.utils.FileReader;
import ru.itis.utils.HuffmanEncoder;

import java.io.IOException;
import java.util.*;

import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) throws IOException {
//        final int BUFFER_SIZE = 5;
        FileReader fileReader = new FileReader();
//        DeltaFilter deltaFilter = new DeltaFilter();
//        SlidingWindowLZ77 slidingWindowLZ77 = new SlidingWindowLZ77();
        String filepath = "/home/xiu-xiu/PythonProjects/Archivator/data/voina_i_mir.txt";
        List<List<Integer>> words = fileReader.getIntsOfWords(filepath);

//        Word word1 = Word.builder().wordInts(asList(1,2,3,4,5)).build();
//        Word word2 = Word.builder().wordInts(asList(1)).build();
//        System.out.println(word1.hashCode());
//        System.out.println(word2.hashCode());

//        List<Integer> deltas = deltaFilter.getDeltas(fileInts);
//        System.out.println("Unique elements in raw array: " + (new HashSet<>(fileInts).size()));
//        System.out.println("Unique elements in deltas array: " + (new HashSet<>(deltas).size()));
//
//        String test = "abacabacabadacadaadaadaaa";
//        List<Integer> testInts = new ArrayList<>();
//        for (char c : test.toCharArray()) {
//            testInts.add((int) c);
//        }
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < 1024000; i++) {
//            stringBuilder.append("1");
//        }
//        BigInteger int1 = new BigInteger(stringBuilder.toString());
//        BigInteger int2 = new BigInteger(stringBuilder.toString());
//        System.out.println(int1);
//        System.out.println(int2);
//        System.out.println(int1.divide(int2));
//
//
//        List<Node> nodesOnDeltas = slidingWindowLZ77.getNodesList(deltas, BUFFER_SIZE);
        HuffmanEncoder encoder = new HuffmanEncoder();
        List<Integer> encodedWords = encoder.encodeWords(words);

        System.out.println(encodedWords.size());
        System.out.println(words.size());
    }
}
