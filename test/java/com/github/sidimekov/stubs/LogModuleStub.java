package com.github.sidimekov.stubs;

import com.github.sidimekov.AbstractFunction;

import java.util.Map;

public class LogModuleStub extends AbstractFunction {
    private final Map<Double, Double> table;

    public LogModuleStub(Map<Double, Double> table) {
        this.table = table;
    }

    @Override
    protected String getFunctionName() {
        return "LogModuleStub";
    }

    @Override
    public double compute(double x) {
        return this.table.get(x);
    }
}