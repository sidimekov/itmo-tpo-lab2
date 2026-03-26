package com.github.sidimekov.trigFunction;

import com.github.sidimekov.AbstractFunction;

public class Cot extends AbstractFunction {
    private final Sin sin;
    private final Cos cos;

    public Cot(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public double compute(double x) {
        double s = sin.compute(x);
        if (Math.abs(s) < 1e-12) return Double.NaN;
        return cos.compute(x) / s;
    }

    @Override
    protected String getFunctionName() {
        return "Cot";
    }
}