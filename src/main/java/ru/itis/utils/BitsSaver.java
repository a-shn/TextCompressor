package ru.itis.utils;

import ru.itis.structures.LZ78Node;
import ru.itis.structures.LZ78Output;

import java.io.*;
import java.util.*;

public class BitsSaver {
    public void lz78Save(List<Integer> bitsArray, String filepath) throws IOException {
        DataOutputStream os = new DataOutputStream(new FileOutputStream(filepath));

//        writeBits(os, bitsArray);
        writeText(os, bitsArray);
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
            byte bb = (byte) (b - 128);
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
            byte bb = (byte) (b - 128);
            os.write(bb);
        }
    }

    private void writeText(DataOutputStream os, List<Integer> encodedWordsByBits) throws IOException {
        for (int i = 0; i < encodedWordsByBits.size() % 8; i++) {
            encodedWordsByBits.add(0);
        }
        for (int i = 0; i < encodedWordsByBits.size(); i += 8) {
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < 8; j++) {
                str.append(encodedWordsByBits.get(i + j));
            }
            os.write(Integer.parseInt(str.toString(), 2));
        }
    }
}
