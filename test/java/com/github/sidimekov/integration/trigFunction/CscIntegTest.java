package com.github.sidimekov.integration.trigFunction;

import com.github.sidimekov.trigFunction.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CscIntegTest {

    @Mock Sin sin;

    @Test
    void shouldCallSin() {
        when(sin.compute(anyDouble())).thenReturn(1.0);

        Csc csc = new Csc(sin);
        csc.compute(1);

        verify(sin, atLeastOnce()).compute(anyDouble());
    }

    @Test
    void shouldComputeCorrectly() {
        double x = 1;

        when(sin.compute(x)).thenReturn(Math.sin(x));

        Csc csc = new Csc(sin);
        double result = csc.compute(x);

        assertEquals(1 / Math.sin(x), result, 1e-3);
    }

    @Test
    void shouldReturnNaN() {
        when(sin.compute(anyDouble())).thenReturn(0.0);

        assertTrue(Double.isNaN(new Csc(sin).compute(1)));
    }
}
