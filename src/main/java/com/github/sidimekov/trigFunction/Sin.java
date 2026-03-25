package com.github.sidimekov.trigFunction;

import com.github.sidimekov.Function;
import com.github.sidimekov.csv.CsvWriter;

import java.util.ArrayList;
import java.util.List;

public class Sin implements Function {
    private final double eps;

    public Sin(double eps) {
        this.eps = eps;
    }

    @Override
    public double compute(double x) {
        double term = x;
        double sum = term;
        int n = 1;
        while (Math.abs(term) > eps) {
            term *= -x * x / ((2 * n) * (2 * n + 1));
            sum += term;
            n++;
        }
        return sum;
    }

    @Override
    public void computeAndSaveCsv(double start, double end, double step, String filePath) {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"X","Sin"});
        for (double x = start; x <= end; x += step) {
            rows.add(new String[]{String.valueOf(x), String.valueOf(compute(x))});
        }
        CsvWriter.writeCsv(filePath, rows, ",");
    }
}
