package com.github.sidimekov.functionSystem;

import com.github.sidimekov.logFunction.Ln;
import com.github.sidimekov.logFunction.Log2;
import com.github.sidimekov.logFunction.Log3;

public class LogModule {
    private final double eps;

    public LogModule(double eps) {
        this.eps = eps;
    }

    public double compute(double x) {
        double log3 = Log3.compute(x, eps);
        double log2 = Log2.compute(x, eps);
        double ln = Ln.compute(x, eps);

        double part1 = Math.pow(Math.pow(log3, 2), 3) - ln + log2;
        double part2 = (log3 + (log3 - ln)) - Math.pow(log2, 3);

        return part1 + part2;
    }
}
