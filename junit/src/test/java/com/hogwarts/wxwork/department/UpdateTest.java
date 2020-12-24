package com.hogwarts.wxwork.department;

import com.hogwarts.workwechat.DepartmentObject;
import com.hogwarts.wxwork.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 描述：修改部门
 *
 * @Author defu
 * @Date 2020/12/21 2:07 上午
 * @Version 1.0
 **/
public class UpdateTest extends BaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/data/UpdateTestData.csv", numLinesToSkip = 1)
    void update_test(String id, String name, String enName, String parentId, String order) {
        name = name + Thread.currentThread().getId() + System.currentTimeMillis();
        Response response = DepartmentObject.updateDepartment(ACCESS_TOKEN, id, name, enName, parentId, order);
        assertAll("修改部门返回结果校验",
                () -> assertEquals("0", response.path("errcode").toString()),
                () -> assertEquals("updated", response.path("errmsg").toString())
        );
    }

}
