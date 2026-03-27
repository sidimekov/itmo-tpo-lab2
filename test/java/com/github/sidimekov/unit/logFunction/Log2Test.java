package com.github.sidimekov.unit.logFunction;

import com.github.sidimekov.logFunction.Ln;
import com.github.sidimekov.logFunction.Log2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Log2Test {

    private static final double DELTA = 1e-6;
    private final Ln lnMock = mock(Ln.class);
    private final Log2 log2 = new Log2(lnMock);

    public Log2Test() {
        when(lnMock.compute(1.0)).thenReturn(0.0);
        when(lnMock.compute(2.0)).thenReturn(0.6931471805599453);
        when(lnMock.compute(4.0)).thenReturn(1.3862943611198906);
        when(lnMock.compute(0.5)).thenReturn(-0.6931471805599453);
    }

    @Test
    @DisplayName("log2(x) в корректных точках")
    void shouldCalculateLog2InValidPoints() {
        assertAll(
                // log2(1) = 0
                () -> assertEquals(0.0, log2.compute(1.0), DELTA),

                // log2(2) = 1
                () -> assertEquals(1.0, log2.compute(2.0), DELTA),

                // log2(4) = 2
                () -> assertEquals(2.0, log2.compute(4.0), DELTA),

                // log2(0.5) = -1
                () -> assertEquals(-1.0, log2.compute(0.5), DELTA)
        );
    }
}
