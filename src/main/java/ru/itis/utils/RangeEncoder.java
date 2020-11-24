package ru.itis.utils;

import ru.itis.structures.Fraction;
import ru.itis.structures.IntegerAndFraction;
import ru.itis.structures.Node;

import java.math.BigInteger;
import java.util.*;

public class RangeEncoder {
    public BigInteger encode(List<Integer> intsList) {
        Set<Integer> intSet = new HashSet<>(intsList);
        BigInteger dictSize = new BigInteger(String.valueOf((intSet.size())));
        int size = intsList.size();
        Map<Integer, Integer> frequencies = new HashMap<>();
        List<IntegerAndFraction> integerAndFractions = new ArrayList<>();
        for (Integer integer : intSet) {
            if (frequencies.containsKey(integer)) {
                frequencies.put(integer, frequencies.get(integer) + 1);
            } else {
                frequencies.put(integer, 1);
            }
        }
        for (Integer integer : frequencies.keySet()) {
            integerAndFractions.add(IntegerAndFraction.builder().fraction(Fraction.builder()
                    .numerator(frequencies.get(integer))
                    .denominator(size)
                    .build())
                    .integer(integer)
                    .build());
        }
        integerAndFractions.sort(Comparator.comparing(a -> a.getFraction().getNumerator()));
        Collections.reverse(integerAndFractions);
        BigInteger low = new BigInteger("0");
        BigInteger high = fastPow(dictSize, new BigInteger(String.valueOf(size)));
        BigInteger range = high.subtract(low);
        for (Integer integer : intsList) {
            int index = 0;
            while (!integerAndFractions.get(index).getInteger().equals(integer)) {
                BigInteger denominator = new BigInteger(String.valueOf(integerAndFractions.get(index).getFraction().getDenominator()));
                BigInteger numerator = new BigInteger(String.valueOf(integerAndFractions.get(index).getFraction().getNumerator()));
                low = low.add(range.divide(denominator).multiply(numerator));
                index++;
            }
            BigInteger denominator = new BigInteger(String.valueOf(integerAndFractions.get(index).getFraction().getDenominator()));
            BigInteger numerator = new BigInteger(String.valueOf(integerAndFractions.get(index).getFraction().getNumerator()));
            high = high.add(range.divide(denominator).multiply(numerator));
            range = high.subtract(low);
        }
        return low.add(range.divide(new BigInteger("2")));
    }

    private BigInteger fastPow(BigInteger base, BigInteger exponent) {
        BigInteger result = BigInteger.ONE;
        while (exponent.signum() > 0) {
            if (exponent.testBit(0)) result = result.multiply(base);
            base = base.multiply(base);
            exponent = exponent.shiftRight(1);
        }
        return result;
    }
}
