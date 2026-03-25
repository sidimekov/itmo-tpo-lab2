package com.github.sidimekov.trigFunction;

public class Cos {
    public static double compute(double x, double eps) {
        double sin = Sin.compute(x, eps);

        if (x >= -Math.PI / 2 && x <= 0) {
            return Math.sqrt(1 - sin * sin);
        } else {
            return -Math.sqrt(1 - sin * sin);
        }
    }
}
