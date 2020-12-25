package com.hogwarts.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * 描述：按格式输出日期
 *
 * @Author defu
 * @Date 2020/12/26 2:10 上午
 * @Version 1.0
 **/
public class FormatDateUtil {

    /**
     * 输出距今天num的日期，格式为yyyy-mm-dd
     *
     * @param num 距今的天数
     * @return
     */
    public static String formatDate(int num) {
        Date date = DateUtils.addDays(new Date(), num);
        return DateFormatUtils.format(date, "yyyy-MM-dd");
    }

}
