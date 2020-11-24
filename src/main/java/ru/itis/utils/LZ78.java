package ru.itis.utils;

import ru.itis.structures.LZ78Node;
import ru.itis.structures.LZ78Output;

import java.util.*;

public class LZ78 {
    public LZ78Output encode(List<Integer> fileInts) {
        Set<Integer> alphabet = new HashSet<>();
        List<Integer> buffer = new ArrayList<>();
        Map<List<Integer>, Integer> dict = new HashMap<>();
        List<LZ78Node> result = new ArrayList<>();
        for (Integer fileInt : fileInts) {
            List<Integer> tmp = new ArrayList<>(buffer);
            tmp.add(fileInt);
            if (dict.containsKey(tmp)) {
                buffer.add(fileInt);
            } else {
                if (dict.containsKey(buffer)) {
                    result.add(LZ78Node.builder().pos(dict.get(buffer)).next(fileInt).build());
                } else {
                    result.add(LZ78Node.builder().pos(null).next(fileInt).build());
                }
                alphabet.add(fileInt);
                dict.put(tmp, dict.size() + 1);
                buffer = new ArrayList<>();
            }
        }
        if (buffer.size() > 0) {
            Integer lastSymbol = buffer.remove(buffer.size() - 1);
            dict.put(buffer, lastSymbol);
        }
//        for (List<Integer> key : dict.keySet()) {
//            StringBuilder sb = new StringBuilder();
//            for (Integer symbol : key) {
//                sb.append((char) symbol.intValue());
//            }
//            System.out.println(sb.toString());
//        }

        return LZ78Output.builder()
                .alphabetSize(alphabet.size())
                .dictSize(dict.keySet().size())
                .nodes(result)
                .build();
    }

    public List<Integer> toBitsArray(List<LZ78Node> nodes, int dictB, int alphabetB) {
        List<Integer> bitsArray = new ArrayList<>();
        for (LZ78Node node : nodes) {
            int d = node.getPos();
            int a = node.getNext();
            for (int i = 0; i < dictB; i++) {
                bitsArray.add(d / powOfTwo());
            }
        }
    }

    private int powOfTwo(int pow) {
        int p = 1;
        for (int i = 0; i < pow; i++) {
            p = p * 2;
        }
        return p;
    }
}
