package com.github.sidimekov.integration;

import com.github.sidimekov.trigFunction.Cos;
import com.github.sidimekov.trigFunction.Cot;
import com.github.sidimekov.trigFunction.Sin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CotIntegTest {

    private static final double EPS = 1e-8;
    private static final double DELTA = 1e-5;

    @Test
    @DisplayName("cot: реальный sin + заглушка cos")
    void shouldComputeCotWithRealSinAndMockedCos() {
        double x = -Math.PI / 4;

        Sin realSin = new Sin(EPS);
        Cos cosMock = mock();
        when(cosMock.compute(x)).thenReturn(Math.cos(x));

        Cot cot = new Cot(realSin, cosMock);

        assertEquals(1.0 / Math.tan(x), cot.compute(x), DELTA);
    }

    @Test
    @DisplayName("cot: заглушка sin + реальный cos")
    void shouldComputeCotWithMockedSinAndRealCos() {
        double x = -Math.PI / 3;

        Sin sinMock = mock();
        when(sinMock.compute(x)).thenReturn(Math.sin(x));

        Cos realCos = new Cos(sinMock);
        Cot cot = new Cot(sinMock, realCos);

        assertEquals(1.0 / Math.tan(x), cot.compute(x), DELTA);
    }

    @Test
    @DisplayName("cot: обе зависимости реальные")
    void shouldComputeCotWithRealSinAndCosOnSeveralPoints() {
        Sin realSin = new Sin(EPS);
        Cos realCos = new Cos(realSin);
        Cot cot = new Cot(realSin, realCos);

        assertAll(
                () -> assertEquals(1.0 / Math.tan(-Math.PI / 6), cot.compute(-Math.PI / 6), DELTA),
                () -> assertEquals(1.0 / Math.tan(-Math.PI / 4), cot.compute(-Math.PI / 4), DELTA),
                () -> assertEquals(1.0 / Math.tan(-Math.PI / 2), cot.compute(-Math.PI / 2), DELTA)
        );
    }

    @Test
    @DisplayName("cot: обе зависимости как заглушки")
    void shouldComputeCotWithMockedSinAndMockedCos() {
        double x = -Math.PI / 4;

        Sin sinMock = mock();
        Cos cosMock = mock();
        when(sinMock.compute(x)).thenReturn(Math.sin(x));
        when(cosMock.compute(x)).thenReturn(Math.cos(x));

        Cot cot = new Cot(sinMock, cosMock);

        assertEquals(1.0 / Math.tan(x), cot.compute(x), DELTA);
    }

    @Test
    @DisplayName("cot: NaN в точке разрыва, когда sin(x)=0")
    void shouldReturnNaNAtDiscontinuity() {
        double x = 0.0;

        Sin realSin = new Sin(EPS);
        Cos cosMock = mock(Cos.class);
        when(cosMock.compute(x)).thenReturn(1.0);

        Cot cot = new Cot(realSin, cosMock);

        assertTrue(Double.isNaN(cot.compute(x)));
    }
}
