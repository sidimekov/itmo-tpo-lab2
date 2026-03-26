package logFunction.integration;

import com.github.sidimekov.logFunction.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LnIntegTest {

    @Spy
    private Ln ln = new Ln(1e-6);

    @Test
    void shouldComputeCorrectly() {
        double x = 2;

        double result = ln.compute(x);

        verify(ln, atLeastOnce()).compute(x);
        assertEquals(Math.log(x), result, 1e-3);
    }

    @Test
    void shouldReturnNaN() {
        assertTrue(Double.isNaN(ln.compute(-1)));
    }
}
