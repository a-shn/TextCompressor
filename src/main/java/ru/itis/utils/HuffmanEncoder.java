package ru.itis.utils;

import ru.itis.structures.HuffmanNode;
import ru.itis.structures.Word;

import java.util.*;

public class HuffmanEncoder {
    public List<Integer> encodeWords(List<List<Integer>> words) {
        List<HuffmanNode> nodes = new ArrayList<>();
        Map<List<Integer>, Integer> frequencies = frequenciesMap(words);
        for (List<Integer> word : frequencies.keySet()) {
            nodes.add(HuffmanNode.builder().word(word).frequency(frequencies.get(word)).build());
        }
        nodes.sort(Comparator.comparing(HuffmanNode::getFrequency));
        Collections.reverse(nodes);
        HuffmanNode headNode = makeTree(nodes);
        Map<List<Integer>, List<Integer>> encodings = getEncodings(headNode);
        List<Integer> encodedWords = new ArrayList<>();
        for (List<Integer> word : words) {
            for (Integer b : encodings.get(word)) {
                encodedWords.add(b);
            }

        }
        return encodedWords;
    }

    private Map<List<Integer>, List<Integer>> getEncodings(HuffmanNode head) {
        Map<List<Integer>, List<Integer>> encodings = new HashMap<>();
        goDeeper(encodings, head, new ArrayList<Integer>());
        return encodings;
    }

    private void goDeeper(Map<List<Integer>, List<Integer>> encodings, HuffmanNode node, List<Integer> code) {
        if (node.getWord() != null) {
            encodings.put(node.getWord(), code);
            return;
        }
        ArrayList<Integer> newCode = new ArrayList<>(code);
        newCode.add(0);
        goDeeper(encodings, node.getMap().get(0), newCode);
        newCode.set(newCode.size() - 1, 1);
        goDeeper(encodings, node.getMap().get(1), newCode);

    }

    private HuffmanNode makeTree(List<HuffmanNode> sortedNodes) {
        while (sortedNodes.size() >= 2) {
            Map<Integer, HuffmanNode> map = new HashMap<>();
            map.put(0, sortedNodes.get(sortedNodes.size() - 1));
            map.put(1, sortedNodes.get(sortedNodes.size() - 2));
            HuffmanNode newNode = HuffmanNode.builder().frequency(sortedNodes.get(sortedNodes.size() - 1).getFrequency()
            + sortedNodes.get(sortedNodes.size() - 2).getFrequency()).map(map).build();
            insert(sortedNodes, newNode);
            sortedNodes.remove(sortedNodes.size() - 1);
            sortedNodes.remove(sortedNodes.size() - 1);

        }
        return sortedNodes.get(0);
    }

    private void insert(List<HuffmanNode> list, HuffmanNode element) {
        list.add(getBIndex(list, element), element);
    }

    private Integer getBIndex(List<HuffmanNode> list, HuffmanNode element) {
        if (element.getFrequency() >= list.get(0).getFrequency()) {
            return 0;
        }
        if (element.getFrequency() <= list.get(list.size() - 1).getFrequency()) {
            return list.size();
        }
        Integer first = 0;
        Integer last = list.size() - 1;
        Integer index = (first + last) / 2;
        while (!(list.get(index - 1).getFrequency() >= element.getFrequency() && list.get(index).getFrequency() <= element.getFrequency())) {
            if (list.get(index).getFrequency() <= element.getFrequency()) {
                last = index + 1;
            } else {
                first = index;
            }
            index = (first + last) / 2;
        }
        return index;
    }

    private Map<List<Integer>, Integer> frequenciesMap(List<List<Integer>> words) {
        Map<List<Integer>, Integer> frequencies = new HashMap<>();
        for (List<Integer> word : words) {
            if (frequencies.containsKey(word)) {
                frequencies.put(word, frequencies.get(word) + 1);
            } else {
                frequencies.put(word, 1);
            }
        }
        return frequencies;
    }

    private Integer count(List<List<Integer>> words, List<Integer> word) {
        int counter = 0;
        for (List<Integer> _word : words) {
            if (equal(word, _word)) {
                counter++;
            }
        }
        return counter;
    }

    private boolean equal(List<Integer> word1, List<Integer> word2) {
        if (word1.size() != word2.size()) {
            return false;
        }
        for (int i = 0; i < word1.size(); i++) {
            if (!word1.get(i).equals(word2.get(i))) {
                return false;
            }
        }
        return true;
    }
}
