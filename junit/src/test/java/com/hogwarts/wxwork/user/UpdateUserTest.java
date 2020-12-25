package com.hogwarts.wxwork.user;

import com.hogwarts.dto.UserDTO;
import com.hogwarts.workwechat.UserObject;
import com.hogwarts.wxwork.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 描述：更新成员
 *
 * @Author defu
 * @Data 2020/12/26 12:06 上午
 * @Version 1.0
 **/
public class UpdateUserTest extends BaseTest {

    private static String name;
    private static String address;
    private static String externalPosition;

    @BeforeEach
    @AfterEach
    void each() {
        clearUserTask();
    }

    @DisplayName("更新成员")
    @ParameterizedTest
    @CsvFileSource(resources = "/data/user/UpdateUserTestData.csv", numLinesToSkip = 1)
    void updateUser(String id, String description, String name, String address, String externalPosition, String errCode) {
        String userId = "CREATE" + Thread.currentThread().getId() + System.currentTimeMillis();
        String userName = userId;
        //初始化一个用户
        UserObject.createUser(ACCESS_TOKEN, userId, userName);

        this.name = StringUtils.isBlank(name) ? null : name + Thread.currentThread().getId() + System.currentTimeMillis();
        this.address = address;
        this.externalPosition = externalPosition;

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userId);
        userDTO.setName(this.name);
        userDTO.setAddress(this.address);
        userDTO.setExternalPosition(this.externalPosition);
        //更新成员
        Response updateResponse = UserObject.updateUser(ACCESS_TOKEN, userDTO);
        assertAll(description + "-接口返回结果校验",
                () -> assertEquals(errCode, updateResponse.path("errcode").toString())
        );

        if (!id.equals("3")) {
            //通过获取成员接口查询修改后的成员信息，进行数据校验
            Response getResponse = UserObject.getUser(ACCESS_TOKEN, userId);
            assertAll(description + "-修改数据校验",
                    () -> assertEquals(this.name, getResponse.path("name")),
                    () -> assertEquals(this.address, getResponse.path("address")),
                    () -> assertEquals(this.externalPosition, getResponse.path("external_position"))
            );
        }
    }

}
