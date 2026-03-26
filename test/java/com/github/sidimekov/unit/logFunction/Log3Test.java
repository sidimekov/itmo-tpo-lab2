package com.github.sidimekov.unit.logFunction;

import com.github.sidimekov.stubs.LnStub;
import com.github.sidimekov.logFunction.Log3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Log3Test {

    private final LnStub lnStub = new LnStub(Map.of(
            1.0, 0.0,
            3.0, 1.0986122886681098,
            9.0, 2.1972245773362196,
            1.0 / 3.0, -1.0986122886681098
    ));

    private static final double DELTA = 1e-6;
    private final Log3 log3 = new Log3(lnStub);

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