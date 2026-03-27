package com.github.sidimekov.unit.trigFunction;

import com.github.sidimekov.trigFunction.Cos;
import com.github.sidimekov.trigFunction.Sec;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SecTest {

    private static final double DELTA = 1e-6;
    private final Cos cosMock = mock(Cos.class);
    private final Sec sec = new Sec(cosMock);

    public SecTest() {
        when(cosMock.compute(0.0)).thenReturn(1.0);
        when(cosMock.compute(-Math.PI / 3)).thenReturn(0.5);
        when(cosMock.compute(-Math.PI)).thenReturn(-1.0);
        when(cosMock.compute(-Math.PI / 2)).thenReturn(0.0);
    }

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
