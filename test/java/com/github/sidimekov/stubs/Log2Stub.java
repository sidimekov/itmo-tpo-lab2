package java.com.github.sidimekov.stubs;

import com.github.sidimekov.AbstractFunction;

import java.util.Map;

public class Log2Stub extends AbstractFunction {
    private final Map<Double, Double> table;

    public Log2Stub(Map<Double, Double> table) {
        this.table = table;
    }

    @Override
    protected String getFunctionName() {
        return "Log2Stub";
    }

    @Override
    public double compute(double x) {
        return this.table.get(x);
    }
}