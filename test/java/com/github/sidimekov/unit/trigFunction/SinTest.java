package com.github.sidimekov.unit.trigFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.sidimekov.trigFunction.Sin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SinTest {
    private static final double EPS = 1e-9;
    private static final double DELTA = 1e-6;
    private final Sin sin = new Sin(EPS);

    @Test
    @DisplayName("синус - типичные отрицательные значения")
    public void shouldMatchMathSinForTypicalNegativeValues() {
        double[] xs = {-Math.PI / 6, -Math.PI / 4, -Math.PI / 2, -1.0};

        for (double x : xs) {
            assertEquals(Math.sin(x), sin.compute(x), DELTA);
        }
    }
    @Test
    @DisplayName("синус граничное значение ноль")
    void shouldReturnZeroAtBoundaryXEqualsZero() {
        assertEquals(0.0, sin.compute(0.0), DELTA);
    }

    @Test
    @DisplayName("синус - проверка около нуля слева")
    void shouldBeStableNearZeroFromLeft() {
        double x = -1e-6;
        assertEquals(Math.sin(x), sin.compute(x), DELTA);
    }
}
