package com.github.sidimekov.trigFunction;

import com.github.sidimekov.Function;
import com.github.sidimekov.csv.CsvWriter;

import java.util.ArrayList;
import java.util.List;

public class Sec implements Function {
    private final Cos cosFunc;

    public Sec(Cos cosFunc) {
        this.cosFunc = cosFunc;
    }

    @Override
    public double compute(double x) {
        double c = cosFunc.compute(x);
        // Возвращаем NaN, если cos(x) == 0
        if (Math.abs(c) < 1e-12) return Double.NaN;
        return 1 / c;
    }

    @Override
    public void computeAndSaveCsv(double start, double end, double step, String filePath) {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"X","Sec"});
        for (double x = start; x <= end; x += step) {
            double val = compute(x);
            rows.add(new String[]{String.valueOf(x), String.valueOf(val)});
        }
        CsvWriter.writeCsv(filePath, rows, ",");
    }
}
