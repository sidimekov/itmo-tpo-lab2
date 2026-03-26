package com.github.sidimekov.integration.logFunction;

import com.github.sidimekov.logFunction.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Log3IntegTest {

    @Mock Ln ln;

    @Test
    void shouldCallLn() {
        when(ln.compute(anyDouble())).thenReturn(1.0);

        Log3 log3 = new Log3(ln);
        log3.compute(3);

        verify(ln, atLeast(2)).compute(anyDouble());
    }
}
