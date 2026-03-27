package com.github.sidimekov.integration.trigFunction;

import com.github.sidimekov.trigFunction.*;
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
class CotIntegTest {

    private static final MathContext MC = new MathContext(9);

    @Mock
    private Sin mockSin;

    @Mock
    private Cos mockCos;

    private Sin spySin;
    private Cos spyCos;

    @BeforeEach
    void setUp() {
        spySin = spy(new Sin(1e-6));
        spyCos = spy(new Cos(spySin));
    }

    @Test
    @DisplayName("Test 1: Call both sine and cosine via spy")
    void shouldCallSinAndCosFunctions() {
        Cot cot = new Cot(spySin, spyCos);
        cot.compute(1.234);
        verify(spySin, atLeastOnce()).compute(anyDouble());
        verify(spyCos, atLeastOnce()).compute(anyDouble());
    }

    @ParameterizedTest(name = "mock.cot({0}) = {1}")
    @CsvFileSource(resources = "/Cot.csv", numLinesToSkip = 1, delimiter = ',')
    void shouldCallCotFunction(double x, String expectedStr) {
        double expected;
        if ("NaN".equals(expectedStr)) {
            expected = Double.NaN;
        } else {
            expected = Double.parseDouble(expectedStr);
        }

        lenient().when(mockSin.compute(x)).thenReturn(Math.sin(x));
        lenient().when(mockCos.compute(x)).thenReturn(Math.cos(x));

        Cot cot = new Cot(mockSin, mockCos);
        double result = cot.compute(x);

        if (Double.isNaN(expected)) {
            assertTrue(Double.isNaN(result));
        } else {
            assertEquals(BigDecimal.valueOf(expected).round(MC),
                    BigDecimal.valueOf(result).round(MC));
        }
    }
}