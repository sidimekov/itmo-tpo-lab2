package com.github.sidimekov.unit.logFunction;

import com.github.sidimekov.stubs.LnStub;
import com.github.sidimekov.logFunction.Log2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Log2Test {

    private final LnStub lnStub = new LnStub(Map.of(
            1.0, 0.0,
            2.0, 0.6931471805599453,
            4.0, 1.3862943611198906,
            0.5, -0.6931471805599453
    ));

    private static final double DELTA = 1e-6;
    private final Log2 log2 = new Log2(lnStub);

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