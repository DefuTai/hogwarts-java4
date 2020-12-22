package com.hogwarts.wxwork.department;

import com.hogwarts.workwechat.DepartmentObject;
import com.hogwarts.wxwork.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteTest extends BaseTest {

    @Test
    void update_test() {
        String id = "2";
        Response response = DepartmentObject.deleteDepartment(ACCESS_TOKEN, id);
        assertAll("创建部门返回结果校验",
                () -> assertEquals("0", response.path("errcode").toString()),
                () -> assertEquals("deleted", response.path("errmsg").toString())
        );
    }

}
