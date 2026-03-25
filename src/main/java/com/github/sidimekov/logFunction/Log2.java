package com.github.sidimekov.logFunction;

import com.github.sidimekov.Function;
import com.github.sidimekov.csv.CsvWriter;

import java.util.ArrayList;
import java.util.List;

public class Log2 implements Function {
    private final Ln lnFunc;

    public Log2(Ln lnFunc) {
        this.lnFunc = lnFunc;
    }

    @Override
    public double compute(double x) {
        return lnFunc.compute(x) / lnFunc.compute(2);
    }

    @Override
    public void computeAndSaveCsv(double start, double end, double step, String filePath) {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"X","Log2"});
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
