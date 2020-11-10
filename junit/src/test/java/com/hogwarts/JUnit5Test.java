package com.hogwarts;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 描述：计算器测试
 *
 * @Author defu
 * @Data 2020-11-04 21:55
 * @Version 1.0
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JUnit5Test {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("BeforeAll >>> 执行！");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("BeforeEach >> 执行！");
    }

    @Test
    @Order(1)
    @DisplayName("加法")
    public void test_junit5_plus() {
        assertEquals(2, Calculator.plus(1, 1), "JUnit5 ==> 求和计算有误！");
        System.out.println("加法被执行！");
    }

    @Test
    @Order(2)
    @DisplayName("减法")
    public void test_junit5_less() {
        assertEquals(0, Calculator.less(1, 1), "JUnit5 ==> 求差计算有误！");
        System.out.println("减法被执行！");
    }

    @Test
    @Order(3)
    @DisplayName("乘法")
    public void test_junit5_multiply() {
        assertEquals(1, Calculator.multiply(1, 1), "JUnit5 ==> 求积计算有误！");
        System.out.println("乘法被执行！");
    }

    @Test
    @Order(4)
    @DisplayName("除法")
    public void test_junit5_except() {
        assertEquals(1, Calculator.except(1, 1), "JUnit5 ==> 求商计算有误！");
        System.out.println("除法被执行！");
    }

    @Test
    @Order(5)
    @DisplayName("Junit5 软断言")
    void palindromes() {
        assertAll("加减乘除依次运算:",
                () -> assertEquals(2, Calculator.plus(1, 1), "求和错误"),
                () -> assertEquals(0, Calculator.less(1, 1)),
                () -> assertEquals(1, Calculator.multiply(1, 1)),
                () -> assertEquals(1, Calculator.except(1, 1))
        );
        System.out.println("软断言被执行！");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("AfterEach << 执行！");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("AfterAll <<< 执行！");
    }

}
