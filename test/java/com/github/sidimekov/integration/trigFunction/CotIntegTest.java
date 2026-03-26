package java.com.github.sidimekov.integration.trigFunction;

import com.github.sidimekov.trigFunction.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CotIntegTest {

    @Mock Sin sin;
    @Mock Cos cos;

    @Test
    void shouldCallDependencies() {
        when(sin.compute(anyDouble())).thenReturn(1.0);
        when(cos.compute(anyDouble())).thenReturn(2.0);

        Cot cot = new Cot(sin, cos);
        cot.compute(1);

        verify(sin, atLeastOnce()).compute(anyDouble());
        verify(cos, atLeastOnce()).compute(anyDouble());
    }

    @Test
    void shouldComputeCorrectly() {
        double x = 1;

        when(sin.compute(x)).thenReturn(Math.sin(x));
        when(cos.compute(x)).thenReturn(Math.cos(x));

        Cot cot = new Cot(sin, cos);
        double result = cot.compute(x);

        assertEquals(Math.cos(x) / Math.sin(x), result, 1e-3);
    }

    @Test
    void shouldReturnNaN() {
        when(sin.compute(anyDouble())).thenReturn(0.0);

        Cot cot = new Cot(sin, cos);
        assertTrue(Double.isNaN(cot.compute(1)));
    }
}