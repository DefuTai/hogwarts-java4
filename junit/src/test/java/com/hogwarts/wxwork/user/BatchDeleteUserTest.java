package com.hogwarts.wxwork.user;

import com.hogwarts.workwechat.UserObject;
import com.hogwarts.wxwork.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 描述：批量删除成员
 *
 * @Author defu
 * @Date 2020/12/26 1:20 上午
 * @Version 1.0
 **/
public class BatchDeleteUserTest extends BaseTest {

    @BeforeEach
    @AfterEach
    void each() {
        clearUserTask();
    }

    @DisplayName("批量删除成员")
    @Test
    void getUser() {
        List<String> userIdList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String userId = "BATCHDELETE" + i + Thread.currentThread().getId() + System.currentTimeMillis();
            String userName = userId;
            //初始化一个用户
            UserObject.createUser(ACCESS_TOKEN, userId, userName);

            userIdList.add(userId);
        }

        //删除成员信息
        Response deleteResponse = UserObject.batchDeleteUser(ACCESS_TOKEN, userIdList.toArray(new String[userIdList.size()]));
        assertAll("批量删除成员接口返回校验",
                () -> assertEquals("0", deleteResponse.path("errcode").toString(), "接口返回errcode有误")
        );
    }

}
