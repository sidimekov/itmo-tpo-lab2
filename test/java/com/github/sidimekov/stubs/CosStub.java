package com.github.sidimekov.stubs;

import com.github.sidimekov.AbstractFunction;

import java.util.Map;

public class CosStub extends AbstractFunction {
    private final Map<Double, Double> table;

    public CosStub(Map<Double, Double> table) {
        this.table = table;
    }

    @Override
    protected String getFunctionName() {
        return "CosStub";
    }

    @Override
    public double compute(double x) {
        return this.table.get(x);
    }
}
