package com.hogwarts;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 描述：通过Allure优化测试报告
 * 注：先执行测试用例，生成allure-results目录后，再到子工程目录下执行"allure serve allure-results"命令，才能正常加载报告数据
 *
 * @Author defu
 * @Data 2020-11-14 13:18
 * @Version 1.0
 **/
public class JUnit5_AllureTest {

    @Test
    @Description("Description 加法")
    @DisplayName("DisplayName 加法")
    @Story("Story 加法")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("https://www.baidu.com")
    @Link(name = "Link 加法", type = "百度", url = "https://www.baidu.com")
    public void test_junit5_plus() {
        assertEquals(2, Calculator.plus(1, 1), "JUnit5 ==> 求和计算有误！");
        System.out.println("加法被执行！");
    }

    @Test
    @DisplayName("减法")
    public void test_junit5_less() {
        assertEquals(0, Calculator.less(1, 1), "JUnit5 ==> 求差计算有误！");
        System.out.println("减法被执行！");
    }

    @Test
    @DisplayName("乘法")
    public void test_junit5_multiply() {
        assertEquals(1, Calculator.multiply(1, 1), "JUnit5 ==> 求积计算有误！");
        System.out.println("乘法被执行！");
    }

    @Test
    @DisplayName("除法")
    public void test_junit5_except() {
        assertEquals(1, Calculator.except(1, 1), "JUnit5 ==> 求商计算有误！");
        System.out.println("除法被执行！");
    }

    @Test
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

}
