package com.github.sidimekov.unit.functionSystem;

import com.github.sidimekov.functionSystem.LogModule;
import com.github.sidimekov.functionSystem.MainSystem;
import com.github.sidimekov.functionSystem.TrigModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainSystemTest {
    private static final double DELTA = 1e-9;

    private MainSystem mainSystem;
    private TrigModule trigModuleMock;
    private LogModule logModuleMock;

    @BeforeEach
    void setUp() {
        Map<Double, Double> trigTable = Map.of(
                -1.0, calculateTrigExpected(-1.0),
                -Math.PI / 2, Double.NaN, // Разрыв sec
                0.0, Double.NaN           // Разрыв cot/csc
        );

        Map<Double, Double> logTable = Map.of(
                0.1, calculateLogExpected(0.1),
                1.0, 0.0,
                2.0, calculateLogExpected(2.0)
        );

        trigModuleMock = mock(TrigModule.class);
        logModuleMock = mock(LogModule.class);

        trigTable.forEach((x, value) -> when(trigModuleMock.compute(x)).thenReturn(value));
        logTable.forEach((x, value) -> when(logModuleMock.compute(x)).thenReturn(value));

        mainSystem = new MainSystem(trigModuleMock, logModuleMock);
    }

    @Nested
    @DisplayName("TrigBranch: x <= 0")
    class TrigBranch {

        @Test
        @DisplayName("должен совпадать с TrigModule mock на -1, -пи/2 и 0")
        void shouldMatchTrigStubForNonPositivePoints() {
            double[] points = {-1.0, -Math.PI / 2, 0.0};
            for (double x : points) {
                assertEquals(trigModuleMock.compute(x), mainSystem.compute(x), DELTA,
                        "Ошибка в x = " + x);
            }
        }

        @Test
        @DisplayName("x = 0: выбирается триг модуль и результат не определен")
        void shouldUseTrigBranchAndReturnNaNAtZero() {
            double actual = mainSystem.compute(0.0);
            assertTrue(Double.isNaN(actual), "При x=0 ожидается NaN из-за разрывов тригонометрии");
        }
    }

    @Nested
    @DisplayName("LogBranch: x > 0")
    class LogBranch {

        @Test
        @DisplayName("Должен корректно выбирать лог ветвь для положительных x (LogModule mock)")
        void shouldMatchLogStubForPositivePoints() {
            double[] points = {0.1, 1.0, 2.0};
            for (double x : points) {
                assertEquals(logModuleMock.compute(x), mainSystem.compute(x), DELTA,
                        "Ошибка в x = " + x);
            }
        }
    }

    private double calculateTrigExpected(double x) {
        double s = Math.sin(x);
        double c = Math.cos(x);
        double cot = c / s;
        double sec = 1.0 / c;
        double csc = 1.0 / s;
        return (((s + cot) * c) - sec) + Math.pow(sec, 3) + csc;
    }

    private double calculateLogExpected(double x) {
        double ln = Math.log(x);
        double l2 = Math.log(x) / Math.log(2);
        double l3 = Math.log(x) / Math.log(3);
        return (Math.pow(Math.pow(l3, 2), 3) - ln + l2) + ((l3 + (l3 - ln)) - Math.pow(l2, 3));
    }
}
