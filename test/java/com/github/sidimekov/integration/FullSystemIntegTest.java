package com.github.sidimekov.integration;

import com.github.sidimekov.functionSystem.MainSystem;
import com.github.sidimekov.util.SystemTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BottomUpFullSystemIntegTest {
    private static final double EPS = 1e-8;
    private static final double DELTA = 1e-5;

    @Test
    @DisplayName("stage9_full_system: Полностью реальная система без заглушников")
    void shouldWorkWithFullyRealSystem() {
        MainSystem system = new MainSystem(EPS);

        SystemTestUtils.assertCommonSystemBehavior(system, DELTA);
    }
}