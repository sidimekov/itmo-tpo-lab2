package com.github.sidimekov.integration;

import com.github.sidimekov.Function;
import com.github.sidimekov.functionSystem.LogModule;
import com.github.sidimekov.functionSystem.MainSystem;
import com.github.sidimekov.functionSystem.TrigModule;
import com.github.sidimekov.logFunction.Ln;
import com.github.sidimekov.logFunction.Log2;
import com.github.sidimekov.logFunction.Log3;
import com.github.sidimekov.stubs.Log2Stub;
import com.github.sidimekov.stubs.Log3Stub;
import com.github.sidimekov.trigFunction.*;
import com.github.sidimekov.util.SystemTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class LogStagesIntegTest {
    private static final double EPS = 1e-8;
    private static final double DELTA = 1e-5;

    @Nested
    @DisplayName("stage6_ln")
    class Stage6Ln {
        @Test
        void shouldWorkWithRealLn() {
            SystemTestUtils.assertCommonSystemBehavior(buildSystem(buildStage5TrigModule(), buildStage6LogModule()), DELTA);
        }
    }

    @Nested
    @DisplayName("stage7_log2")
    class Stage7Log2 {
        @Test
        void shouldWorkWithRealLnAndLog2() {
            SystemTestUtils.assertCommonSystemBehavior(buildSystem(buildStage5TrigModule(), buildStage7LogModule()), DELTA);
        }
    }

    @Nested
    @DisplayName("stage8_log3")
    class Stage8Log3 {
        @Test
        void shouldWorkWithRealLogModule() {
            SystemTestUtils.assertCommonSystemBehavior(buildSystem(buildStage5TrigModule(), buildStage8LogModule()), DELTA);
        }
    }

    private MainSystem buildSystem(Function trigModule, Function logModule) {
        return new MainSystem(trigModule, logModule);
    }

    private Function buildStage5TrigModule() {
        Sin sin = new Sin(EPS);
        Cos cos = new Cos(sin);
        Cot cot = new Cot(sin, cos);
        Sec sec = new Sec(cos);
        Csc csc = new Csc(sin);
        return new TrigModule(sin, cos, cot, sec, csc);
    }

    private Function buildStage6LogModule() {
        Ln ln = new Ln(EPS);
        return new LogModule(ln, new Log2Stub(buildLog2Table()), new Log3Stub(buildLog3Table()));
    }

    private Function buildStage7LogModule() {
        Ln ln = new Ln(EPS);
        return new LogModule(ln, new Log2(ln), new Log3Stub(buildLog3Table()));
    }

    private Function buildStage8LogModule() {
        Ln ln = new Ln(EPS);
        return new LogModule(ln, new Log2(ln), new Log3(ln));
    }

    private Map<Double, Double> buildLog2Table() {
        Map<Double, Double> table = new HashMap<>();
        table.put(SystemTestUtils.X_LOG_SMALL, Math.log(SystemTestUtils.X_LOG_SMALL) / Math.log(2));
        table.put(SystemTestUtils.X_LOG_ONE, 0.0);
        table.put(SystemTestUtils.X_LOG_BIG, Math.log(SystemTestUtils.X_LOG_BIG) / Math.log(2));
        return table;
    }

    private Map<Double, Double> buildLog3Table() {
        Map<Double, Double> table = new HashMap<>();
        table.put(SystemTestUtils.X_LOG_SMALL, Math.log(SystemTestUtils.X_LOG_SMALL) / Math.log(3));
        table.put(SystemTestUtils.X_LOG_ONE, 0.0);
        table.put(SystemTestUtils.X_LOG_BIG, Math.log(SystemTestUtils.X_LOG_BIG) / Math.log(3));
        return table;
    }
}