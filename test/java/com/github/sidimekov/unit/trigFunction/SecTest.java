package com.github.sidimekov.unit.trigFunction;

import com.github.sidimekov.stubs.CosStub;
import com.github.sidimekov.trigFunction.Sec;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SecTest {

    private final CosStub cosStub = new CosStub(Map.of(
            0.0, 1.0,
            -Math.PI / 3, 0.5,
            -Math.PI, -1.0,
            -Math.PI / 2, 0.0
    ));

    private static final double DELTA = 1e-6;
    private final Sec sec = new Sec(cosStub);

    @Test
    @DisplayName("секанс в корректных точках ОДЗ")
    void shouldCalculateSecInValidPoints() {
        assertAll(
                // sec(0) = 1 / 1 = 1
                () -> assertEquals(1.0, sec.compute(0.0), DELTA),
                // sec(-pi/3) = 1 / 0.5 = 2
                () -> assertEquals(2.0, sec.compute(-Math.PI / 3), DELTA),
                // sec(-pi) = 1 / -1 = -1
                () -> assertEquals(-1.0, sec.compute(-Math.PI), DELTA)
        );
    }

    @Test
    @DisplayName("секанс в точке разрыва (cos x = 0)")
    void shouldHandleDiscontinuityAtPiOverTwo() {
        double result = sec.compute(-Math.PI / 2);
        assertTrue(Double.isInfinite(result) || Double.isNaN(result),
                "В точке -PI/2 ожидается бесконечность или NaN");
    }
}