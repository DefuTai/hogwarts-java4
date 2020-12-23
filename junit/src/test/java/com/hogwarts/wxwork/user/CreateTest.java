package com.hogwarts.wxwork.user;

import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 描述：创建成员
 *
 * @Author defu
 * @Data 2020/12/23 1:17 下午
 * @Version 1.0
 **/
public class CreateTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/data/", numLinesToSkip = 1)
    void createUser() {
        Response response = null;
        assertAll("创建部门返回结果校验",
                () -> assertEquals("0", response.path("errcode").toString()),
                () -> assertEquals("created", response.path("errmsg").toString()),
                () -> assertNotNull(response.path("id"))
        );
    }

}
