package com.github.sidimekov.logFunction;

import com.github.sidimekov.AbstractFunction;
import com.github.sidimekov.Function;

public class Log3 extends AbstractFunction {
    private final Function ln;

    public Log3(Function ln) {
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
