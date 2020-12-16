package com.hogwarts.wxwork;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020-12-15 23:55
 * @Version 1.0
 **/
public class BaseTest {

    public static String ACCESS_TOKEN;

    private static final String CORP_ID = "ww98102567c218bd46";
    private static final String CORP_SECRET = "r30ubf7HcirzW8U-Tz3ymQYEHh99gFQ8XIw7NN1EnTQ";

    @BeforeAll
    public static void beforeAll() {
        ACCESS_TOKEN = given()
                .params("corpid", CORP_ID, "corpsecret", CORP_SECRET)
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .extract().response().path("access_token");
    }

    @Test
    void test() {
        System.out.println(ACCESS_TOKEN);
    }

}
