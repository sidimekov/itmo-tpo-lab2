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
class CosIntegTest {

    private static final MathContext MC = new MathContext(9);

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
    @DisplayName("Test 1: Call cosine via spy")
    void shouldCallCosFunction() {
        spyCos.compute(1.234);
        verify(spyCos, atLeastOnce()).compute(anyDouble());
        verify(spySin, atLeastOnce()).compute(anyDouble());
    }

    @ParameterizedTest(name = "mock.cos({0}) = {1}")
    @CsvFileSource(resources = "/Cos.csv", numLinesToSkip = 1, delimiter = ',')
    void shouldCallCosFunctionWithMock(double x, String expectedStr) {
        double expected = "NaN".equals(expectedStr) ? Double.NaN : Double.parseDouble(expectedStr);

        lenient().when(mockCos.compute(x)).thenReturn(Math.cos(x));

        double result = mockCos.compute(x);

        if (Double.isNaN(expected)) {
            assertTrue(Double.isNaN(result));
        } else {
            assertEquals(BigDecimal.valueOf(expected).round(MC),
                    BigDecimal.valueOf(result).round(MC));
        }
    }
}