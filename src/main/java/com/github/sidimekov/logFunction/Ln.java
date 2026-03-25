package com.github.sidimekov.logFunction;

import com.github.sidimekov.Function;
import com.github.sidimekov.csv.CsvWriter;

import java.util.ArrayList;
import java.util.List;

public class Ln implements Function {
    private final double eps;

    public Ln(double eps) {
        this.eps = eps;
    }

    @Override
    public double compute(double x) {
        if (x <= 0) throw new ArithmeticException("ln undefined for x <= 0");
        double y = (x - 1) / (x + 1);
        double term = y;
        double sum = term;
        int n = 1;
        while (Math.abs(term) > eps) {
            term = Math.pow(y, 2 * n + 1) / (2 * n + 1);
            sum += term;
            n++;
        }
        return 2 * sum;
    }

    @Override
    public void computeAndSaveCsv(double start, double end, double step, String filePath) {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"X","Ln"});
        for (double x = start; x <= end; x += step) {
            try {
                rows.add(new String[]{String.valueOf(x), String.valueOf(compute(x))});
            } catch (ArithmeticException e) {
                rows.add(new String[]{String.valueOf(x), "NaN"});
            }
        }
        CsvWriter.writeCsv(filePath, rows, ",");
    }
}
