package com.hogwarts;

import org.junit.jupiter.api.RepeatedTest;

/**
 * 描述：重复此测试方法
 *
 * @Author defu
 * @Data 2020-11-10 09:25
 * @Version 1.0
 **/
public class JUnit5_RepeatedTest {

    @RepeatedTest(10)
    public void repeatedTest() {
        System.out.println(Thread.currentThread().getName() + " 执行结果：" + Calculator.sum(1));
    }

    @RepeatedTest(10)
    public void countTest() {
        System.out.println("执行次数：" + Calculator.count(1));
    }

    @RepeatedTest(10)
    public void synCountTest() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " 执行结果：" + Calculator.synCount(1));
    }

}
