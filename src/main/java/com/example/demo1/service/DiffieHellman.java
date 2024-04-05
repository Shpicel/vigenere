package com.example.demo1.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

@Service
public class DiffieHellman {

    public BigInteger getPublicKey(BigInteger primeNum, BigInteger primeRootNum, BigInteger privateKey) {
        System.out.println(privateKey);
        return primeRootNum.modPow(privateKey, primeNum);
    }

    public BigInteger getSharedSecret(BigInteger otherPublicKey, BigInteger primeNum, BigInteger privateKey) {
        return otherPublicKey.modPow(privateKey, primeNum);
    }

    public BigInteger generatePrime(int bitLength, int iterations) {
        Random random = new Random();
        int step = 0;
        long startTime = System.currentTimeMillis();

        BigInteger prime = new BigInteger(bitLength, random);
        while (!prime.isProbablePrime(iterations)) {
            prime = new BigInteger(bitLength, random);
            step = step + 1;
        }

        long endTime = System.currentTimeMillis();
//        System.out.println("Простое число: " + prime);
//        System.out.println("Итераций алгоритма: " + step);
        System.out.println("Время генерации простого числа: " + (endTime - startTime) + " мс");
        return prime;
    }


    public BigInteger[] runDiffieHellman(String strPrivateKeyXa, String strPrivateKeyXb, String strPrimeNum, String strPrimeRootNum) {
        BigInteger privateKeyXa = new BigInteger(strPrivateKeyXa);
        BigInteger privateKeyXb = new BigInteger(strPrivateKeyXb);
        BigInteger primeNum = new BigInteger(strPrimeNum);
        BigInteger primeRootNum = new BigInteger(strPrimeRootNum);

        DiffieHellman alice = new DiffieHellman();
        DiffieHellman bob = new DiffieHellman();

        BigInteger alicePublicKey = alice.getPublicKey(primeNum, primeRootNum, privateKeyXa);
        BigInteger bobPublicKey = bob.getPublicKey(primeNum, primeRootNum, privateKeyXb);

        BigInteger aliceSharedSecret = alice.getSharedSecret(bobPublicKey, primeNum, privateKeyXa);
        BigInteger bobSharedSecret = bob.getSharedSecret(alicePublicKey, primeNum, privateKeyXb);

//        System.out.println("Alice's shared secret: " + aliceSharedSecret);
//        System.out.println("Bob's shared secret: " + bobSharedSecret);
        BigInteger[] result = new BigInteger[2];
        result[0]=aliceSharedSecret;
        result[1]=bobSharedSecret;
        return result;
    }

    public List<BigInteger> runBigIntegerPrimeSieve(String strLowerBound, String strUpperBound) {
        BigIntegerPrimeSieve primeSieve = new BigIntegerPrimeSieve();
        BigInteger lowerBound = new BigInteger(strLowerBound);
        BigInteger upperBound = new BigInteger(strUpperBound);
        List<BigInteger> primes = primeSieve.getPrimes(lowerBound, upperBound);
//        System.out.println("Простые числа в диапазоне от " + lowerBound + " до " + upperBound + ": " + primes);
        return primes;
    }
}
