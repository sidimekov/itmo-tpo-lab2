package com.github.sidimekov.logFunction;

import com.github.sidimekov.AbstractFunction;

public class Log2 extends AbstractFunction {
    private final Ln ln;

    public Log2(Ln ln) {
        this.ln = ln;
    }

    @Override
    public double compute(double x) {
        double lnX = ln.compute(x);
        double ln2 = ln.compute(2);

        if (Double.isNaN(lnX)) return Double.NaN;

        return lnX / ln2;
    }

    @Override
    protected String getFunctionName() {
        return "Log2";
    }
}
