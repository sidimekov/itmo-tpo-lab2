package com.github.sidimekov.functionSystem;

import com.github.sidimekov.Function;
import com.github.sidimekov.csv.CsvWriter;
import com.github.sidimekov.logFunction.*;

import java.util.ArrayList;
import java.util.List;

public class LogModule implements Function {
    private final Ln ln;
    private final Log2 log2;
    private final Log3 log3;

    public LogModule(double eps) {
        this.ln = new Ln(eps);
        this.log2 = new Log2(ln);
        this.log3 = new Log3(ln);
    }

    @Override
    public double compute(double x) {
        double lnV = ln.compute(x);
        double log2V = log2.compute(x);
        double log3V = log3.compute(x);

        double part1 = Math.pow(Math.pow(log3V,2),3) - lnV + log2V;
        double part2 = (log3V + (log3V - lnV)) - Math.pow(log2V,3);
        return part1 + part2;
    }

    @Override
    public void computeAndSaveCsv(double start, double end, double step, String filePath) {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"X","Ln","Log2","Log3","LogModule"});
        for (double x = start; x <= end; x += step) {
            if (x <= 0) {
                rows.add(new String[]{String.valueOf(x),"NaN","NaN","NaN","NaN"});
                continue;
            }
            double lnV = ln.compute(x);
            double log2V = log2.compute(x);
            double log3V = log3.compute(x);
            double logRes = compute(x);

            rows.add(new String[]{
                    String.valueOf(x),
                    String.valueOf(lnV),
                    String.valueOf(log2V),
                    String.valueOf(log3V),
                    String.valueOf(logRes)
            });
        }
        CsvWriter.writeCsv(filePath, rows, ",");
    }

    public Ln getLn() { return ln; }
    public Log2 getLog2() { return log2; }
    public Log3 getLog3() { return log3; }
}
