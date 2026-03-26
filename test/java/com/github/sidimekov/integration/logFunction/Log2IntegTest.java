package java.com.github.sidimekov.integration.logFunction;

import com.github.sidimekov.logFunction.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Log2IntegTest {

    @Mock Ln ln;

    @Test
    void shouldCallLn() {
        when(ln.compute(anyDouble())).thenReturn(1.0);

        Log2 log2 = new Log2(ln);
        log2.compute(2);

        verify(ln, atLeast(2)).compute(anyDouble());
    }

    @Test
    void shouldComputeCorrectly() {
        double x = 2;

        when(ln.compute(x)).thenReturn(Math.log(x));
        when(ln.compute(2)).thenReturn(Math.log(2));

        Log2 log2 = new Log2(ln);
        double result = log2.compute(x);

        assertEquals(Math.log(x) / Math.log(2), result, 1e-3);
    }
}
