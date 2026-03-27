package com.github.sidimekov.functionSystem;

import com.github.sidimekov.AbstractFunction;
import com.github.sidimekov.Function;
import com.github.sidimekov.logFunction.*;

public class LogModule extends AbstractFunction {
    private final Function ln;
    private final Function log2;
    private final Function log3;

    public LogModule(double eps) {
        this.ln = new Ln(eps);
        this.log2 = new Log2(ln);
        this.log3 = new Log3(ln);
    }

    public LogModule(Function ln, Function log2, Function log3) {
        this.ln = ln;
        this.log2 = log2;
        this.log3 = log3;
    }

    @Override
    public double compute(double x) {
        double lnV = ln.compute(x);
        double log2V = log2.compute(x);
        double log3V = log3.compute(x);

        if (Double.isNaN(lnV) || Double.isNaN(log2V) || Double.isNaN(log3V)) {
            return Double.NaN;
        }

        return (((Math.pow(Math.pow(log3V, 2), 3) - lnV) + log2V)
                + ((log3V + (log3V - lnV)) - Math.pow(log2V, 3)));
    }

    @Override
    protected String getFunctionName() {
        return "LogModule";
    }

    public Ln getLn() { return (Ln) ln; }
    public Log2 getLog2() { return (Log2) log2; }
    public Log3 getLog3() { return (Log3) log3; }
}
