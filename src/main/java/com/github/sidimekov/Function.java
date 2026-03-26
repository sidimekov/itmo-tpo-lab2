package com.github.sidimekov;

public interface Function {
    double compute(double x);
    void computeAndSaveCsv(double start, double end, double step, String filePath);
}
