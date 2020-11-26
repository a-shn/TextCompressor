package ru.itis;

import ru.itis.structures.Node;
import ru.itis.structures.Word;
import ru.itis.utils.*;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.time.Period;
import java.util.*;

import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) throws IOException {
        final int BUFFER_SIZE = 10;
        FileReader fileReader = new FileReader();
        DeltaFilter deltaFilter = new DeltaFilter();
        RangeEncoder rangeEncoder = new RangeEncoder();
        SlidingWindowLZ77 slidingWindowLZ77 = new SlidingWindowLZ77();
        String filepath = "/home/xiu-xiu/PythonProjects/Archivator/data/voina_i_mir.txt";


        List<Integer> fileInts = fileReader.getIntArrayOfFile(filepath);

//        List<Integer> deltas = deltaFilter.getDeltas(fileInts);
//        System.out.println("Unique elements in raw array: " + (new HashSet<>(fileInts).size()));
//        System.out.println("Unique elements in deltas array: " + (new HashSet<>(deltas).size()));

//        List<Node> nodesOnDeltas = slidingWindowLZ77.getNodesList(fileInts, BUFFER_SIZE);
//        System.out.println(fileInts.size());
//        System.out.println(slidingWindowLZ77.transformToInts(nodesOnDeltas).size());
//        HuffmanEncoder encoder = new HuffmanEncoder();
//        List<Integer> encodedWordsByBits = encoder.encodeWords(slidingWindowLZ77.transformToLists(nodesOnDeltas));



        PreProcessor preProcessor = new PreProcessor();
        List<List<Integer>> words = fileReader.getIntsOfWords(filepath);
//        List<List<Integer>> words = fileReader.getIntPacks(filepath, 1);
//        words = preProcessor.divideSuffixes(words, 3);
        Set<List<Integer>> wordsSet = new HashSet<>(words);
        int symbolsInDict = 0;
        for (List<Integer> word : wordsSet) {
            symbolsInDict = symbolsInDict + word.size();
        }

        for (List<Integer> word : wordsSet) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (Integer symbol : word) {
                sb.append((char) symbol.intValue());
                sb2.append(symbol).append(" ");
            }
            System.out.println("|" + sb.toString() + "| " + sb2.toString());
        }

        System.out.println("unique words:" + wordsSet.size());
        System.out.println("symbols in dictionary:" + symbolsInDict);
        System.out.println("words in raw:" + words.size());
        System.out.println("symbols in raw:" + fileInts.size());
        HuffmanEncoder encoder = new HuffmanEncoder();
        List<Integer> encodedWordsByBits = encoder.encodeWords(words);
//
        DataOutputStream os = new DataOutputStream(new FileOutputStream("output.dat"));
        for (int i = 0; i < encodedWordsByBits.size() / 8; i++) {
            int b = 0;
            for (int j = 0; j < 8; j++) {
                int index = encodedWordsByBits.get(i * 8 + j);
                int pow = 1;
                for (int p = 7 - j; p >= 0; p--) {
                    pow = pow * 2;
                }
                b = b + index * pow;
            }
            byte bb = (byte) b;
            os.write(bb);
        }
    }
}
