package com.github.sidimekov.functionSystem;

import com.github.sidimekov.trigFunction.Sin;
import com.github.sidimekov.trigFunction.Cos;
import com.github.sidimekov.trigFunction.Cot;
import com.github.sidimekov.trigFunction.Sec;
import com.github.sidimekov.trigFunction.Csc;

public class TrigModule {
    private final double eps;

    public TrigModule(double eps) {
        this.eps = eps;
    }

    public double compute(double x) {
        double sin = Sin.compute(x, eps);
        double cos = Cos.compute(x, eps);
        double cot = Cot.compute(x, eps);
        double sec = Sec.compute(x, eps);
        double csc = Csc.compute(x, eps);
        return (((((sin + cot) * cos) - sec) + Math.pow(sec, 3)) + csc);
    }
}
