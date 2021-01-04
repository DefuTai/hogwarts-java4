package com.hogwarts.utils;

import java.util.Random;

/**
 * 描述：
 *
 * @Author defu
 * @Date 2021/1/3 11:29 下午
 * @Version 1.0
 **/
public class FakerUtils {

    //汉字的Unicode范围
    private final static int delta = 0x9fa5 - 0x4e00 + 1;
    //手机号前缀
    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,188,185,181".split(",");

    /**
     * 获取一个指定长度的随机数
     *
     * @param length 获取的随机数的长度
     * @return 随机数
     */
    public static int getRandomInt(int length) {
        length = length - 1;
        int randomint = (int) ((Math.random() * 9 + 1) * Math.pow(10, (double) length));
        return randomint;
    }

    /**
     * 获取一个指定范围的随机数
     *
     * @param min 获取的随机数左边界
     * @param max 获取的随机数右边界
     * @return 随机数
     */
    public static int getRandomInt(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;

    }

    /**
     * 获取时间戳
     *
     * @return
     */
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 获取随机数
     *
     * @param start
     * @param end
     * @return
     */
    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    /**
     * 14位订单号生成器
     *
     * @return
     */
    public static String orderNo() {
        String cardNo = "123456";
        for (int i = 0; i < 8; i++) {
            cardNo += getNum(0, 9);
        }
        return cardNo;
    }

    /**
     * 电话号码生成器
     *
     * @return
     */
    public static String getTel() {
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + third;
    }

    /**
     * 获取随机汉字
     *
     * @param num
     * @return
     */
    public static char getRandomHan(int num) {
        Random ran = new Random();
        return (char) (0x4e00 + ran.nextInt(delta));
    }

}
