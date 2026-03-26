package com.github.sidimekov.stubs;

import com.github.sidimekov.AbstractFunction;

import java.util.Map;

public class Log3Stub extends AbstractFunction {
    private final Map<Double, Double> table;

    public Log3Stub(Map<Double, Double> table) {
        this.table = table;
    }

    @Override
    protected String getFunctionName() {
        return "Log3Stub";
    }

    @Override
    public double compute(double x) {
        return this.table.get(x);
    }
}