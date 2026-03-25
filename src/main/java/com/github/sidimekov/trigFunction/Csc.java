package com.github.sidimekov.trigFunction;

public class Csc {
    public static double compute(double x, double eps) {
        double sin = Sin.compute(x, eps);
        if (sin == 0) throw new ArithmeticException("Cosecant undefined for x = " + x);
        return 1 / sin;
    }
}
