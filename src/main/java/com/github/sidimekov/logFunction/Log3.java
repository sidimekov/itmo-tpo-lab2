package com.github.sidimekov.logFunction;

import com.github.sidimekov.AbstractFunction;

public class Log3 extends AbstractFunction {
    private final Ln ln;

    public Log3(Ln ln) {
        this.ln = ln;
    }

    @Override
    public double compute(double x) {
        double lnX = ln.compute(x);
        double ln3 = ln.compute(3);

        if (Double.isNaN(lnX)) return Double.NaN;

        return lnX / ln3;
    }

    @Override
    protected String getFunctionName() {
        return "Log3";
    }
}
