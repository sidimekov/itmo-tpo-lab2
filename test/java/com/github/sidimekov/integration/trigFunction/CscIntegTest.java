package com.github.sidimekov.integration.trigFunction;

import com.github.sidimekov.trigFunction.Csc;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CscIntegTest {

    private static final MathContext MC = new MathContext(9);

    @Mock
    private Sin mockSin;

    private Sin spySin;
    private Csc spyCsc;

    @BeforeEach
    void setUp() {
        spySin = spy(new Sin(1e-6));
        spyCsc = spy(new Csc(spySin));
    }

    @Test
    @DisplayName("Test 1: Call csc via spy")
    void shouldCallCscFunction() {
        spyCsc.compute(1.234);
        verify(spyCsc, atLeastOnce()).compute(anyDouble());
        verify(spySin, atLeastOnce()).compute(anyDouble());
    }

    @ParameterizedTest(name = "mock.csc({0}) = {1}")
    @CsvFileSource(resources = "/Csc.csv", numLinesToSkip = 1, delimiter = ',')
    void shouldCallCscFunctionWithMock(double x, String expectedStr) {
        double expected = "NaN".equals(expectedStr) ? Double.NaN : Double.parseDouble(expectedStr);

        lenient().when(mockSin.compute(x)).thenReturn(Math.sin(x));

        Csc csc = new Csc(mockSin);
        double result = csc.compute(x);

        if (Double.isNaN(expected)) {
            assertTrue(Double.isNaN(result));
        } else {
            assertEquals(BigDecimal.valueOf(expected).round(MC),
                    BigDecimal.valueOf(result).round(MC));
        }
    }
}