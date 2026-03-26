package com.github.sidimekov.trigFunction;

import com.github.sidimekov.AbstractFunction;
import com.github.sidimekov.Function;

public class Sec extends AbstractFunction {
    private final Function cos;

    public Sec(Function cos) {
        this.cos = cos;
    }

    @Override
    public double compute(double x) {
        double c = cos.compute(x);
        if (Math.abs(c) < 1e-12) return Double.NaN;
        return 1 / c;
    }

    @Override
    protected String getFunctionName() {
        return "Sec";
    }
}
