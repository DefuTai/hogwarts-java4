package com.hogwarts.wxwork.user;

import com.hogwarts.dto.UserDTO;
import com.hogwarts.utils.RandomUtil;
import com.hogwarts.workwechat.UserObject;
import com.hogwarts.wxwork.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 描述：创建成员
 *
 * @Author defu
 * @Date 2020/12/23 1:17 下午
 * @Version 1.0
 **/
public class CreateUserTest extends BaseTest {

    @BeforeEach
    @AfterEach
    void each() {
        clearUserTask();
    }

    @DisplayName("创建成员")
    @ParameterizedTest
    @CsvFileSource(resources = "/data/user/CreateUserTestData.csv", numLinesToSkip = 1)
    void createUser(String id, String description, String name, String userId, String department, String mobile) {
        UserDTO user = new UserDTO();
        user.setUserId(userId + Thread.currentThread().getId() + System.currentTimeMillis());
        user.setName(name + Thread.currentThread().getId() + System.currentTimeMillis());
        user.setMobile(mobile + RandomUtil.nextInt(8));
        user.setDepartment(new Integer[]{Integer.valueOf(department)});

        Response response = UserObject.createUser(ACCESS_TOKEN, user);
        assertAll(description,
                () -> assertEquals("0", response.path("errcode").toString()),
                () -> assertEquals("created", response.path("errmsg").toString())
        );
    }

}
