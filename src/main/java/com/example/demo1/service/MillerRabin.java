package com.example.demo1.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Random;
@Service
public class MillerRabin {
    public static boolean isPrime(BigInteger n, int k) {
        if (n.compareTo(BigInteger.valueOf(2)) < 0) {
            return false;
        }
        if (n.compareTo(BigInteger.valueOf(2)) == 0) {
            return true;
        }

        if (!n.testBit(0)) {  // Проверяем, является ли число четным
            return false;
        }

        BigInteger d = n.subtract(BigInteger.ONE);
        int s = 0;
        while (d.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            s++;
            d = d.divide(BigInteger.valueOf(2));
        }

        for (int i = 0; i < k; i++) {
            BigInteger a = uniformRandom(BigInteger.valueOf(2), n.subtract(BigInteger.ONE));
            BigInteger x = a.modPow(d, n);
            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) {
                continue;
            }
            boolean isPrime = false;
            for (int j = 0; j < s - 1; j++) {
                x = x.modPow(BigInteger.valueOf(2), n);
                if (x.equals(BigInteger.ONE)) {
                    return false;
                }
                if (x.equals(n.subtract(BigInteger.ONE))) {
                    isPrime = true;
                    break;
                }
            }
            if (!isPrime) {
                return false;
            }
        }
        return true;
    }

    private static BigInteger uniformRandom(BigInteger bottom, BigInteger top) {
        Random rnd = new Random();
        BigInteger res;
        do {
            res = new BigInteger(top.bitLength(), rnd);
        } while (res.compareTo(bottom) < 0 || res.compareTo(top) > 0);
        return res;
    }
}
