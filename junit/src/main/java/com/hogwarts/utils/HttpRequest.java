package com.hogwarts.utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020/12/21 3:38 上午
 * @Version 1.0
 **/
public class HttpRequest {

    /**
     * @param url         请求地址
     * @param paramMap    参数
     * @param contentType 参数类型
     * @return
     */
    public static Response post(String url, Map<String, Object> paramMap, ContentType contentType) {
        return given().log().all()
                .contentType(contentType)
                .body(Map2JsonString.map2JsonString(paramMap))
                .post(url)
                .then()
                .log().body()
                .extract()
                .response();
    }

    /**
     * @param url   请求地址
     * @param param 参数
     * @return
     */
    public static Response post(String url, String param) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(param)
                .post(url)
                .then()
                .log()
                .body()
                .extract()
                .response();
    }

    /**
     * @param url 请求地址
     * @return
     */
    public static Response get(String url) {
        return given().log().all()
                .contentType("application/json")
                .get(url)
                .then()
                .log().body()
                .extract().response();
    }

}
