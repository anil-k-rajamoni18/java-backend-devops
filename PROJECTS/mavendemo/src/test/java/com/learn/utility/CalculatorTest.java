package com.learn.utility;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CalculatorTest {

    Calculator calc;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
    }

    @Test
    void testAddition() {
        assertEquals(5, calc.add(2, 3), "2 + 3 should be 5");
    }

    @Test
    void testMultiplication() {
        assertEquals(6, calc.multiply(2, 3), "2 * 3 should be 6");
    }

    @Test
    @Disabled("Feature not ready yet")
    void testDivision() {
        fail("This test is not implemented yet");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test completed");
    }
}
