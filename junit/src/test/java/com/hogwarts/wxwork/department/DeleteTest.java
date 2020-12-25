package com.hogwarts.wxwork.department;

import com.alibaba.fastjson.JSONObject;
import com.hogwarts.workwechat.DepartmentObject;
import com.hogwarts.wxwork.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @DisplayName("删除部门")
    @Test
    void deleteTest() {
        //创建一条部门记录
        String departmentId = DepartmentObject.createDepartment(ACCESS_TOKEN);
        //对上面一条部门记录进行删除
        Response deleteDepartment = DepartmentObject.deleteDepartment(ACCESS_TOKEN, departmentId);
        assertAll("删除部门返回结果校验",
                () -> assertEquals("0", deleteDepartment.path("errcode").toString()),
                () -> assertEquals("deleted", deleteDepartment.path("errmsg").toString())
        );
        //通过查询接口对已删除记录进行数据校验
        Response listDepartment = DepartmentObject.listDepartment(ACCESS_TOKEN, departmentId);
        assertTrue(listDepartment.jsonPath().getList("department", JSONObject.class).size() == 0);
    }

}
