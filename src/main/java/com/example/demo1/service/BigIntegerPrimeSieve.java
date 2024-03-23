package com.example.demo1.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class BigIntegerPrimeSieve {
//    public static void main(String[] args) {
////        BigIntegerPrimeSieve primeSieve = new BigIntegerPrimeSieve();
////        BigInteger lowerBound = new BigInteger("222255304413");
////        BigInteger upperBound = new BigInteger("222255304623");
////        List<BigInteger> primes = primeSieve.getPrimes(lowerBound, upperBound);
////        System.out.println("Простые числа в диапазоне от " + lowerBound + " до " + upperBound + ": " + primes);
//    }

    public List<BigInteger> getPrimes(BigInteger lowerBound, BigInteger upperBound) {
        long startTime = System.currentTimeMillis();
        List<BigInteger> primes = new ArrayList<>();

        boolean[] isComposite = new boolean[upperBound.subtract(lowerBound).intValue() + 1];

        for (BigInteger i = BigInteger.TWO; i.multiply(i).compareTo(upperBound) <= 0; i = i.add(BigInteger.ONE)) {
            BigInteger start = (lowerBound.compareTo(i.multiply(i)) < 0) ?
                    i.multiply(i) :
                    lowerBound.add(i.subtract(lowerBound.mod(i)).mod(i));
            for (BigInteger j = start; j.compareTo(upperBound) <= 0; j = j.add(i)) {
                isComposite[j.subtract(lowerBound).intValue()] = true;
//                System.out.println(Arrays.toString(isComposite));
            }
        }

        for (int k = 0; k < isComposite.length; k++) {
            if (!isComposite[k] && lowerBound.add(BigInteger.valueOf(k)).compareTo(BigInteger.TWO) >= 0) {
                primes.add(lowerBound.add(BigInteger.valueOf(k)));

//                System.out.println(primes);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Время генерации: " + (endTime - startTime) + " мс");

        return primes;
    }
}
