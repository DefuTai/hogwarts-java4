package com.hogwarts;

import org.testng.annotations.Test;

/**
 * 描述：并发测试
 *
 * @Author defu
 * @Data 2020-11-13 00:21
 * @Version 1.0
 **/
public class TestNGConcurrentTest {

    @Test(threadPoolSize = 2, invocationCount = 10, timeOut = 5000)
    public void test_concurrent_1() {
        Calculator.sum(1);
        System.out.println(Thread.currentThread().getName() + " 执行结果为：" + Calculator.sum(1));
    }

    @Test(threadPoolSize = 2, invocationCount = 10, timeOut = 5000)
    public void test_concurrent_2() {
        System.out.println(Thread.currentThread().getName() + " 执行结果为：" + Calculator.synSum(1));
    }

}
