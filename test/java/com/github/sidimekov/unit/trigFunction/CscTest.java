package com.github.sidimekov.unit.trigFunction;

import com.github.sidimekov.stubs.SinStub;
import com.github.sidimekov.trigFunction.Csc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CscTest {

    private final SinStub sinStub = new SinStub(Map.of(
            -Math.PI / 2, -1.0,
            -Math.PI / 6, -0.5,
            0.0, 0.0,
            -Math.PI, 0.0
    ));

    private static final double DELTA = 1e-6;
    private final Csc csc = new Csc(sinStub);

    @Test
    @DisplayName("косеканс в корректных точках ОДЗ")
    void shouldCalculateCscInValidPoints() {
        // csc(-pi/2) = 1 / -1 = -1
        assertEquals(-1.0, csc.compute(-Math.PI / 2), DELTA);

        // csc(-pi/6) = 1 / -0.5 = -2
        assertEquals(-2.0, csc.compute(-Math.PI / 6), DELTA);
    }

    @Test
    @DisplayName("косеканс в точках разрыва (sin x = 0)")
    void shouldHandleDiscontinuities() {
        double resultAtZero = csc.compute(0.0);
        double resultAtMinusPi = csc.compute(-Math.PI);

        assertTrue(Double.isInfinite(resultAtZero) || Double.isNaN(resultAtZero),
                "В точке 0.0 ожидается бесконечность или NaN");
        assertTrue(Double.isInfinite(resultAtMinusPi) || Double.isNaN(resultAtMinusPi),
                "В точке -PI ожидается бесконечность или NaN");
    }
}