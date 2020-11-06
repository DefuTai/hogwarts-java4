package com.hogwarts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 描述：计算器测试
 *
 * @Author defu
 * @Data 2020-11-04 21:55
 * @Version 1.0
 **/
public class JUnit5Test {

    @Test
    public void test_junit5_plus() {
        assertEquals(2, Calculator.plus(1, 1), "JUnit5 ==> 求和计算有误！");
    }

    @Test
    public void test_junit5_less() {
        assertEquals(0, Calculator.less(1, 1), "JUnit5 ==> 求差计算有误！");
    }

    @Test
    public void test_junit5_multiply() {
        assertEquals(1, Calculator.multiply(1, 1), "JUnit5 ==> 求积计算有误！");
    }

    @Test
    public void test_junit5_except() {
        assertEquals(1, Calculator.except(1, 1), "JUnit5 ==> 求商计算有误！");
    }

    @Test
    void palindromes() {
        assertAll("加减乘除依次运算:",
                () -> assertEquals(2, Calculator.plus(1, 1), "求和错误"),
                () -> assertEquals(0, Calculator.less(1, 1)),
                () -> assertEquals(1, Calculator.multiply(1, 1)),
                () -> assertEquals(1, Calculator.except(1, 1))
        );
    }

}
