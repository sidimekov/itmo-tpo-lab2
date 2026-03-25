package com.github.sidimekov.functionSystem;

public class MainSystem {
    private final TrigModule trigModule;
    private final LogModule logModule;

    public MainSystem(double eps) {
        this.trigModule = new TrigModule(eps);
        this.logModule = new LogModule(eps);
    }

    public double compute(double x) {
        if (x <= 0) {
            return trigModule.compute(x);
        } else {
            return logModule.compute(x);
        }
    }
}
