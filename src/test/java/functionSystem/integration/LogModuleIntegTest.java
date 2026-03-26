package functionSystem.integration;

import com.github.sidimekov.functionSystem.LogModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogModuleIntegTest {

    @Spy
    private LogModule log = new LogModule(1e-6);

    @Test
    void shouldCallCompute() {
        log.compute(2);

        verify(log, atLeastOnce()).compute(anyDouble());
    }

    @Test
    void shouldReturnNaN() {
        assertTrue(Double.isNaN(log.compute(-1)));
    }
}
