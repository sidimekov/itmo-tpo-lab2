package com.github.sidimekov.integration.trigFunction;

import com.github.sidimekov.trigFunction.Sin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SinIntegTest {

    @Spy
    private Sin sin = new Sin(1e-6);

    @Test
    void shouldComputeCorrectly() {
        double x = -1;

        double result = sin.compute(x);

        verify(sin, atLeastOnce()).compute(x);
        assertEquals(Math.sin(x), result, 1e-3);
    }
}
