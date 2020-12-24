package com.hogwarts.wxwork.department;

import com.hogwarts.workwechat.DepartmentObject;
import com.hogwarts.wxwork.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 描述：删除部门
 *
 * @Author defu
 * @Date 2020/12/21 2:07 上午
 * @Version 1.0
 **/
public class DeleteTest extends BaseTest {

    @BeforeEach
    @AfterEach
    void each() {
        clearDepartmentTask();
    }

    @Test
    void deleteTest() {
        String departmentId = DepartmentObject.createDepartment(ACCESS_TOKEN);
        Response response = DepartmentObject.deleteDepartment(ACCESS_TOKEN, departmentId);
        assertAll("删除部门返回结果校验",
                () -> assertEquals("0", response.path("errcode").toString()),
                () -> assertEquals("deleted", response.path("errmsg").toString())
        );
    }

}
