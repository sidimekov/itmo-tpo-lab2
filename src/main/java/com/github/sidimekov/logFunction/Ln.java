package com.github.sidimekov.logFunction;

import com.github.sidimekov.AbstractFunction;

public class Ln extends AbstractFunction {
    private final double eps;

    public Ln(double eps) {
        this.eps = eps;
    }

    @Override
    public double compute(double x) {
        if (x <= 0) return Double.NaN;

        double y = (x - 1) / (x + 1);
        double term = y;
        double sum = term;
        int n = 1;

        while (Math.abs(term) > eps) {
            term = Math.pow(y, 2 * n + 1) / (2 * n + 1);
            sum += term;
            n++;
        }

        return 2 * sum;
    }

    @Override
    protected String getFunctionName() {
        return "Ln";
    }
}
