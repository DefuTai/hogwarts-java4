package com.hogwarts.wxwork.message;

import com.hogwarts.wxwork.BaseTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020-12-16 00:06
 * @Version 1.0
 **/
public class SendTest extends BaseTest {

    @Test
    void sendTest() {
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "\t\"touser\": \"@all\",\n" +
                        "\t\"msgtype\": \"text\",\n" +
                        "\t\"agentid\": 1000002,\n" +
                        "\t\"text\": {\n" +
                        "\t\t\"content\": \"想要提升测试技能，就到霍格沃兹测试学院报名吧！\\n报名直通车<a href=\\\"https://ke.qq.com/course/348893\\\">软件测试Java中高级测试开发「名企定向培养」班</a>\"\n" +
                        "\t},\n" +
                        "\t\"safe\": 0,\n" +
                        "\t\"enable_id_trans\": 0,\n" +
                        "\t\"enable_duplicate_check\": 0,\n" +
                        "\t\"duplicate_check_interval\": 1800\n" +
                        "}")
                .queryParam("access_token", ACCESS_TOKEN)
                .post("https://qyapi.weixin.qq.com/cgi-bin/message/send")
                .then()
                .log().all();
    }

}
