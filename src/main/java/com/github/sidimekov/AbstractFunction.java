package com.github.sidimekov;

import com.github.sidimekov.csv.CsvWriter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFunction implements Function {

    protected abstract String getFunctionName();

    @Override
    public void computeAndSaveCsv(double start, double end, double step, String filePath) {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"X", getFunctionName()});

        for (double x = start; x <= end; x += step) {
            double value;
            try {
                value = compute(x);
            } catch (Exception e) {
                value = Double.NaN;
            }

            rows.add(new String[]{
                    String.valueOf(x),
                    String.valueOf(value)
            });
        }

        CsvWriter.writeCsv(filePath, rows, ",");
    }
}
