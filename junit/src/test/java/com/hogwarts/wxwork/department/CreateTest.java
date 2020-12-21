package com.hogwarts.wxwork.department;

import com.hogwarts.workwechat.DepartmentObject;
import com.hogwarts.wxwork.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 描述：创建部门
 *
 * @Author defu
 * @Data 2020/12/21 1:06 上午
 * @Version 1.0
 **/
public class CreateTest extends BaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/data/CreateTestData.csv", numLinesToSkip = 1)
    void create_test(String accessToken, String name, String enName, String parentId, String order, String id, String returnCode) {
        Response response = DepartmentObject.creatDepartment(accessToken, name, enName, parentId, order, id);
        assertAll("创建部门返回结果校验",
                () -> assertEquals(returnCode, response.path("errcode").toString())
        );
    }

}