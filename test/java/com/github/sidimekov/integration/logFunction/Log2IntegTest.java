package com.github.sidimekov.integration.logFunction;

import com.github.sidimekov.logFunction.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Log2IntegTest {

    private static final MathContext MC = new MathContext(9);

    @Mock
    private Ln mockLn;

    private Ln spyLn;

    @BeforeEach
    void setUp() {
        spyLn = spy(new Ln(1e-6));
    }

    @Test
    @DisplayName("Test 1: Call Ln via spy inside Log2")
    void shouldCallLnFunction() {
        Log2 log2 = new Log2(spyLn);
        log2.compute(2.718);
        verify(spyLn, atLeastOnce()).compute(anyDouble());
    }

    @ParameterizedTest(name = "mock.log2({0}) = {1}")
    @CsvFileSource(resources = "/Log2.csv", numLinesToSkip = 1, delimiter = ',')
    void shouldCallLog2Function(double x, String expectedStr) {
        double expected;
        if ("NaN".equals(expectedStr)) {
            expected = Double.NaN;
        } else {
            expected = Double.parseDouble(expectedStr);
        }

        lenient().when(mockLn.compute(x)).thenReturn(Math.log(x));
        lenient().when(mockLn.compute(2.0)).thenReturn(Math.log(2));

        Log2 log2 = new Log2(mockLn);
        double result = log2.compute(x);

        if (Double.isNaN(expected)) {
            assertTrue(Double.isNaN(result));
        } else {
            assertEquals(BigDecimal.valueOf(expected).round(MC),
                    BigDecimal.valueOf(result).round(MC));
        }
    }
}