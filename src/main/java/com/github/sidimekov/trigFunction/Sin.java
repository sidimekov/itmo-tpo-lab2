package com.github.sidimekov.trigFunction;

public class Sin {
    public static double compute(double x, double eps) {
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
}
