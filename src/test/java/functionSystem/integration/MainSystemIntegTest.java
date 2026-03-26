package functionSystem.integration;

import com.github.sidimekov.functionSystem.MainSystem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainSystemIntegTest {

    @Spy
    private MainSystem system = new MainSystem(1e-6);

    @Test
    void shouldCallCompute() {
        system.compute(1);
        verify(system, atLeastOnce()).compute(anyDouble());
    }

    @Test
    void shouldWorkBothBranches() {
        assertFalse(Double.isNaN(system.compute(-1)));
        assertFalse(Double.isNaN(system.compute(2)));
    }
}
