package com.github.sidimekov.integration.trigFunction;

import com.github.sidimekov.trigFunction.Sec;
import com.github.sidimekov.trigFunction.Cos;
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
class SecIntegTest {

    private static final MathContext MC = new MathContext(9);

    @Mock
    private Cos mockCos;

    private Cos spyCos;
    private Sec spySec;

    @BeforeEach
    void setUp() {
        spyCos = spy(new Cos(new com.github.sidimekov.trigFunction.Sin(1e-6)));
        spySec = spy(new Sec(spyCos));
    }

    @Test
    @DisplayName("Test 1: Call sec via spy")
    void shouldCallSecFunction() {
        spySec.compute(1.234);
        verify(spySec, atLeastOnce()).compute(anyDouble());
        verify(spyCos, atLeastOnce()).compute(anyDouble());
    }

    @ParameterizedTest(name = "mock.sec({0}) = {1}")
    @CsvFileSource(resources = "/Sec.csv", numLinesToSkip = 1, delimiter = ',')
    void shouldCallSecFunctionWithMock(double x, String expectedStr) {
        double expected = "NaN".equals(expectedStr) ? Double.NaN : Double.parseDouble(expectedStr);

        lenient().when(mockCos.compute(x)).thenReturn(Math.cos(x));

        Sec sec = new Sec(mockCos);
        double result = sec.compute(x);

        if (Double.isNaN(expected)) {
            assertTrue(Double.isNaN(result));
        } else {
            assertEquals(BigDecimal.valueOf(expected).round(MC),
                    BigDecimal.valueOf(result).round(MC));
        }
    }
}