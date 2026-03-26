package trigFunction.integration;

import com.github.sidimekov.trigFunction.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecIntegTest {

    @Mock Cos cos;

    @Test
    void shouldCallCos() {
        when(cos.compute(anyDouble())).thenReturn(1.0);

        Sec sec = new Sec(cos);
        sec.compute(1);

        verify(cos, atLeastOnce()).compute(anyDouble());
    }

    @Test
    void shouldComputeCorrectly() {
        double x = 1;

        when(cos.compute(x)).thenReturn(Math.cos(x));

        Sec sec = new Sec(cos);
        double result = sec.compute(x);

        assertEquals(1 / Math.cos(x), result, 1e-3);
    }

    @Test
    void shouldReturnNaN() {
        when(cos.compute(anyDouble())).thenReturn(0.0);

        assertTrue(Double.isNaN(new Sec(cos).compute(1)));
    }
}
