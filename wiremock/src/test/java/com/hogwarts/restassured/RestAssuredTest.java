package com.hogwarts.restassured;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020-12-18 23:22
 * @Version 1.0
 **/
public class RestAssuredTest {

    @Test
    void test() {
        given()
                .when()
                .get("http://localhost:8000/lotto.json")
                .then().body("lotto.lottoId", equalTo(5));
    }

}
