package com.github.sidimekov.functionSystem;

import com.github.sidimekov.AbstractFunction;

public class MainSystem extends AbstractFunction {

    private final TrigModule trig;
    private final LogModule log;

    public MainSystem(double eps) {
        this.trig = new TrigModule(eps);
        this.log = new LogModule(eps);
    }

    @Override
    public double compute(double x) {
        if (x <= 0) {
            return trig.compute(x);
        } else {
            return log.compute(x);
        }
    }

    @Override
    protected String getFunctionName() {
        return "MainSystem";
    }

    public TrigModule getTrigModule() { return trig; }
    public LogModule getLogModule() { return log; }
}
