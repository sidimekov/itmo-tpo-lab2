package com.github.sidimekov.logFunction;

import com.github.sidimekov.AbstractFunction;
import com.github.sidimekov.Function;

public class Log2 extends AbstractFunction {
    private final Function ln;

    public Log2(Function ln) {
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
