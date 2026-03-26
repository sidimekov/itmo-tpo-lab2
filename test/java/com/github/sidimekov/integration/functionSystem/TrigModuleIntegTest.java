package com.github.sidimekov.integration.functionSystem;

import com.github.sidimekov.functionSystem.TrigModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrigModuleIntegTest {

    @Spy
    private TrigModule trig = new TrigModule(1e-6);

    @Test
    void shouldCallCompute() {
        trig.compute(-1);

        verify(trig, atLeastOnce()).compute(anyDouble());
    }

    @Test
    void shouldReturnNaN() {
        assertTrue(Double.isNaN(trig.compute(0)));
    }
}
