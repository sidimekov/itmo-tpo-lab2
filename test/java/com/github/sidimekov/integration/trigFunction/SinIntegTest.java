package com.github.sidimekov.integration.trigFunction;

import com.github.sidimekov.trigFunction.Sin;
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
class SinIntegTest {

    private static final MathContext MC = new MathContext(9);

    @Mock
    private Sin mockSin;

    private Sin spySin;

    @BeforeEach
    void setUp() {
        spySin = spy(new Sin(1e-6));
    }

    @Test
    @DisplayName("Test 1: Call sine via spy")
    void shouldCallSinFunction() {
        spySin.compute(1.234);
        verify(spySin, atLeastOnce()).compute(anyDouble());
    }

    @ParameterizedTest(name = "mock.sin({0}) = {1}")
    @CsvFileSource(resources = "/Sin.csv", numLinesToSkip = 1, delimiter = ',')
    void shouldCallSinFunctionWithMock(double x, String expectedStr) {
        double expected = "NaN".equals(expectedStr) ? Double.NaN : Double.parseDouble(expectedStr);

        lenient().when(mockSin.compute(x)).thenReturn(Math.sin(x));

        double result = mockSin.compute(x);

        if (Double.isNaN(expected)) {
            assertTrue(Double.isNaN(result));
        } else {
            assertEquals(BigDecimal.valueOf(expected).round(MC),
                    BigDecimal.valueOf(result).round(MC));
        }
    }
}
