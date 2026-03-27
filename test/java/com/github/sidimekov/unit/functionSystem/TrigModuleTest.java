package com.github.sidimekov.unit.functionSystem;

import com.github.sidimekov.functionSystem.TrigModule;
import com.github.sidimekov.trigFunction.Cos;
import com.github.sidimekov.trigFunction.Cot;
import com.github.sidimekov.trigFunction.Csc;
import com.github.sidimekov.trigFunction.Sec;
import com.github.sidimekov.trigFunction.Sin;
import com.github.sidimekov.util.TrigTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrigModuleTest {
    private static final double EPS = 1e-9;

    private static final double X_QUADRANT_IV = -0.7853981633974483;   // -pi/4
    private static final double X_QUADRANT_III = -2.356194490192345;   // -3pi/4
    private static final double X_SIN_DISCONTINUITY = -3.141592653589793; // -pi
    private static final double X_SEC_DISCONTINUITY = -1.5707963267948966; // -pi/2
    private static final double X_NEAR_ZERO_NEGATIVE = -1e-6;
    private static final double X_ZERO_CRITICAL = 0.0;

    private TrigModule trigModule;
    private Sin sinMock;
    private Cos cosMock;
    private Cot cotMock;
    private Sec secMock;
    private Csc cscMock;

    @BeforeEach
    void setUp() {
        Map<Double, Double> sinValues = TrigTestData.getSinPoints();
        Map<Double, Double> cosValues = TrigTestData.getCosPoints();
        Map<Double, Double> cotValues = TrigTestData.getCotPoints();
        Map<Double, Double> secValues = TrigTestData.getSecPoints();
        Map<Double, Double> cscValues = TrigTestData.getCscPoints();

        sinMock = mock(Sin.class);
        cosMock = mock(Cos.class);
        cotMock = mock(Cot.class);
        secMock = mock(Sec.class);
        cscMock = mock(Csc.class);

        when(sinMock.compute(anyDouble())).thenAnswer(invocation -> sinValues.get(invocation.getArgument(0)));
        when(cosMock.compute(anyDouble())).thenAnswer(invocation -> cosValues.get(invocation.getArgument(0)));
        when(cotMock.compute(anyDouble())).thenAnswer(invocation -> cotValues.get(invocation.getArgument(0)));
        when(secMock.compute(anyDouble())).thenAnswer(invocation -> secValues.get(invocation.getArgument(0)));
        when(cscMock.compute(anyDouble())).thenAnswer(invocation -> cscValues.get(invocation.getArgument(0)));

        trigModule = new TrigModule(
                sinMock,
                cosMock,
                cotMock,
                secMock,
                cscMock
        );
    }

    @Test
    @DisplayName("Проверка вычисления в IV четверти (x = -pi/4)")
    void shouldComputeForValidValuesInFourthQuadrant() {
        double actual = trigModule.compute(X_QUADRANT_IV);
        double expected = expectedFormula(X_QUADRANT_IV);

        assertEquals(expected, actual, EPS);
    }

    @Test
    @DisplayName("Проверка вычисления в III четверти (x = -3pi/4)")
    void shouldComputeForValidValuesInThirdQuadrant() {
        double actual = trigModule.compute(X_QUADRANT_III);
        double expected = expectedFormula(X_QUADRANT_III);

        assertEquals(expected, actual, EPS);
    }

    @Test
    @DisplayName("Проверка точки разрыва при sin(x) = 0 (x = -pi)")
    void shouldReturnNaNAtCotAndCscDiscontinuityWhenSinIsZero() {
        double actual = trigModule.compute(X_SIN_DISCONTINUITY);

        assertTrue(Double.isNaN(actual));
    }

    @Test
    @DisplayName("Проверка точки разрыва при cos(x) = 0 (x = -pi/2)")
    void shouldReturnNaNAtSecDiscontinuityWhenCosIsZero() {
        double actual = trigModule.compute(X_SEC_DISCONTINUITY);

        assertTrue(Double.isNaN(actual));
    }

    @Test
    @DisplayName("Проверка точности вблизи границы ветвей (x -> 0-)")
    void shouldComputeForSmallNegativeValueNearZero() {
        double actual = trigModule.compute(X_NEAR_ZERO_NEGATIVE);
        double expected = expectedFormula(X_NEAR_ZERO_NEGATIVE);

        assertEquals(expected, actual, EPS);
    }

    @Test
    @DisplayName("Проверка критической точки x = 0")
    void shouldReturnNaNAtCriticalZeroPoint() {
        double actual = trigModule.compute(X_ZERO_CRITICAL);

        assertTrue(Double.isNaN(actual));
    }

    private double expectedFormula(double x) {
        double sin = Math.sin(x);
        double cos = Math.cos(x);
        double cot = cos / sin;
        double sec = 1 / cos;
        double csc = 1 / sin;
        return (((sin + cot) * cos) - sec) + Math.pow(sec, 3) + csc;
    }
}
