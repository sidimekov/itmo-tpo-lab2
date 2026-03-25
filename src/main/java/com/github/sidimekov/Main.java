package com.github.sidimekov;

import com.github.sidimekov.csv.CsvWriter;
import com.github.sidimekov.functionSystem.MainSystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начальное значение x:");
        double start = scanner.nextDouble();

        System.out.println("Введите конечное значение x:");
        double end = scanner.nextDouble();

        System.out.println("Введите шаг:");
        double step = scanner.nextDouble();

        System.out.println("Введите точность (eps):");
        double eps = scanner.nextDouble();

        MainSystem systemFunction = new MainSystem(eps);

        List<Double> xs = new ArrayList<>();
        List<Double> results = new ArrayList<>();

        for (double x = start; x <= end; x += step) {
            try {
                double y = systemFunction.compute(x);
                xs.add(x);
                results.add(y);
                System.out.printf("x=%.6f, f(x)=%.6f%n", x, y);
            } catch (ArithmeticException e) {
                System.out.printf("x=%.6f, ошибка: %s%n", x, e.getMessage());
            }
        }

        System.out.println("Введите путь для CSV файла (например, output.csv):");
        String filePath = scanner.next();

        System.out.println("Введите разделитель CSV (например, , или ;):");
        String delimiter = scanner.next();

        try {
            CsvWriter.writeCsv(filePath, xs, results, delimiter);
            System.out.println("CSV файл успешно создан: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка записи CSV: " + e.getMessage());
        }
    }
}