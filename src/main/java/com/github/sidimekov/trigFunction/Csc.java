package com.github.sidimekov.trigFunction;

import com.github.sidimekov.AbstractFunction;

public class Csc extends AbstractFunction {
    private final Sin sin;

    public Csc(Sin sin) {
        this.sin = sin;
    }

    @Override
    public double compute(double x) {
        double s = sin.compute(x);
        if (Math.abs(s) < 1e-12) return Double.NaN;
        return 1 / s;
    }

    @Override
    protected String getFunctionName() {
        return "Csc";
    }
}
