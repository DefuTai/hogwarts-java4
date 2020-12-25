package com.hogwarts.utils;

/**
 * 描述：获取随机数字字符串
 *
 * @Author defu
 * @Data 2020/12/25 8:55 上午
 * @Version 1.0
 **/
public class RandomUtil {

    /**
     * 随机获取指定位数的数字字符串
     *
     * @param len 指定获取数字字符串长度
     * @return
     */
    public static synchronized String nextInt(int len) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        sb.append(Thread.currentThread().getId());

        String var = sb.toString();
        return var.substring(var.length() - len, var.length());
    }

}
