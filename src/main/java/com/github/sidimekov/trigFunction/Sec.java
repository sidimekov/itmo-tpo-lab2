package com.github.sidimekov.trigFunction;

public class Sec {
    public static double compute(double x, double eps) {
        double cos = Cos.compute(x, eps);
        if (cos == 0) throw new ArithmeticException("Secant undefined for x = " + x);
        return 1 / cos;
    }
}
