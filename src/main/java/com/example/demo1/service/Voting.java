package com.example.demo1.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class Voting {
    RSA rsa = new RSA();

    public String runVoting(int countVotes) {
        int bitLength = 1024;
        SecureRandom random = new SecureRandom();
        BigInteger p = rsa.generateP(bitLength, random);
        BigInteger q = rsa.generateQ(bitLength, random);
        BigInteger m = rsa.getM(p, q);
        BigInteger n = rsa.getN(p, q);
        BigInteger secretKey = rsa.getD(m, bitLength, random);
        BigInteger openKey = rsa.getE(m, secretKey);
        ArrayList<ArrayList<BigInteger>> listVotes = generateVotes(countVotes);
        ArrayList<BigInteger> listQi = getQi(listVotes);
        ArrayList<BigInteger> listTi = getTi(listVotes);
        ArrayList<BigInteger> listClearVotes = getClearVote(listVotes);
        ArrayList<BigInteger> listFi = getFi(listTi, openKey, n);
        ArrayList<BigInteger> listDecTi = getDecTi(listFi, secretKey, n);
        BigInteger bigQ = getBigQ(listDecTi);
        ArrayList<BigInteger> resultVotesList = getPR(listQi, bigQ);
        int u = countVotes - (resultVotesList.get(0).intValue() + resultVotesList.get(1).intValue());
        BigInteger bigF = getBigF(listFi);
        return "Чистые голоса: " + listClearVotes.toString() +
                "\nОткрытый ключ: " + openKey +
                "\nЗакрытый ключ: " + secretKey +
                "\nВоздержались: " + u +
                "\nПроголосовали за: " + resultVotesList.get(0) +
                "\nПроголосовали против: " + resultVotesList.get(1) +
                "\nКонтрольное число: " + resultVotesList.get(2) +
                "\nПроверка Q: " + checkBigQ(resultVotesList.get(0), resultVotesList.get(1), resultVotesList.get(2), bigQ) +
                "\nПроверка p r: " + checkBigF(resultVotesList.get(2));
    }

    public ArrayList<ArrayList<BigInteger>> generateVotes(int countVotes) {
        ArrayList<ArrayList<BigInteger>> result = new ArrayList<>();
        ArrayList<BigInteger> listQi = new ArrayList<>();
        ArrayList<BigInteger> listTi = new ArrayList<>();
        ArrayList<BigInteger> listVote = new ArrayList<>();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < countVotes; i++) {
            BigInteger qi = rsa.generateRandomPrime(16, random);
            int vote = generateNumber();
            BigInteger ti = BigInteger.valueOf(vote).multiply(qi);
            listQi.add(qi);
            listTi.add(ti);
            listVote.add(BigInteger.valueOf(vote));
        }
        result.add(listQi);
        result.add(listTi);
        result.add(listVote);
        return result;
    }

    public static int generateNumber() {
        SecureRandom random = new SecureRandom();
        return random.nextInt(3) + 1; // Генерация числа от 1 до 3
    }

    public ArrayList<BigInteger> getTi(ArrayList<ArrayList<BigInteger>> listVotes) {
        return listVotes.get(1);
    }

    public ArrayList<BigInteger> getQi(ArrayList<ArrayList<BigInteger>> listVotes) {
        return listVotes.get(0);
    }

    public ArrayList<BigInteger> getClearVote(ArrayList<ArrayList<BigInteger>> listVotes) {
        return listVotes.get(2);
    }

    public ArrayList<BigInteger> getFi(ArrayList<BigInteger> listTi, BigInteger e, BigInteger n) {
        ArrayList<BigInteger> result = new ArrayList<>();
        for (BigInteger bigInteger : listTi) {
            result.add(rsa.encryptRSA(bigInteger, e, n));
        }
        return result;
    }

    public ArrayList<BigInteger> getDecTi(ArrayList<BigInteger> listFi, BigInteger d, BigInteger n) {
        ArrayList<BigInteger> result = new ArrayList<>();
        for (BigInteger bigInteger : listFi) {
            result.add(rsa.decryptRSA(bigInteger, d, n));
        }
        return result;
    }

    public BigInteger getBigQ(ArrayList<BigInteger> listTi) {
        BigInteger result = BigInteger.ONE;
        for (BigInteger bigInteger : listTi) {
            result = result.multiply(bigInteger);
        }
        return result;
    }

    public ArrayList<BigInteger> getPR(ArrayList<BigInteger> listQ, BigInteger Q) {
        BigInteger R = BigInteger.ONE;
        for (BigInteger q : listQ) {
            R = R.multiply(q);
        }
        BigInteger multiB = Q.divide(R);
        int r = 0;
        int p = 0;
        while (!multiB.equals(BigInteger.ONE)) {
            if (multiB.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
                multiB = multiB.divide(BigInteger.valueOf(2));
                r++;
            } else {
                multiB = multiB.divide(BigInteger.valueOf(3));
                p++;
            }
        }
        ArrayList<BigInteger> result = new ArrayList<>();
        result.add(BigInteger.valueOf(r));
        result.add(BigInteger.valueOf(p));
        result.add(R);
        return result;
    }

    public boolean checkBigQ(BigInteger r, BigInteger p, BigInteger R, BigInteger bigQ) {
        BigInteger newQ = BigInteger.TWO.pow(r.intValue()).multiply(BigInteger.valueOf(3).pow(p.intValue()).multiply(R));
        return Objects.equals(bigQ, newQ);
    }

    public BigInteger getBigF(ArrayList<BigInteger> listFi) {
        BigInteger result = BigInteger.ONE;
        for (BigInteger bigInteger : listFi) {
            result = result.multiply(bigInteger);
        }
        System.out.println(result);
        return result;
    }

    public boolean checkBigF(BigInteger R) {
        return !R.mod(BigInteger.TWO).equals(BigInteger.ZERO) && !R.mod(BigInteger.valueOf(3)).equals(BigInteger.ZERO);
    }
}
