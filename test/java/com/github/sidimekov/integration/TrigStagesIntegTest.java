package com.github.sidimekov.integration;

import com.github.sidimekov.Function;
import com.github.sidimekov.functionSystem.LogModule;
import com.github.sidimekov.functionSystem.MainSystem;
import com.github.sidimekov.functionSystem.TrigModule;
import com.github.sidimekov.trigFunction.Cos;
import com.github.sidimekov.trigFunction.Cot;
import com.github.sidimekov.trigFunction.Csc;
import com.github.sidimekov.trigFunction.Sec;
import com.github.sidimekov.trigFunction.Sin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TrigStagesIntegTest {
    private static final double EPS = 1e-8;
    private static final double DELTA = 1e-5;
    private static final double X_VALID_TRIG = -1.0;
    private static final double X_QUADRANT_IV = -Math.PI / 4;
    private static final double X_NEAR_ZERO = -1e-6;
    private static final double X_SIN_DISCONTINUITY = -Math.PI;
    private static final double X_SEC_DISCONTINUITY = -Math.PI / 2;
    private static final double X_ZERO = 0.0;

    private static final double X_LOG_SMALL = 0.1;
    private static final double X_LOG_ONE = 1.0;
    private static final double X_LOG_BIG = 2.0;

    @Nested
    @DisplayName("stage0_all_stubs")
    class Stage0AllStubs {
        @Test
        void shouldWorkWithAllStubs() {
            assertCommonBehavior(buildSystem(buildStage0TrigModule()));
        }
    }

    @Nested
    @DisplayName("stage1_sin")
    class Stage1Sin {
        @Test
        void shouldWorkWithRealSin() {
            assertCommonBehavior(buildSystem(buildStage1TrigModule()));
        }
    }

    @Nested
    @DisplayName("stage2_cos")
    class Stage2Cos {
        @Test
        void shouldWorkWithRealSinCos() {
            assertCommonBehavior(buildSystem(buildStage2TrigModule()));
        }
    }

    @Nested
    @DisplayName("stage3_cot")
    class Stage3Cot {
        @Test
        void shouldWorkWithRealSinCosCot() {
            assertCommonBehavior(buildSystem(buildStage3TrigModule()));
        }
    }

    @Nested
    @DisplayName("stage4_sec")
    class Stage4Sec {
        @Test
        void shouldWorkWithRealSinCosCotSec() {
            assertCommonBehavior(buildSystem(buildStage4TrigModule()));
        }
    }

    @Nested
    @DisplayName("stage5_csc")
    class Stage5Csc {
        @Test
        void shouldWorkWithRealTrigModule() {
            assertCommonBehavior(buildSystem(buildStage5TrigModule()));
        }
    }

    private MainSystem buildSystem(Function trigModule) {
        return new MainSystem(trigModule, buildLogModuleMock(buildLogResultsTable()));
    }

    private void assertCommonBehavior(MainSystem system) {
        assertAll("Комплексная проверка системы по классам эквивалентности",
                () -> assertEquals(expectedTrig(X_VALID_TRIG), system.compute(X_VALID_TRIG), DELTA, "Ошибка в обычной точке x=-1"),
                () -> assertEquals(expectedTrig(X_QUADRANT_IV), system.compute(X_QUADRANT_IV), DELTA, "Ошибка в IV четверти"),
                () -> assertEquals(expectedTrig(X_NEAR_ZERO), system.compute(X_NEAR_ZERO), DELTA, "Ошибка вблизи нуля (слева)"),
                () -> assertEquals(expectedLog(X_LOG_SMALL), system.compute(X_LOG_SMALL), DELTA, "Ошибка в лог-ветви (x < 1)"),
                () -> assertEquals(0.0, system.compute(X_LOG_ONE), DELTA, "В точке x=1 результат должен быть 0"),
                () -> assertEquals(expectedLog(X_LOG_BIG), system.compute(X_LOG_BIG), DELTA, "Ошибка в лог-ветви (x > 1)")
        );
    }

    private Function buildStage0TrigModule() {
        return new TrigModule(
                buildSinMock(buildSinTable()),
                buildCosMock(buildCosTable()),
                buildCotMock(buildCotTable()),
                buildSecMock(buildSecTable()),
                buildCscMock(buildCscTable())
        );
    }

    private Function buildStage1TrigModule() {
        return new TrigModule(
                new Sin(EPS),
                buildCosMock(buildCosTable()),
                buildCotMock(buildCotTable()),
                buildSecMock(buildSecTable()),
                buildCscMock(buildCscTable())
        );
    }

    private Function buildStage2TrigModule() {
        Sin sin = new Sin(EPS);
        Cos cos = new Cos(sin);
        return new TrigModule(
                sin,
                cos,
                buildCotMock(buildCotTable()),
                buildSecMock(buildSecTable()),
                buildCscMock(buildCscTable())
        );
    }

    private Function buildStage3TrigModule() {
        Sin sin = new Sin(EPS);
        Cos cos = new Cos(sin);
        Cot cot = new Cot(sin, cos);
        return new TrigModule(
                sin,
                cos,
                cot,
                buildSecMock(buildSecTable()),
                buildCscMock(buildCscTable())
        );
    }

    private Function buildStage4TrigModule() {
        Sin sin = new Sin(EPS);
        Cos cos = new Cos(sin);
        Cot cot = new Cot(sin, cos);
        Sec sec = new Sec(cos);
        return new TrigModule(
                sin,
                cos,
                cot,
                sec,
                buildCscMock(buildCscTable())
        );
    }

    private Function buildStage5TrigModule() {
        Sin sin = new Sin(EPS);
        Cos cos = new Cos(sin);
        Cot cot = new Cot(sin, cos);
        Sec sec = new Sec(cos);
        Csc csc = new Csc(sin);
        return new TrigModule(sin, cos, cot, sec, csc);
    }

    private Map<Double, Double> buildSinTable() {
        return createTable(Math::sin);
    }

    private Map<Double, Double> buildCosTable() {
        return createTable(Math::cos);
    }

    private Map<Double, Double> buildCotTable() {
        return createTable(x -> (Math.abs(Math.sin(x)) < 1e-10) ? Double.NaN : 1.0 / Math.tan(x));
    }

    private Map<Double, Double> buildSecTable() {
        return createTable(x -> (Math.abs(Math.cos(x)) < 1e-10) ? Double.NaN : 1.0 / Math.cos(x));
    }

    private Map<Double, Double> buildCscTable() {
        return createTable(x -> (Math.abs(Math.sin(x)) < 1e-10) ? Double.NaN : 1.0 / Math.sin(x));
    }

    private Map<Double, Double> createTable(java.util.function.Function<Double, Double> func) {
        Map<Double, Double> table = new HashMap<>();
        double[] points = {X_VALID_TRIG, X_QUADRANT_IV, X_NEAR_ZERO, X_SIN_DISCONTINUITY, X_SEC_DISCONTINUITY, X_ZERO};
        for (double p : points) {
            table.put(p, func.apply(p));
        }
        return table;
    }

    private Map<Double, Double> buildLogResultsTable() {
        Map<Double, Double> table = new HashMap<>();
        table.put(X_LOG_SMALL, expectedLog(X_LOG_SMALL));
        table.put(X_LOG_ONE, 0.0);
        table.put(X_LOG_BIG, expectedLog(X_LOG_BIG));
        return table;
    }

    private LogModule buildLogModuleMock(Map<Double, Double> values) {
        LogModule logModuleMock = mock(LogModule.class);
        when(logModuleMock.compute(anyDouble())).thenAnswer(invocation -> values.get(invocation.getArgument(0)));
        return logModuleMock;
    }

    private Sin buildSinMock(Map<Double, Double> values) {
        Sin sinMock = mock(Sin.class);
        when(sinMock.compute(anyDouble())).thenAnswer(invocation -> values.get(invocation.getArgument(0)));
        return sinMock;
    }

    private Cos buildCosMock(Map<Double, Double> values) {
        Cos cosMock = mock(Cos.class);
        when(cosMock.compute(anyDouble())).thenAnswer(invocation -> values.get(invocation.getArgument(0)));
        return cosMock;
    }

    private Cot buildCotMock(Map<Double, Double> values) {
        Cot cotMock = mock(Cot.class);
        when(cotMock.compute(anyDouble())).thenAnswer(invocation -> values.get(invocation.getArgument(0)));
        return cotMock;
    }

    private Sec buildSecMock(Map<Double, Double> values) {
        Sec secMock = mock(Sec.class);
        when(secMock.compute(anyDouble())).thenAnswer(invocation -> values.get(invocation.getArgument(0)));
        return secMock;
    }

    private Csc buildCscMock(Map<Double, Double> values) {
        Csc cscMock = mock(Csc.class);
        when(cscMock.compute(anyDouble())).thenAnswer(invocation -> values.get(invocation.getArgument(0)));
        return cscMock;
    }

    private double expectedTrig(double x) {
        double s = Math.sin(x);
        double c = Math.cos(x);
        double cot = c / s;
        double sec = 1.0 / c;
        double csc = 1.0 / s;
        return (((s + cot) * c) - sec) + Math.pow(sec, 3) + csc;
    }

    private double expectedLog(double x) {
        double ln = Math.log(x);
        double l2 = Math.log(x) / Math.log(2);
        double l3 = Math.log(x) / Math.log(3);
        return (Math.pow(Math.pow(l3, 2), 3) - ln + l2)
                + ((l3 + (l3 - ln)) - Math.pow(l2, 3));
    }
}
