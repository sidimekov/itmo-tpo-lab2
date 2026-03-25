package com.github.sidimekov.functionSystem;

import com.github.sidimekov.csv.CsvWriter;

import java.util.ArrayList;
import java.util.List;

public class MainSystem {
    private final TrigModule trigModule;
    private final LogModule logModule;

    public MainSystem(double eps) {
        trigModule = new TrigModule(eps);
        logModule = new LogModule(eps);
    }

    public double compute(double x) {
        if (x <= 0) return trigModule.compute(x);
        else return logModule.compute(x);
    }

    public void computeAndSaveCsv(double start, double end, double step, String filePath) {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"X","MainResult"});
        for (double x = start; x <= end; x += step) {
            double mainRes = compute(x);
            rows.add(new String[]{
                    String.valueOf(x),
                    String.valueOf(mainRes)
            });
        }
        CsvWriter.writeCsv(filePath, rows, ",");
    }

    public TrigModule getTrigModule() { return trigModule; }
    public LogModule getLogModule() { return logModule; }
}
