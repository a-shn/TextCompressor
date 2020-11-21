package ru.itis.utils;

import ru.itis.structures.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SlidingWindowLZ77 {
    public List<Node> getNodesList(List<Integer> intsList, int bufferSize) {
        int pos = 0;
        List<Node> nodes = new ArrayList<>();
        while (pos < intsList.size()) {
            Node node = findMatching(intsList, pos, Math.min(bufferSize, pos));
            pos = pos + 1 + node.getLength();
            nodes.add(node);
        }
        return nodes;
    }

    private Node findMatching(List<Integer> intsList, int pos, int bufferSize) {
        if (pos == 0) {
            return new Node(0, 0, intsList.get(0));
        }
        int offset = bufferSize;
        int length = 0;
        List<Integer> tmpSubarray = new ArrayList<>();
        List<Node> candidates = new ArrayList<>();
        for (int i = 0; i < bufferSize; i++) {
            tmpSubarray.clear();
            tmpSubarray.add(intsList.get(pos - bufferSize + i));
            int bufferStart = pos - bufferSize + i;
            length = 0;
            int j = 1;
            while (bufferStart + (j % (bufferSize - i)) < intsList.size()
                    && pos + length + 1 < intsList.size()
                    && subarrayEqual(tmpSubarray, intsList.subList(pos, pos + length + 1))) {
                length++;
                tmpSubarray.add(intsList.get(bufferStart +
                        (j % (bufferSize - bufferStart))));
                j++;
            }
            if (length > 0) {
                if (pos + length < intsList.size()) {
                    candidates.add(new Node(offset, length, intsList.get(pos + length)));
                } else {
                    candidates.add(new Node(offset, length, null));
                }
            } else {
                if (pos + length < intsList.size()) {
                    candidates.add(new Node(0, length, intsList.get(pos + length)));
                } else {
                    candidates.add(new Node(0, length, null));
                }
            }
            offset--;
        }
        return Collections.max(candidates, Comparator.comparing(Node::getLength));
    }

    private Boolean subarrayEqual(List<Integer> arr1, List<Integer> arr2) {
        if (arr1.size() != arr2.size()) {
            return false;
        }
        for (int i = 0; i < arr1.size(); i++) {
            if (!arr1.get(i).equals(arr2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> transformToInts(List<Node> nodes) {
        List<Integer> intsList = new ArrayList<>();
        for (Node node : nodes) {
            intsList.add(node.getOffset());
            intsList.add((int)',');
            intsList.add(node.getLength());
            intsList.add((int)',');
            intsList.add(node.getSymbol());
            intsList.add((int)',');
        }
        intsList.remove(intsList.size() - 1);
        return intsList;
    }
}
