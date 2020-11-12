package com.hogwarts;

/**
 * 描述: 计算器
 *
 * @Author defu
 * @Data 2020-11-04 20:49
 * @Version 1.0
 **/
public class Calculator {

    public static int sum = 0;

    public static int count(int x) {
        int i = sum;
        sum = i + x;
        return sum;
    }

    public synchronized static int synCount(int x) throws InterruptedException {
        int i = sum;
        Thread.sleep(1000);
        sum = i + x;
        return sum;
    }

    /**
     * 累加求和
     *
     * @param x
     * @return
     */
    public static int sum(int x) {
        return sum += x;
    }

    /**
     * 加法
     *
     * @param x
     * @param y
     * @return
     */
    public static int plus(int x, int y) {
        return x + y;
    }

    /**
     * 减法
     *
     * @param x
     * @param y
     * @return
     */
    public static int less(int x, int y) {
        return x - y;
    }

    /**
     * 乘法
     *
     * @param x
     * @param y
     * @return
     */
    public static int multiply(int x, int y) {
        return x * y;
    }

    /**
     * 除法
     *
     * @param x
     * @param y
     * @return
     */
    public static int except(int x, int y) {
        return x / y;
    }

}
