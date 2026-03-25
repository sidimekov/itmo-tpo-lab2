package com.github.sidimekov.trigFunction;

public class Cot {
    public static double compute(double x, double eps) {
        double sin = Sin.compute(x, eps);
        double cos = Cos.compute(x, eps);
        if (sin == 0) throw new ArithmeticException("Cotangent undefined for x = " + x);
        return cos / sin;
    }
}