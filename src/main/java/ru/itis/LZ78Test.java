package ru.itis;

import ru.itis.structures.LZ78Node;
import ru.itis.structures.LZ78Output;
import ru.itis.utils.BitsSaver;
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
        BitsSaver bitsSaver = new BitsSaver();
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
        for (LZ78Node node : result.getNodes()) {
            posSet.add(node.getPos());
            nextSet.add(node.getNext());
        }
        System.out.println("poses: " + posSet.size());
        System.out.println("nexts: " + nextSet.size());

        List<Integer> bitsArray = lz78.toBitsArray(result);
        bitsSaver.lz78Save(bitsArray, "lz78.dat");
    }
}
