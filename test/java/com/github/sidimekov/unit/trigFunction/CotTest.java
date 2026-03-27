package com.github.sidimekov.unit.trigFunction;

import com.github.sidimekov.trigFunction.Cos;
import com.github.sidimekov.trigFunction.Cot;
import com.github.sidimekov.trigFunction.Sin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CotTest {

    private static final double DELTA = 1e-6;
    private final Sin sinMock = mock(Sin.class);
    private final Cos cosMock = mock(Cos.class);
    private final Cot cot = new Cot(sinMock, cosMock);

    public CotTest() {
        when(sinMock.compute(-Math.PI / 4)).thenReturn(-Math.sqrt(2) / 2);
        when(sinMock.compute(-Math.PI / 2)).thenReturn(-1.0);
        when(sinMock.compute(-Math.PI / 6)).thenReturn(-0.5);
        when(sinMock.compute(0.0)).thenReturn(0.0);

        when(cosMock.compute(-Math.PI / 4)).thenReturn(Math.sqrt(2) / 2);
        when(cosMock.compute(-Math.PI / 2)).thenReturn(0.0);
        when(cosMock.compute(-Math.PI / 6)).thenReturn(Math.sqrt(3) / 2);
        when(cosMock.compute(0.0)).thenReturn(1.0);
    }

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
