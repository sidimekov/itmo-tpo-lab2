package com.github.sidimekov.trigFunction;

import com.github.sidimekov.Function;
import com.github.sidimekov.csv.CsvWriter;

import java.util.ArrayList;
import java.util.List;

public class Cos implements Function {
    private final Sin sinFunc;

    public Cos(Sin sinFunc) {
        this.sinFunc = sinFunc;
    }

    @Override
    public double compute(double x) {
        double sin = sinFunc.compute(x);
        if (x >= -Math.PI/2 && x <= Math.PI/2) return Math.sqrt(1 - sin * sin);
        else return -Math.sqrt(1 - sin * sin);
    }

    @Override
    public void computeAndSaveCsv(double start, double end, double step, String filePath) {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"X","Cos"});
        for (double x = start; x <= end; x += step) {
            rows.add(new String[]{String.valueOf(x), String.valueOf(compute(x))});
        }
        CsvWriter.writeCsv(filePath, rows, ",");
    }
}
