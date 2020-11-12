package com.hogwarts;

import org.junit.*;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020-11-05 23:11
 * @Version 1.0
 **/
public class JUnit4Test {

    @BeforeClass
    public static void beforeClass(){
        System.out.println("BeforeClass => 使用了该注解的方法应该在当前类中所有使用了@Test注解方法前执行");
    }

    @Before
    public void before(){
        System.out.println("Before => 使用了该注解的方法应该在当前类中每个使用了@Test注解的方法前执行");
    }

    @Test
    public void test_junit4_plus() {
        Assert.assertEquals("JUnit4 ==> 求和计算有误！", 2, Calculator.plus(1, 1));
    }

    @Test
    public void test_junit4_less() {
        Assert.assertEquals("JUnit4 ==> 求差计算有误！", 0, Calculator.less(1, 1));
    }

    @Test
    public void test_junit4_multiply() {
        Assert.assertEquals("JUnit4 ==> 求积计算有误！", 1, Calculator.multiply(1, 1));
    }

    @Test
    public void test_junit4_except() {
        Assert.assertEquals("JUnit4 ==> 求商计算有误！", 1, Calculator.except(1, 1));
    }

    @After
    public void after(){
        System.out.println("After => 使用了该注解的方法应该在当前类中每个使用了@Test注解的方法后执行");
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("AfterClass => 使用了该注解的方法应该在当前类中所有使用了@Test注解的方法后执行");
    }

}
