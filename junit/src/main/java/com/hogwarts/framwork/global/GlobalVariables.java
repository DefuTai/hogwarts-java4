package com.hogwarts.framwork.global;

import java.util.HashMap;

/**
 * 描述：存储全局变量
 *
 * @Author defu
 * @Date 2021/1/4 12:28 上午
 * @Version 1.0
 **/
public class GlobalVariables {

    static private HashMap<String, String> globalVariables = new HashMap<>();

    public static HashMap<String, String> getGlobalVariables() {
        return globalVariables;
    }

    public static void setGlobalVariables(HashMap<String, String> globalVariables) {
        GlobalVariables.globalVariables = globalVariables;
    }
}
