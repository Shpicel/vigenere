package com.example.demo1.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

@Service
public class MethodBlackly {
    public HashMap<String, Object> runBlackly(String fileName) {
        HashMap<String, Object> resultMap = new HashMap<>();
        ArrayList<ArrayList<double[]>> table = EcuationReader.readEquationsFromFile(fileName);
        double[] result;
        ArrayList<double[]> subResults = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        for (ArrayList<double[]> doubles : table) {
            while (doubles.size() > doubles.get(doubles.size() - 1).length - 1) {
                Random rand = new Random();
                int index1 = rand.nextInt(doubles.size());
                doubles.remove(index1);
            }
            result = solveEquation(doubles);
            subResults.add(result);
            double res = result[0] + 0.2;
            char asciiChar = getCharFromASCII((int) res);
            str.append(asciiChar);
        }
        resultMap.put("subResults", subResults);
        resultMap.put("finalResult", str);
        return resultMap;
    }

    public static double[] solveEquation(ArrayList<double[]> coefficients) {
        int n = coefficients.size();

        for (int i = 0; i < n; i++) {
            double maxCoefficient = Math.abs(coefficients.get(i)[i]);
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(coefficients.get(k)[i]) > maxCoefficient) {
                    maxCoefficient = Math.abs(coefficients.get(k)[i]);
                    maxRow = k;
                }
            }

            double[] temp = coefficients.get(maxRow);
            coefficients.set(maxRow, coefficients.get(i));
            coefficients.set(i, temp);

            for (int k = i + 1; k < n; k++) {
                double factor = -coefficients.get(k)[i] / coefficients.get(i)[i];
                for (int j = i; j <= n; j++) {
                    if (i == j) {
                        coefficients.get(k)[j] = 0;
                    } else {
                        coefficients.get(k)[j] += factor * coefficients.get(i)[j];
                    }
                }
            }
        }

        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            solution[i] = coefficients.get(i)[n];
            for (int j = i + 1; j < n; j++) {
                solution[i] -= coefficients.get(i)[j] * solution[j];
            }
            solution[i] = solution[i] / coefficients.get(i)[i];
        }

        return solution;
    }

    public static char getCharFromASCII(int x) {
        int circChar = x + 880;
        return (char) circChar;
    }
}
