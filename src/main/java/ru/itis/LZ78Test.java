package ru.itis;

import ru.itis.structures.LZ78Node;
import ru.itis.structures.LZ78Output;
import ru.itis.utils.FileReader;
import ru.itis.utils.HuffmanEncoder;
import ru.itis.utils.LZ78;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class LZ78Test {
    public static void main(String[] args) throws IOException {
        String filepath = "/home/xiu-xiu/PythonProjects/Archivator/data/voina_i_mir.txt";
        FileReader fileReader = new FileReader();
        List<Integer> fileInts = fileReader.getIntArrayOfFile(filepath);

        LZ78 lz78 = new LZ78();

        LZ78Output result = lz78.encode(fileInts);

        System.out.println("symbols in raw: " + fileInts.size());
        System.out.println("nodes after lz78: " + result.getNodes().size());

//        System.out.println(Math.log(result.getDictSize()) / Math.log(2));
//        System.out.println(Math.log(result.getAlphabetSize()) / Math.log(2));
//        System.out.println(25 * result.getNodes().size());

        Set<Integer> posSet = new HashSet<>();
        Set<Integer> nextSet = new HashSet<>();
        List<List<Integer>> integers = new ArrayList<>();
        for (LZ78Node node : result.getNodes()) {
            integers.add(Collections.singletonList(node.getPos()));
            integers.add(Collections.singletonList(node.getNext()));
            posSet.add(node.getPos());
            nextSet.add(node.getNext());
        }
        System.out.println("poses: " + posSet.size());
        System.out.println("nexts: " + nextSet.size());


        DataOutputStream os = new DataOutputStream(new FileOutputStream("output.dat"));
        List<Integer> bitsArray = new ArrayList<>();
        int dictB = (int) (Math.log(result.getDictSize()) / Math.log(2)) + 1;
        int alphabetB = (int) (Math.log(result.getAlphabetSize()) / Math.log(2)) + 1;
        for (int  i = 0; i < result.getNodes().size(); i++) {
            for (int j = 0; j < dictB; j++) {

            }
        }
    }
}
