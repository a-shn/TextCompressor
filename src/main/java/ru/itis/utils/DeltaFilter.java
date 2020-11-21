package ru.itis.utils;

import java.util.ArrayList;
import java.util.List;

public class DeltaFilter {
    public List<Integer> getDeltas(List<Integer> fileInts) {
        List<Integer> deltas = new ArrayList<>();
        deltas.add(fileInts.get(0));
        for (int i = 1; i < fileInts.size(); i++) {
            deltas.add(fileInts.get(i) - fileInts.get(i - 1));
        }
        return deltas;
    }
}
