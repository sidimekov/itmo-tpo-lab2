package com.github.sidimekov.trigFunction;

import com.github.sidimekov.Function;
import com.github.sidimekov.csv.CsvWriter;

import java.util.ArrayList;
import java.util.List;

public class Csc implements Function {
    private final Sin sinFunc;

    public Csc(Sin sinFunc) {
        this.sinFunc = sinFunc;
    }

    @Override
    public double compute(double x) {
        double s = sinFunc.compute(x);
        if (Math.abs(s) < 1e-12) return Double.NaN;
        return 1 / s;
    }

    @Override
    public void computeAndSaveCsv(double start, double end, double step, String filePath) {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"X","Csc"});
        for (double x = start; x <= end; x += step) {
            double val = compute(x);
            rows.add(new String[]{String.valueOf(x), String.valueOf(val)});
        }
        CsvWriter.writeCsv(filePath, rows, ",");
    }
}
