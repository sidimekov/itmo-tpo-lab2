package com.github.sidimekov.logFunction;

public class Log2 {
    public static double compute(double x, double eps) {
        return Ln.compute(x, eps) / Ln.compute(2, eps);
    }
}
