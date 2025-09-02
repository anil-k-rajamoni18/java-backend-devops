package com.learn.springbootdemo.util;

import com.learn.springbootdemo.utility.Calculator;
import com.learn.springbootdemo.utility.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UtilsTest {

    static Calculator calci;
    static StringUtils stringUtils;

    @BeforeAll
    static void setUp() {
        calci = new Calculator();
        stringUtils = new StringUtils();
        System.out.println("Inside setUp method using @BeforeAll");
    }

    @Test
    void testAddition() {
        assertEquals(5, calci.add(2, 3));
    }

    @Test
    void testDivision() {
        assertEquals(2, calci.divide(10,5));
    }

    @Test
    void testPalindrome(){
        assertTrue(stringUtils.isPalindrome("radar"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"radar", "level", "madam"})
    void testPalindrome(String word) {
        assertTrue(stringUtils.isPalindrome(word));
    }
}
