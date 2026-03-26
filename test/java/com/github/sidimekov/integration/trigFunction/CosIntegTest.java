package java.com.github.sidimekov.integration.trigFunction;

import com.github.sidimekov.trigFunction.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CosIntegTest {

    @Mock
    private Sin sin;

    @Test
    void shouldCallSin() {
        when(sin.compute(anyDouble())).thenReturn(0.5);

        Cos cos = new Cos(sin);
        cos.compute(1);

        verify(sin, atLeastOnce()).compute(anyDouble());
    }

    @Test
    void shouldComputeCorrectly() {
        double x = 1;

        when(sin.compute(x)).thenReturn(Math.sin(x));

        Cos cos = new Cos(sin);
        double result = cos.compute(x);

        assertEquals(Math.cos(x), result, 1e-2);
    }
}
