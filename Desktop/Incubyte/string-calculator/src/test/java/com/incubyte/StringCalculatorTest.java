package com.incubyte;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {
    private StringCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new StringCalculator();
    }

    @Test
    void shouldReturnZeroForEmptyString() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    void shouldReturnNumberForSingleNumber() {
        assertEquals(1, calculator.add("1"));
    }

    @Test
    void shouldReturnSumForTwoNumbers() {
        assertEquals(3, calculator.add("1,2"));
    }

    @Test
    void shouldReturnSumForMultipleNumbers() {
        assertEquals(6, calculator.add("1,2,3"));
    }

    @Test
    void shouldHandleNewLinesBetweenNumbers() {
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    void shouldSupportCustomDelimiter() {
        assertEquals(3, calculator.add("//;\n1;2"));
    }

    @Test
    void shouldThrowExceptionForNegativeNumber() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.add("-1,2")
        );
        assertEquals("negative numbers not allowed [-1]", exception.getMessage());
    }

    @Test
    void shouldShowAllNegativeNumbersInExceptionMessage() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.add("-1,-2,3")
        );
        assertEquals("negative numbers not allowed [-1, -2]", exception.getMessage());
    }

    @Test
    void shouldSupportMultipleDelimiters() {
        assertEquals(6, calculator.add("//[;][#]\n1;2#3"));
    }

    @Test
    void testNegativeNumbers() {
    StringCalculator calculator = new StringCalculator();
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calculator.add("-1"));
    assertEquals("negative numbers not allowed [-1]", exception.getMessage());
}

}
