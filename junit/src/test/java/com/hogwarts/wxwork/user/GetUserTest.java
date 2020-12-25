package com.hogwarts.wxwork.user;

import com.hogwarts.workwechat.UserObject;
import com.hogwarts.wxwork.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 描述：读取成员
 *
 * @Author defu
 * @Data 2020/12/25 11:38 下午
 * @Version 1.0
 **/
public class GetUserTest extends BaseTest {

    @BeforeEach
    @AfterEach
    void each() {
        clearUserTask();
    }

    @DisplayName("读取成员")
    @Test
    void getUser() {
        String userId = "GET" + Thread.currentThread().getId() + System.currentTimeMillis();
        String userName = userId;
        //初始化一个用户
        UserObject.createUser(ACCESS_TOKEN, userId, userName);

        //读取成员信息
        Response getResponse = UserObject.getUser(ACCESS_TOKEN, userId);
        assertAll("读取成员信息校验",
                () -> assertEquals(userName, getResponse.path("name"), "成员名称校验失败"),
                () -> assertNotNull(getResponse.path("mobile"), "成员电话非空校验失败")
        );
    }

}
