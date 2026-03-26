package com.github.sidimekov.trigFunction;

import com.github.sidimekov.AbstractFunction;

public class Sin extends AbstractFunction {
    private final double eps;

    public Sin(double eps) {
        this.eps = eps;
    }

    @Override
    public double compute(double x) {
        double term = x;
        double sum = term;
        int n = 1;

        while (Math.abs(term) > eps) {
            term *= -x * x / ((2 * n) * (2 * n + 1));
            sum += term;
            n++;
        }

        return sum;
    }

    @Override
    protected String getFunctionName() {
        return "Sin";
    }
}
