package com.github.sidimekov.trigFunction;

import com.github.sidimekov.AbstractFunction;
import com.github.sidimekov.Function;

public class Cos extends AbstractFunction {
    private final Function sin;

    public Cos(Function sin) {
        this.sin = sin;
    }

    @Override
    public double compute(double x) {
        double s = sin.compute(x);
        if (x >= -Math.PI/2 && x <= Math.PI/2)
            return Math.sqrt(1 - s * s);
        else
            return -Math.sqrt(1 - s * s);
    }

    @Override
    protected String getFunctionName() {
        return "Cos";
    }
}
