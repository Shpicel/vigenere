package com.example.demo1.service;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    public BigInteger generateQ(int bitLength, SecureRandom random){
        return generateRandomPrime(bitLength, random);
    }

    public BigInteger generateP(int bitLength, SecureRandom random){
        return generateRandomPrime(bitLength, random);
    }
    public BigInteger getN(BigInteger p, BigInteger q){
        return p.multiply(q);
    }

    public BigInteger getM(BigInteger p, BigInteger q){
        return p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    }

    public BigInteger getD(BigInteger m, int bitLength, SecureRandom random) {
        BigInteger result;
        int newBitLength = bitLength * 2;
        for (BigInteger i = BigInteger.TWO; i.compareTo(m) < 0; i = i.add(BigInteger.ONE)) {
            BigInteger num = new BigInteger(newBitLength, random);
            if (num.gcd(m).equals(BigInteger.ONE) && num.compareTo(m) < 0) {
                result = new BigInteger(String.valueOf(num));
                return result;
            }
        }
        return new BigInteger("-1");
    }

    public BigInteger getE(BigInteger m, BigInteger d) {
        return d.modInverse(m); // Метод modInverse находит мультипликативную инверсию
    }

    public BigInteger generateRandomPrime(int bitLength, SecureRandom random) {
        BigInteger prime;
        do {
            prime = new BigInteger(bitLength, random);
        } while (!MillerRabin.isPrime(prime, 100)); // Проверяем, является ли число простым
        System.out.println("простое число " + prime);
        return prime;
    }

    public BigInteger encryptRSA(BigInteger a, BigInteger e, BigInteger n) {
        return a.modPow(e, n);
    }

    public BigInteger decryptRSA(BigInteger b, BigInteger d, BigInteger n) {
        return b.modPow(d, n);
    }
}
