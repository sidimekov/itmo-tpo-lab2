package com.github.sidimekov.functionSystem;

import com.github.sidimekov.AbstractFunction;
import com.github.sidimekov.Function;

public class MainSystem extends AbstractFunction {

    private final Function trig;
    private final Function log;

    public MainSystem(double eps) {
        this.trig = new TrigModule(eps);
        this.log = new LogModule(eps);
    }

    public MainSystem(Function trig, Function log) {
        this.trig = trig;
        this.log = log;
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

    public TrigModule getTrigModule() { return (TrigModule) trig; }
    public LogModule getLogModule() { return (LogModule) log; }
}
