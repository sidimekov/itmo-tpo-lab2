package com.github.sidimekov.unit.trigFunction;

import com.github.sidimekov.stubs.CosStub;
import com.github.sidimekov.stubs.SinStub;
import com.github.sidimekov.trigFunction.Cot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CotTest {

    private final SinStub sinStub = new SinStub(Map.of(
            -Math.PI / 4, -Math.sqrt(2) / 2,
            -Math.PI / 2, -1.0,
            -Math.PI / 6, -0.5,
            0.0, 0.0
    ));

    private final CosStub cosStub = new CosStub(Map.of(
            -Math.PI / 4, Math.sqrt(2) / 2,
            -Math.PI / 2, 0.0,
            -Math.PI / 6, Math.sqrt(3) / 2,
            0.0, 1.0
    ));

    private static final double DELTA = 1e-6;
    private final Cot cot = new Cot(sinStub, cosStub);

    @Test
    @DisplayName("котангенс в корректных точках ОДЗ")
    void shouldCalculateCotInValidPoints() {
        assertAll(
                // cot(-pi/4) = cos(-pi/4) / sin(-pi/4) = -1
                () -> assertEquals(-1.0, cot.compute(-Math.PI / 4), DELTA),
                // cot(-pi/2) = 0 / -1 = 0
                () -> assertEquals(0.0, cot.compute(-Math.PI / 2), DELTA),
                // cot(-pi/6) = (sqrt(3)/2) / -0.5 = -sqrt(3)
                () -> assertEquals(-Math.sqrt(3), cot.compute(-Math.PI / 6), DELTA)
        );
    }

    @Test
    @DisplayName("котангенс в точке разрыва (sin x = 0)")
    void shouldHandleDiscontinuityAtZero() {
        double result = cot.compute(0.0);
        assertTrue(Double.isInfinite(result) || Double.isNaN(result),
                "В точке 0.0 ожидается бесконечность или NaN");
    }
}