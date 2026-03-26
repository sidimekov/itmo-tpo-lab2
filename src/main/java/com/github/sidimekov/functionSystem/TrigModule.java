package com.github.sidimekov.functionSystem;

import com.github.sidimekov.AbstractFunction;
import com.github.sidimekov.trigFunction.*;

public class TrigModule extends AbstractFunction {
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

        if (Double.isNaN(sinV) || Double.isNaN(cosV) ||
                Double.isNaN(cotV) || Double.isNaN(secV) || Double.isNaN(cscV)) {
            return Double.NaN;
        }

        return (((((sinV + cotV) * cosV) - secV) + Math.pow(secV, 3)) + cscV);
    }

    @Override
    protected String getFunctionName() {
        return "TrigModule";
    }

    public Sin getSin() { return sin; }
    public Cos getCos() { return cos; }
    public Cot getCot() { return cot; }
    public Sec getSec() { return sec; }
    public Csc getCsc() { return csc; }
}
