package com.github.sidimekov.integration.logFunction;

import com.github.sidimekov.logFunction.Ln;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LnIntegTest {

    private static final MathContext MC = new MathContext(8);

    @Mock
    private Ln mockLn;

    private Ln spyLn;

    @BeforeEach
    void setUp() {
        spyLn = spy(new Ln(1e-6));
    }

    @Test
    @DisplayName("Test 1: Call ln via spy")
    void shouldCallLnFunction() {
        spyLn.compute(1.234);
        verify(spyLn, atLeastOnce()).compute(anyDouble());
    }

    @ParameterizedTest(name = "mock.ln({0}) = {1}")
    @CsvFileSource(resources = "/Ln.csv", numLinesToSkip = 1, delimiter = ',')
    void shouldCallLnFunctionWithMock(double x, String expectedStr) {
        double expected = "NaN".equals(expectedStr) ? Double.NaN : Double.parseDouble(expectedStr);

        lenient().when(mockLn.compute(x)).thenReturn(Math.log(x));

        double result = mockLn.compute(x);

        if (Double.isNaN(expected)) {
            assertTrue(Double.isNaN(result));
        } else {
            assertEquals(BigDecimal.valueOf(expected).round(MC),
                    BigDecimal.valueOf(result).round(MC));
        }
    }
}