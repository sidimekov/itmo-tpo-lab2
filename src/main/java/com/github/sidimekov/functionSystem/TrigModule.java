package com.github.sidimekov.functionSystem;

import com.github.sidimekov.Function;
import com.github.sidimekov.csv.CsvWriter;
import com.github.sidimekov.trigFunction.*;

import java.util.ArrayList;
import java.util.List;

public class TrigModule implements Function {
    private final Sin sin;
    private final Cos cos;
    private final Cot cot;
    private final Sec sec;
    private final Csc csc;

    public TrigModule(double eps) {
        this.sin = new Sin(eps);
        this.cos = new Cos(sin);
        this.cot = new Cot(sin, cos);
        this.sec = new Sec(cos);
        this.csc = new Csc(sin);
    }

    @Override
    public double compute(double x) {
        double sinV = sin.compute(x);
        double cosV = cos.compute(x);
        double cotV = cot.compute(x);
        double secV = sec.compute(x);
        double cscV = csc.compute(x);

        double trigRes = (((((sinV + cotV) * cosV) - secV) + Math.pow(secV,3)) + cscV);

        // Если хоть одно значение NaN, возвращаем NaN для всего результата
        if (Double.isNaN(sinV) || Double.isNaN(cosV) || Double.isNaN(cotV)
                || Double.isNaN(secV) || Double.isNaN(cscV)) {
            return Double.NaN;
        }

        return trigRes;
    }

    @Override
    public void computeAndSaveCsv(double start, double end, double step, String filePath) {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"X","Sin","Cos","Cot","Sec","Csc","TrigModule"});
        for (double x = start; x <= end; x += step) {
            double sinV = sin.compute(x);
            double cosV = cos.compute(x);
            double cotV = cot.compute(x);
            double secV = sec.compute(x);
            double cscV = csc.compute(x);
            double trigRes = compute(x);

            rows.add(new String[]{
                    String.valueOf(x),
                    String.valueOf(sinV),
                    String.valueOf(cosV),
                    String.valueOf(cotV),
                    String.valueOf(secV),
                    String.valueOf(cscV),
                    String.valueOf(trigRes)
            });
        }
        CsvWriter.writeCsv(filePath, rows, ",");
    }

    public Sin getSin() { return sin; }
    public Cos getCos() { return cos; }
    public Cot getCot() { return cot; }
    public Sec getSec() { return sec; }
    public Csc getCsc() { return csc; }
}
