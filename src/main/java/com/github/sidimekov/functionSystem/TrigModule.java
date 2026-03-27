package com.github.sidimekov.functionSystem;

import com.github.sidimekov.AbstractFunction;
import com.github.sidimekov.Function;
import com.github.sidimekov.trigFunction.*;

public class TrigModule extends AbstractFunction {
    private final Function sin;
    private final Function cos;
    private final Function cot;
    private final Function sec;
    private final Function csc;

    public TrigModule(double eps) {
        this.sin = new Sin(eps);
        this.cos = new Cos(sin);
        this.cot = new Cot(sin, cos);
        this.sec = new Sec(cos);
        this.csc = new Csc(sin);
    }

    public TrigModule(Function sin, Function cos, Function cot, Function sec, Function csc) {
        this.sin = sin;
        this.cos = cos;
        this.cot = cot;
        this.sec = sec;
        this.csc = csc;
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

    public Function getSin() { return sin; }
    public Function getCos() { return cos; }
    public Function getCot() { return cot; }
    public Function getSec() { return sec; }
    public Function getCsc() { return csc; }
}
