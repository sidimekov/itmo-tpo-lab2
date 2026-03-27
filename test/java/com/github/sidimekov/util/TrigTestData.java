package com.github.sidimekov.util;

import java.util.HashMap;
import java.util.Map;

public final class TrigTestData {
    private static final double X_QUADRANT_IV = -Math.PI / 4;
    private static final double X_QUADRANT_III = -3 * Math.PI / 4;
    private static final double X_SIN_DISCONTINUITY = -Math.PI;
    private static final double X_SEC_DISCONTINUITY = -Math.PI / 2;
    private static final double X_NEAR_ZERO_NEGATIVE = -1e-6;
    private static final double X_ZERO_CRITICAL = 0.0;

    private static final Map<Double, Double> SIN_POINTS = new HashMap<>();
    private static final Map<Double, Double> COS_POINTS = new HashMap<>();
    private static final Map<Double, Double> COT_POINTS = new HashMap<>();
    private static final Map<Double, Double> SEC_POINTS = new HashMap<>();
    private static final Map<Double, Double> CSC_POINTS = new HashMap<>();

    static {
        addPoint(X_QUADRANT_IV);
        addPoint(X_QUADRANT_III);
        addPoint(X_SIN_DISCONTINUITY);
        addPoint(X_SEC_DISCONTINUITY);
        addPoint(X_NEAR_ZERO_NEGATIVE);
        addPoint(X_ZERO_CRITICAL);
    }

    private static void addPoint(double x) {
        double s = Math.sin(x);
        double c = Math.cos(x);
        SIN_POINTS.put(x, s);
        COS_POINTS.put(x, c);

        // Расчет COT (cos/sin) с проверкой на разрыв
        if (Math.abs(s) < 1e-15) {
            COT_POINTS.put(x, Double.NaN);
            CSC_POINTS.put(x, Double.NaN);
        } else {
            COT_POINTS.put(x, c / s);
            CSC_POINTS.put(x, 1.0 / s);
        }

        // Расчет SEC (1/cos) с проверкой на разрыв
        if (Math.abs(c) < 1e-15) {
            SEC_POINTS.put(x, Double.NaN);
        } else {
            SEC_POINTS.put(x, 1.0 / c);
        }
    }

    private TrigTestData() {
    }

    public static Map<Double, Double> getSinPoints() {
        return new HashMap<>(SIN_POINTS);
    }

    public static Map<Double, Double> getCosPoints() {
        return new HashMap<>(COS_POINTS);
    }

    public static Map<Double, Double> getCotPoints() {
        return new HashMap<>(COT_POINTS);
    }

    public static Map<Double, Double> getSecPoints() {
        return new HashMap<>(SEC_POINTS);
    }

    public static Map<Double, Double> getCscPoints() {
        return new HashMap<>(CSC_POINTS);
    }
}