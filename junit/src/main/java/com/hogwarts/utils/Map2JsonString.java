package com.hogwarts.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * 描述：Map类型转换成JSON字符串
 *
 * @Author defu
 * @Data 2020/12/21 3:04 上午
 * @Version 1.0
 **/
public class Map2JsonString {

    /**
     * 将map转换成json字符串
     *
     * @param map
     * @return
     */
    public static String map2JsonString(Map<String, Object> map) {
        String mapper = null;
        try {
            mapper = new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return mapper;
    }

}
