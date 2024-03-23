package com.example.demo1.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiffieHellman {
//    private BigInteger privateKey;
//
//    public DiffieHellman() {
//        this.privateKey = generatePrime(64, 2100000000);
//    }

    public BigInteger getPublicKey(BigInteger p, BigInteger g, BigInteger privateKey) {
        System.out.println(privateKey);
        return g.modPow(privateKey, p);
    }

    public BigInteger getSharedSecret(BigInteger otherPublicKey, BigInteger p, BigInteger privateKey) {
        return otherPublicKey.modPow(privateKey, p);
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
        System.out.println("Простое число: " + prime);
        System.out.println("Итераций алгоритма: " + step);
        System.out.println("Время генерации: " + (endTime - startTime) + " мс");
        return prime;
    }

//    public static List<Integer> getPrimes(Integer lowerBound, Integer upperBound) {
//        boolean[] isComposite = new boolean[upperBound + 1];
//        List<Integer> primes = new ArrayList<>();
//        long startTime = System.currentTimeMillis();
//
//        for (int i = 2; i * i <= upperBound; i++) {
//            if (!isComposite[i]) {
//                for (int j = i * i; j <= upperBound; j += i) {
//                    isComposite[j] = true;
//                }
//            }
//        }
//
//        for (int i = Math.max(2, lowerBound); i <= upperBound; i++) {
//            if (!isComposite[i]) {
//                primes.add(i);
//            }
//        }
//
//        long endTime = System.currentTimeMillis();
//        System.out.println("Простые числа: " + primes);
//        System.out.println("Время генерации: " + (endTime - startTime) + " мс");
//        return primes;
//    }

//    public static List<BigInteger> getPrimes(BigInteger lowerBound, BigInteger upperBound) {
//        long startTime = System.currentTimeMillis();
//        List<BigInteger> primes = new ArrayList<>();
//
//        boolean[] isComposite = new boolean[upperBound.subtract(lowerBound).intValue() + 1];
//
//        for (BigInteger i = BigInteger.TWO; i.multiply(i).compareTo(upperBound) <= 0; i = i.add(BigInteger.ONE)) {
//            BigInteger start = (lowerBound.compareTo(i.multiply(i)) < 0) ?
//                    i.multiply(i) :
//                    lowerBound.add(i.subtract(lowerBound.mod(i)).mod(i));
//            for (BigInteger j = start; j.compareTo(upperBound) <= 0; j = j.add(i)) {
//                isComposite[j.subtract(lowerBound).intValue()] = true;
////                System.out.println(Arrays.toString(isComposite));
//            }
//        }
//
//        for (int k = 0; k < isComposite.length; k++) {
//            if (!isComposite[k] && lowerBound.add(BigInteger.valueOf(k)).compareTo(BigInteger.TWO) >= 0) {
//                primes.add(lowerBound.add(BigInteger.valueOf(k)));
//
////                System.out.println(primes);
//            }
//        }
//        long endTime = System.currentTimeMillis();
//        System.out.println("Время генерации: " + (endTime - startTime) + " мс");
//
//        return primes;
//    }

    public BigInteger runDiffieHellman() {
        Random random = new Random();
        BigInteger privateKeyXa = generatePrime(64, 2100000000);
        BigInteger privateKeyXb = generatePrime(64, 2100000000);

        // Здесь нужно ввести большие простые числа p и g
        BigInteger p = new BigInteger("23");
        BigInteger g = new BigInteger("5");

        DiffieHellman alice = new DiffieHellman();
        DiffieHellman bob = new DiffieHellman();

        BigInteger alicePublicKey = alice.getPublicKey(p, g, privateKeyXa);
        BigInteger bobPublicKey = bob.getPublicKey(p, g, privateKeyXb);

        BigInteger aliceSharedSecret = alice.getSharedSecret(bobPublicKey, p, privateKeyXa);
        BigInteger bobSharedSecret = bob.getSharedSecret(alicePublicKey, p, privateKeyXb);

        System.out.println("Alice's shared secret: " + aliceSharedSecret);
        System.out.println("Bob's shared secret: " + bobSharedSecret);
        return aliceSharedSecret;
    }

    public List<BigInteger> runBigIntegerPrimeSieve() {
        BigIntegerPrimeSieve primeSieve = new BigIntegerPrimeSieve();
        BigInteger lowerBound = new BigInteger("222255304413");
        BigInteger upperBound = new BigInteger("222255304623");
        List<BigInteger> primes = primeSieve.getPrimes(lowerBound, upperBound);
        System.out.println("Простые числа в диапазоне от " + lowerBound + " до " + upperBound + ": " + primes);
        return primes;
    }

//    public void main(String[] args) {


//        List<Integer> primeList = getPrimes(50, 100);
//        BigInteger lowerBound = new BigInteger("222255304413");
//        BigInteger upperBound = new BigInteger("222255304623");
//        List<BigInteger> primeList = getPrimes(lowerBound, upperBound);
//        System.out.println("Простые числа: " + primeList);
//    }
}
