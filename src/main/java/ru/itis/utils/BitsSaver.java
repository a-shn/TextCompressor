package ru.itis.utils;

import ru.itis.structures.LZ78Node;
import ru.itis.structures.LZ78Output;

import java.io.*;
import java.util.List;
import java.util.Map;

public class BitsSaver {
    public void lz78Save(List<Integer> bitsArray, String filepath) throws IOException {
        DataOutputStream os = new DataOutputStream(new FileOutputStream(filepath));

        writeBits(os, bitsArray);
    }

    private void writeBits(DataOutputStream os, List<Integer> bitsArray) throws IOException {
        for (int i = 0; i < bitsArray.size() / 8; i++) {
            int b = 0;
            for (int j = 0; j < 8; j++) {
                int bit = bitsArray.get(i * 8 + j);
                int pow = 1;
                for (int p = 7 - j; p >= 0; p--) {
                    pow = pow * 2;
                }
                b = b + bit * pow;
            }
            byte bb = (byte) b;
            os.write(bb);
        }
        if (bitsArray.size() % 8 > 0) {
            int i = bitsArray.size() / 8;
            int mod = bitsArray.size() % 8;
            int b = 0;
            for (int j = 0; j < mod; j++) {
                int bit = bitsArray.get(i * 8 + j);
                int pow = 1;
                for (int p = 7 - j; p >= 0; p--) {
                    pow = pow * 2;
                }
                b = b + bit * pow;
            }
            byte bb = (byte) b;
            os.write(bb);
        }
    }
}
