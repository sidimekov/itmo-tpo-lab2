package com.github.sidimekov.util;

import java.util.HashMap;
import java.util.Map;

public final class TrigTestData {
    private static final double X_QUADRANT_IV = -0.7853981633974483;   // -pi/4
    private static final double X_QUADRANT_III = -2.356194490192345;   // -3pi/4
    private static final double X_SIN_DISCONTINUITY = -3.141592653589793; // -pi
    private static final double X_SEC_DISCONTINUITY = -1.5707963267948966; // -pi/2
    private static final double X_NEAR_ZERO_NEGATIVE = -1e-6;
    private static final double X_ZERO_CRITICAL = 0.0;

    private static final Map<Double, Double> SIN_POINTS = new HashMap<>();
    private static final Map<Double, Double> COS_POINTS = new HashMap<>();
    private static final Map<Double, Double> COT_POINTS = new HashMap<>();
    private static final Map<Double, Double> SEC_POINTS = new HashMap<>();
    private static final Map<Double, Double> CSC_POINTS = new HashMap<>();

    static {
        SIN_POINTS.put(X_QUADRANT_IV, -0.7071067811865475);
        SIN_POINTS.put(X_QUADRANT_III, -0.7071067811865476);
        SIN_POINTS.put(X_SIN_DISCONTINUITY, 0.0);
        SIN_POINTS.put(X_SEC_DISCONTINUITY, -1.0);
        SIN_POINTS.put(X_NEAR_ZERO_NEGATIVE, -9.99999999e-07);
        SIN_POINTS.put(X_ZERO_CRITICAL, 0.0);

        COS_POINTS.put(X_QUADRANT_IV, 0.7071067811865476);
        COS_POINTS.put(X_QUADRANT_III, -0.7071067811865475);
        COS_POINTS.put(X_SIN_DISCONTINUITY, -1.0);
        COS_POINTS.put(X_SEC_DISCONTINUITY, 0.0);
        COS_POINTS.put(X_NEAR_ZERO_NEGATIVE, 0.9999999999995);
        COS_POINTS.put(X_ZERO_CRITICAL, 1.0);

        COT_POINTS.put(X_QUADRANT_IV, -1.0000000000000002);
        COT_POINTS.put(X_QUADRANT_III, 0.9999999999999999);
        COT_POINTS.put(X_SIN_DISCONTINUITY, Double.NaN);
        COT_POINTS.put(X_SEC_DISCONTINUITY, -0.0);
        COT_POINTS.put(X_NEAR_ZERO_NEGATIVE, -999999.9999996667);
        COT_POINTS.put(X_ZERO_CRITICAL, Double.NaN);

        SEC_POINTS.put(X_QUADRANT_IV, 1.414213562373095);
        SEC_POINTS.put(X_QUADRANT_III, -1.4142135623730951);
        SEC_POINTS.put(X_SIN_DISCONTINUITY, -1.0);
        SEC_POINTS.put(X_SEC_DISCONTINUITY, Double.NaN);
        SEC_POINTS.put(X_NEAR_ZERO_NEGATIVE, 1.0000000000005);
        SEC_POINTS.put(X_ZERO_CRITICAL, 1.0);

        CSC_POINTS.put(X_QUADRANT_IV, -1.4142135623730951);
        CSC_POINTS.put(X_QUADRANT_III, -1.414213562373095);
        CSC_POINTS.put(X_SIN_DISCONTINUITY, Double.NaN);
        CSC_POINTS.put(X_SEC_DISCONTINUITY, -1.0);
        CSC_POINTS.put(X_NEAR_ZERO_NEGATIVE, -1000000.0000001667);
        CSC_POINTS.put(X_ZERO_CRITICAL, Double.NaN);
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
