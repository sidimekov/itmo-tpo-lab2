package com.github.sidimekov.unit.logFunction;

import com.github.sidimekov.logFunction.Ln;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LnTest {

    private static final double EPS = 1e-6;
    private static final double DELTA = 1e-4; // Дельта для сравнения результатов ряда
    private final Ln ln = new Ln(EPS);

    @Test
    @DisplayName("ln(x) в точке x = 1")
    void shouldReturnZeroAtOne() {
        assertEquals(0.0, ln.compute(1.0), DELTA);
    }

    @Test
    @DisplayName("ln(x) для корректных положительных значений (x > 0)")
    void shouldCalculateLnForPositiveValues() {
        //  x < 1 (результат < 0)
        assertEquals(Math.log(0.5), ln.compute(0.5), DELTA);

        //  x > 1 (результат > 0)
        assertEquals(Math.log(2.0), ln.compute(2.0), DELTA);
        assertEquals(Math.log(Math.E), ln.compute(Math.E), DELTA);
        assertEquals(Math.log(10.0), ln.compute(10.0), DELTA);
    }

    @Test
    @DisplayName("ln(x) вне ОДЗ (x <= 0)")
    void shouldHandleValuesOutsideDomain() {
        // NaN / -Infinity
        double resultAtZero = ln.compute(0.0);
        double resultAtNegative = ln.compute(-5.0);

        assertTrue(Double.isNaN(resultAtZero),
                "Для x=0 ожидается NaN");
        assertTrue(Double.isNaN(resultAtNegative),
                "Для x<0 ожидается NaN");
    }
}