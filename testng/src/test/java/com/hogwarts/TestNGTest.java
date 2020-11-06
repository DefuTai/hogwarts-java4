package com.hogwarts;

import org.testng.Assert;
import org.testng.annotations.*;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020-11-05 23:11
 * @Version 1.0
 **/
public class TestNGTest {

    @BeforeClass
    public void before(){
        System.out.println("BeforeClass");
    }

    @BeforeGroups
    public void beforeGroups(){
        System.out.println("BeforeGroups");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("BeforeMethod");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("BeforeSuite");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("BeforeTest");
    }

    @Test
    public void test_testNg_plus() {
        Assert.assertEquals(2, Calculator.plus(1, 1), "TestNG ==> 求和计算有误！");
    }

    @Test
    public void test_testNg_less() {
        Assert.assertEquals(0, Calculator.less(1, 1), "TestNG ==> 求差计算有误！");
    }

    @Test
    public void test_testNg_multiply() {
        Assert.assertEquals(1, Calculator.multiply(1, 1), "TestNG ==> 求积计算有误！");
    }

    @Test
    public void test_testNg_except() {
        Assert.assertEquals(1, Calculator.except(1, 1), "TestNG ==> 求商计算有误！");
    }

}
