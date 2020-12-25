package com.hogwarts.wxwork.department;

import com.hogwarts.workwechat.DepartmentObject;
import com.hogwarts.wxwork.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 描述：创建部门
 *
 * @Author defu
 * @Date 2020/12/21 1:06 上午
 * @Version 1.0
 **/
public class CreateTest extends BaseTest {

    @BeforeEach
    @AfterEach
    void each() {
        clearDepartmentTask();
    }

    @DisplayName("创建部门")
    @ParameterizedTest
    @CsvFileSource(resources = "/data/department/CreateTestData.csv", numLinesToSkip = 1)
    void create_test(String name, String enName, String parentId, String order, String id) {
        name = name + Thread.currentThread().getId() + System.currentTimeMillis();
        Response response = DepartmentObject.createDepartment(ACCESS_TOKEN, name, enName, parentId, order, id);
        assertAll("创建部门返回结果校验",
                () -> assertEquals("0", response.path("errcode").toString()),
                () -> assertEquals("created", response.path("errmsg").toString()),
                () -> assertNotNull(response.path("id"))
        );
    }

}
