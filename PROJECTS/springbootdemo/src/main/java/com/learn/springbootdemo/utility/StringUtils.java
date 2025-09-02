package com.learn.springbootdemo.utility;

public class StringUtils {
    public boolean isPalindrome(String str) {
        System.out.println("checking word: " + str);
        return new StringBuilder(str).reverse().toString().equals(str);
    }
}
