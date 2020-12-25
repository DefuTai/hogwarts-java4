package com.hogwarts.wxwork.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.hogwarts.workwechat.MessageObject;
import com.hogwarts.wxwork.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 描述：发送应用消息
 *
 * @Author defu
 * @Data 2020-12-16 00:06
 * @Version 1.0
 **/
public class SendMessageTest extends BaseTest {

    @DisplayName("发送应用消息")
    @Test
    void sendMessage() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference<HashMap<String, HashMap<String, Object>>> typeReference = new TypeReference<HashMap<String, HashMap<String, Object>>>() {
        };
        HashMap<String, HashMap<String, Object>> paramYaml = null;
        try {
            paramYaml = mapper.readValue(SendMessageTest.class.getResourceAsStream("/data/message/SendMessageTestData.yaml"), typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Response response = MessageObject.sendMessage(ACCESS_TOKEN, new ObjectMapper().writeValueAsString(paramYaml));
        assertAll("发送应用消息结果校验",
                () -> assertEquals("", response.path("errcode").toString())
        );
    }

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
