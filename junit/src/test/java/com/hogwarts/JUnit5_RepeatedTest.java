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
        System.out.println("执行次数：" + Calculator.sum(1));
    }

}
