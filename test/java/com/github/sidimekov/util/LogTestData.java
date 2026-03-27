package com.github.sidimekov.util;

import java.util.HashMap;
import java.util.Map;

public final class LogTestData {
    private static final double X_INTERVAL_ZERO_TO_ONE = 0.5;
    private static final double X_ONE = 1.0;
    private static final double X_INTERVAL_ABOVE_ONE = 4.0;
    private static final double X_NEAR_ZERO_POSITIVE = 1e-6;
    private static final double X_BASE_TWO = 2.0;
    private static final double X_BASE_THREE = 3.0;

    private static final Map<Double, Double> LN_POINTS = new HashMap<>();
    private static final Map<Double, Double> LOG2_POINTS = new HashMap<>();
    private static final Map<Double, Double> LOG3_POINTS = new HashMap<>();

    static {
        LN_POINTS.put(X_INTERVAL_ZERO_TO_ONE, Math.log(X_INTERVAL_ZERO_TO_ONE));
        LN_POINTS.put(X_ONE, 0.0);
        LN_POINTS.put(X_INTERVAL_ABOVE_ONE, Math.log(X_INTERVAL_ABOVE_ONE));
        LN_POINTS.put(X_NEAR_ZERO_POSITIVE, Math.log(X_NEAR_ZERO_POSITIVE));
        LN_POINTS.put(X_BASE_TWO, Math.log(X_BASE_TWO));
        LN_POINTS.put(X_BASE_THREE, Math.log(X_BASE_THREE));

        LOG2_POINTS.put(X_INTERVAL_ZERO_TO_ONE, Math.log(X_INTERVAL_ZERO_TO_ONE) / Math.log(2));
        LOG2_POINTS.put(X_ONE, 0.0);
        LOG2_POINTS.put(X_INTERVAL_ABOVE_ONE, Math.log(X_INTERVAL_ABOVE_ONE) / Math.log(2));
        LOG2_POINTS.put(X_NEAR_ZERO_POSITIVE, Math.log(X_NEAR_ZERO_POSITIVE) / Math.log(2));
        LOG2_POINTS.put(X_BASE_TWO, 1.0);
        LOG2_POINTS.put(X_BASE_THREE, Math.log(X_BASE_THREE) / Math.log(2));

        LOG3_POINTS.put(X_INTERVAL_ZERO_TO_ONE, Math.log(X_INTERVAL_ZERO_TO_ONE) / Math.log(3));
        LOG3_POINTS.put(X_ONE, 0.0);
        LOG3_POINTS.put(X_INTERVAL_ABOVE_ONE, Math.log(X_INTERVAL_ABOVE_ONE) / Math.log(3));
        LOG3_POINTS.put(X_NEAR_ZERO_POSITIVE, Math.log(X_NEAR_ZERO_POSITIVE) / Math.log(3));
        LOG3_POINTS.put(X_BASE_TWO, Math.log(X_BASE_TWO) / Math.log(3));
        LOG3_POINTS.put(X_BASE_THREE, 1.0);
    }

    private LogTestData() {
    }

    public static Map<Double, Double> getLnPoints() {
        return new HashMap<>(LN_POINTS);
    }

    public static Map<Double, Double> getLog2Points() {
        return new HashMap<>(LOG2_POINTS);
    }

    public static Map<Double, Double> getLog3Points() {
        return new HashMap<>(LOG3_POINTS);
    }
}