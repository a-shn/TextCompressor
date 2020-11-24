package ru.itis;

import ru.itis.structures.LZ78Node;
import ru.itis.structures.Node;
import ru.itis.utils.FileReader;
import ru.itis.utils.LZ78;
import ru.itis.utils.SlidingWindowLZ77;

import java.io.IOException;
import java.util.List;

public class LZ77Test {
    public static void main(String[] args) throws IOException {
        final int BUFFER_SIZE = 10;
        String filepath = "/home/xiu-xiu/PythonProjects/Archivator/data/voina_i_mir.txt";
        FileReader fileReader = new FileReader();
        List<Integer> fileInts = fileReader.getIntArrayOfFile(filepath);

        SlidingWindowLZ77 lz77 = new SlidingWindowLZ77();

        List<Node> nodes = lz77.getNodesList(fileInts, BUFFER_SIZE);

        System.out.println("symbols in raw: " + fileInts.size());
        System.out.println("nodes after lz77: " + nodes.size());
    }
}
