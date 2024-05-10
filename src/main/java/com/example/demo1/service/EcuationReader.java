package com.example.demo1.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class EcuationReader {
    public static ArrayList<ArrayList<double[]>> readEquationsFromFile(String fileName) {
        ArrayList<ArrayList<double[]>> listCoef = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                double[] result;
                String[] equations = line.split(";"); // Разбиваем строку на уравнения по символу ;
                int i = 0;
                ArrayList<double[]> str = new ArrayList<>();
                for (String equation : equations) {
                    result = extractCoefficients(equation);
                    str.add(result);
                    i++;
                }
                listCoef.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listCoef;
    }

    public static double[] extractCoefficients(String equation) {
        String[] values = equation.trim().split(", "); // Разбиваем уравнение на коэффициенты по запятым и пробелам
        double[] coefficients = new double[4]; // создаем массив для хранения коэффициентов каждого уравнения
        for (int j = 0; j < 4; j++) {
            coefficients[j] = Double.parseDouble(values[j]); // Преобразуем строки в числа и записываем в массив
        }
        return coefficients;
    }

    public void printTable(ArrayList<ArrayList<double[]>> table){
        for (ArrayList<double[]> doubles : table) {
            for (double[] aDouble : doubles) {
                System.out.print(Arrays.toString(aDouble));
            }
            System.out.println();
        }
    }
}
