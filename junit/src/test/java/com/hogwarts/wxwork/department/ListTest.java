package com.hogwarts.wxwork.department;

import com.hogwarts.wxwork.BaseTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020/12/21 2:07 上午
 * @Version 1.0
 **/
public class ListTest extends BaseTest {

    @Test
    void list_test() {
        String id = "1";
        given()
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=" + ACCESS_TOKEN + "&id=" + id)
                .then()
                .log().all()
                .extract().response();
    }

}
