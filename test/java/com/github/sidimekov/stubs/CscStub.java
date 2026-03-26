package java.com.github.sidimekov.stubs;

import com.github.sidimekov.AbstractFunction;

import java.util.Map;

public class CscStub extends AbstractFunction {
    private final Map<Double, Double> table;

    public CscStub(Map<Double, Double> table) {
        this.table = table;
    }

    @Override
    protected String getFunctionName() {
        return "CscStub";
    }

    @Override
    public double compute(double x) {
        return this.table.get(x);
    }
}
