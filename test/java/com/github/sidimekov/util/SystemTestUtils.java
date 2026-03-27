package com.github.sidimekov.util;

import com.github.sidimekov.functionSystem.MainSystem;

import static org.junit.jupiter.api.Assertions.*;

public final class SystemTestUtils {
    // Точки для тригонометрии
    public static final double X_VALID_TRIG = -1.0;
    public static final double X_QUADRANT_IV = -Math.PI / 4;
    public static final double X_NEAR_ZERO = -1e-6;
    public static final double X_SIN_DISCONTINUITY = -Math.PI;
    public static final double X_SEC_DISCONTINUITY = -Math.PI / 2;
    public static final double X_ZERO = 0.0;

    // Точки для логарифмов
    public static final double X_LOG_SMALL = 0.1;
    public static final double X_LOG_ONE = 1.0;
    public static final double X_LOG_BIG = 2.0;

    private SystemTestUtils() {
    }

    public static double expectedTrig(double x) {
        double s = Math.sin(x);
        double c = Math.cos(x);
        double cot = c / s;
        double sec = 1.0 / c;
        double csc = 1.0 / s;
        return (((s + cot) * c) - sec) + Math.pow(sec, 3) + csc;
    }

    public static double expectedLog(double x) {
        double ln = Math.log(x);
        double l2 = Math.log(x) / Math.log(2);
        double l3 = Math.log(x) / Math.log(3);
        return (Math.pow(Math.pow(l3, 2), 3) - ln + l2) + ((l3 + (l3 - ln)) - Math.pow(l2, 3));
    }

    public static void assertIsDiscontinuity(double value, String message) {
        // Число считается разрывом, если оно NaN, Infinity или больше 10^10
        boolean isBroken = !Double.isFinite(value) || Math.abs(value) > 1e10;
        assertTrue(isBroken, message + " (получено значение: " + value + ")");
    }

    /**
     * Универсальная проверка поведения системы для всех интеграционных тестов.
     */
    public static void assertCommonSystemBehavior(MainSystem system, double delta) {
        assertAll("Комплексная проверка системы по классам эквивалентности",
                // 1. Тригонометрическая ветвь
                () -> assertEquals(expectedTrig(X_VALID_TRIG), system.compute(X_VALID_TRIG), delta, "Ошибка в x=-1"),
                () -> assertEquals(expectedTrig(X_QUADRANT_IV), system.compute(X_QUADRANT_IV), delta, "Ошибка в IV четверти"),
                () -> assertEquals(expectedTrig(X_NEAR_ZERO), system.compute(X_NEAR_ZERO), delta, "Ошибка вблизи нуля (слева)"),

                // 2. Логарифмическая ветвь (x > 0)
                () -> assertEquals(expectedLog(X_LOG_SMALL), system.compute(X_LOG_SMALL), delta, "Ошибка в лог-ветви (x < 1)"),
                () -> assertEquals(0.0, system.compute(X_LOG_ONE), delta, "В точке x=1 результат должен быть 0"),
                () -> assertEquals(expectedLog(X_LOG_BIG), system.compute(X_LOG_BIG), delta, "Ошибка в лог-ветви (x > 1)")
        );
    }
}