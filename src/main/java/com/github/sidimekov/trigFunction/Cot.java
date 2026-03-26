package com.github.sidimekov.trigFunction;

import com.github.sidimekov.AbstractFunction;
import com.github.sidimekov.Function;

public class Cot extends AbstractFunction {
    private final Function sin;
    private final Function cos;

    public Cot(Function sin, Function cos) {
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