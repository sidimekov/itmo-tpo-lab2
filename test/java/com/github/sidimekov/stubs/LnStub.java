package java.com.github.sidimekov.stubs;

import com.github.sidimekov.AbstractFunction;

import java.util.Map;

public class LnStub extends AbstractFunction {
    private final Map<Double, Double> table;

    public LnStub(Map<Double, Double> table) {
        this.table = table;
    }

    @Override
    protected String getFunctionName() {
        return "LnStub";
    }

    @Override
    public double compute(double x) {
        return this.table.get(x);
    }
}