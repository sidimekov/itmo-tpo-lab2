package com.github.sidimekov.unit.logFunction;

import com.github.sidimekov.logFunction.Ln;
import com.github.sidimekov.logFunction.Log3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Log3Test {

    private static final double DELTA = 1e-6;
    private final Ln lnMock = mock(Ln.class);
    private final Log3 log3 = new Log3(lnMock);

    public Log3Test() {
        when(lnMock.compute(1.0)).thenReturn(0.0);
        when(lnMock.compute(3.0)).thenReturn(1.0986122886681098);
        when(lnMock.compute(9.0)).thenReturn(2.1972245773362196);
        when(lnMock.compute(1.0 / 3.0)).thenReturn(-1.0986122886681098);
    }

    @Test
    @DisplayName("log3(x) в корректных точках")
    void shouldCalculateLog3InValidPoints() {
        assertAll(
                // log3(1) = 0
                () -> assertEquals(0.0, log3.compute(1.0), DELTA),

                // log3(3) = 1
                () -> assertEquals(1.0, log3.compute(3.0), DELTA),

                // log3(9) = 2
                () -> assertEquals(2.0, log3.compute(9.0), DELTA),

                // log3(1/3) = -1
                () -> assertEquals(-1.0, log3.compute(1.0 / 3.0), DELTA)
        );
    }
}
