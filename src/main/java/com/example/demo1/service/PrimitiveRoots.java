package com.example.demo1.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
@Service
public class PrimitiveRoots {
    public List<BigInteger> runPrimitiveRoots(String strInputRoot) {
        long startTime = System.currentTimeMillis();
        BigInteger n = new BigInteger(strInputRoot);
//        System.out.println("Первые 100 первообразных корней для числа " + n + ":");
        List<BigInteger> primitiveRoots = printPrimitiveRoots(n, 100);
        long endTime = System.currentTimeMillis();
        System.out.println("Время выполнения 100 первообразных: " + (endTime - startTime) + " мс");
        return primitiveRoots;
    }

    public static List<BigInteger> printPrimitiveRoots(BigInteger n, int count) {
        List<BigInteger> primitiveRoots = new ArrayList<>();
        for (BigInteger a = BigInteger.TWO; a.compareTo(n) < 0; a = a.add(BigInteger.ONE)) {
            if (isPrimitiveRoot(a, n)) {
                primitiveRoots.add(a);
//                System.out.println(a);
                count--;
                if (count == 0) {
                    break;
                }
            }
        }
        return primitiveRoots;
    }

    public static boolean isPrimitiveRoot(BigInteger a, BigInteger n) {
        for (BigInteger i = BigInteger.ONE; i.compareTo(n.subtract(BigInteger.ONE)) < 0; i = i.add(BigInteger.ONE)) {
            if (a.modPow(i, n).equals(BigInteger.ONE)) {
                return false;
            }
        }
        return true;
    }
}
