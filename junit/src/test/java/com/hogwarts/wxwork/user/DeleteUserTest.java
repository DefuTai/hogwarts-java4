package com.hogwarts.wxwork.user;

import com.hogwarts.workwechat.UserObject;
import com.hogwarts.wxwork.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 描述：删除成员
 *
 * @Author defu
 * @Data 2020/12/26 12:59 上午
 * @Version 1.0
 **/
public class DeleteUserTest extends BaseTest {

    @BeforeEach
    @AfterEach
    void each() {
        clearUserTask();
    }

    @DisplayName("删除成员")
    @Test
    void getUser() {
        String userId = "DELETE" + Thread.currentThread().getId() + System.currentTimeMillis();
        String userName = userId;
        //初始化一个用户
        UserObject.createUser(ACCESS_TOKEN, userId, userName);

        //删除成员信息
        Response deleteResponse = UserObject.deleteUser(ACCESS_TOKEN, userId);
        assertAll("删除成员接口返回校验",
                () -> assertEquals("0", deleteResponse.path("errcode").toString(), "接口返回errcode有误")
        );

        //通过获取成员接口查询被删除成员信息，若接口返回errcode为60111，即校验通过
        Response getResponse = UserObject.getUser(ACCESS_TOKEN, userId);
        assertAll("删除成员",
                () -> assertEquals("60111", getResponse.path("errcode").toString())
        );
    }

}
