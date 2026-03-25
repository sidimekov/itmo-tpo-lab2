package com.github.sidimekov.logFunction;

public class Ln {
    public static double compute(double x, double eps) {
        if (x <= 0) throw new ArithmeticException("ln(x) undefined for x <= 0");
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
}
