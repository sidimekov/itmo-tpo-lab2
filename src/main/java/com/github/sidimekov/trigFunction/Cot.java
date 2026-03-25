package com.github.sidimekov.trigFunction;

import com.github.sidimekov.Function;
import com.github.sidimekov.csv.CsvWriter;

import java.util.ArrayList;
import java.util.List;

public class Cot implements Function {
    private final Sin sinFunc;
    private final Cos cosFunc;

    public Cot(Sin sinFunc, Cos cosFunc) {
        this.sinFunc = sinFunc;
        this.cosFunc = cosFunc;
    }

    @Override
    public double compute(double x) {
        double sin = sinFunc.compute(x);
        if (Math.abs(sin) < 1e-12) return Double.NaN;
        return cosFunc.compute(x) / sin;
    }

    @Override
    public void computeAndSaveCsv(double start, double end, double step, String filePath) {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"X","Cot"});
        for (double x = start; x <= end; x += step) {
            double val = compute(x);
            rows.add(new String[]{String.valueOf(x), String.valueOf(val)});
        }
        CsvWriter.writeCsv(filePath, rows, ",");
    }
}