package com.github.sidimekov.unit.functionSystem;

import com.github.sidimekov.functionSystem.LogModule;
import com.github.sidimekov.logFunction.Ln;
import com.github.sidimekov.logFunction.Log2;
import com.github.sidimekov.logFunction.Log3;
import com.github.sidimekov.util.LogTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Тесты модуля логарифмов")
public class LogModuleTest {
    private static final double EPS = 1e-9;

    private static final double X_INTERVAL_ZERO_TO_ONE = 0.5;
    private static final double X_ONE = 1.0;
    private static final double X_INTERVAL_ABOVE_ONE = 4.0;
    private static final double X_NEAR_ZERO_POSITIVE = 1e-6;
    private static final double X_BASE_TWO = 2.0;
    private static final double X_BASE_THREE = 3.0;

    private LogModule logModule;
    private Ln lnMock;
    private Log2 log2Mock;
    private Log3 log3Mock;

    @BeforeEach
    void setUp() {
        Map<Double, Double> lnValues = LogTestData.getLnPoints();
        Map<Double, Double> log2Values = LogTestData.getLog2Points();
        Map<Double, Double> log3Values = LogTestData.getLog3Points();

        lnMock = mock(Ln.class);
        log2Mock = mock(Log2.class);
        log3Mock = mock(Log3.class);

        when(lnMock.compute(anyDouble())).thenAnswer(invocation -> lnValues.get(invocation.getArgument(0)));
        when(log2Mock.compute(anyDouble())).thenAnswer(invocation -> log2Values.get(invocation.getArgument(0)));
        when(log3Mock.compute(anyDouble())).thenAnswer(invocation -> log3Values.get(invocation.getArgument(0)));

        logModule = new LogModule(
                lnMock,
                log2Mock,
                log3Mock
        );
    }

    @Test
    @DisplayName("Считает корректно в интервале от нуля до единицы")
    void shouldComputeInIntervalZeroToOne() {
        double actual = logModule.compute(X_INTERVAL_ZERO_TO_ONE);
        double expected = expectedFormula(X_INTERVAL_ZERO_TO_ONE);

        assertEquals(expected, actual, EPS);
    }

    @Test
    @DisplayName("Возвращает ноль в точке один")
    void shouldReturnZeroAtOne() {
        double actual = logModule.compute(X_ONE);

        assertEquals(0.0, actual, EPS);
    }

    @Test
    @DisplayName("Считает корректно в интервале больше единицы")
    void shouldComputeInIntervalAboveOne() {
        double actual = logModule.compute(X_INTERVAL_ABOVE_ONE);
        double expected = expectedFormula(X_INTERVAL_ABOVE_ONE);

        assertEquals(expected, actual, EPS);
    }

    @Test
    @DisplayName("Считает устойчиво на малом положительном значении")
    void shouldComputeNearZeroPositive() {
        double actual = logModule.compute(X_NEAR_ZERO_POSITIVE);
        double expected = expectedFormula(X_NEAR_ZERO_POSITIVE);

        assertEquals(expected, actual, EPS);
        assertTrue(Double.isFinite(actual));
    }

    @Test
    @DisplayName("Считает корректно в точке основания два")
    void shouldComputeAtBaseTwoPoint() {
        double actual = logModule.compute(X_BASE_TWO);
        double expected = expectedFormula(X_BASE_TWO);

        assertEquals(expected, actual, EPS);
    }

    @Test
    @DisplayName("Считает корректно в точке основания три")
    void shouldComputeAtBaseThreePoint() {
        double actual = logModule.compute(X_BASE_THREE);
        double expected = expectedFormula(X_BASE_THREE);

        assertEquals(expected, actual, EPS);
    }

    private double expectedFormula(double x) {
        double ln = Math.log(x);
        double log2 = Math.log(x) / Math.log(2);
        double log3 = Math.log(x) / Math.log(3);

        return ((Math.pow(Math.pow(log3, 2), 3) - ln + log2)
                + ((log3 + (log3 - ln)) - Math.pow(log2, 3)));
    }
}
