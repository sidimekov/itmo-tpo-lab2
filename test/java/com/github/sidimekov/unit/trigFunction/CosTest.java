package com.github.sidimekov.unit.trigFunction;

import com.github.sidimekov.trigFunction.Sin;
import com.github.sidimekov.trigFunction.Cos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CosTest {

    private static final double DELTA = 1e-6;
    private final Sin sinMock = mock(Sin.class);
    private final Cos cos = new Cos(sinMock);

    public CosTest() {
        when(sinMock.compute(-Math.PI / 3)).thenReturn(-Math.sqrt(3) / 2);
        when(sinMock.compute(-Math.PI / 4)).thenReturn(-Math.sqrt(2) / 2);
        when(sinMock.compute(0.0)).thenReturn(0.0);
        when(sinMock.compute(Math.PI / 3)).thenReturn(Math.sqrt(3) / 2);
        when(sinMock.compute(-Math.PI / 2)).thenReturn(-1.0);
        when(sinMock.compute(-Math.PI)).thenReturn(0.0);
        when(sinMock.compute(-2 * Math.PI / 3)).thenReturn(-Math.sqrt(3) / 2);
    }

    @Test
    @DisplayName("косинус значения в основном диапазоне")
    void shouldMatchMathCosInsidePrincipalRange() {
        double[] xs = {-Math.PI / 3, -Math.PI / 4, 0.0, Math.PI / 3};

        for (double x : xs) {
            assertEquals(Math.cos(x), cos.compute(x), DELTA);
        }
    }

    @Test
    @DisplayName("косинус около пи/2")
    void shouldBeCloseToZeroAtPiOverTwoDiscontinuityForSec() {
        assertEquals(0.0, cos.compute(-Math.PI / 2), DELTA);
    }

    @Test
    @DisplayName("косинус в разных четвертях (проверка знака)")
    void shouldHandleSignChangeInDifferentQuadrants() {
        // x = -Math.PI (cos = -1), x = -2*Math.PI/3 (cos = -0.5)
        assertEquals(-1.0, cos.compute(-Math.PI), DELTA);
        assertEquals(-0.5, cos.compute(-2 * Math.PI / 3), DELTA);
    }
}
