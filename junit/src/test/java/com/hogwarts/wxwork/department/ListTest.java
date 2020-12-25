package com.hogwarts.wxwork.department;

import com.alibaba.fastjson.JSONObject;
import com.hogwarts.workwechat.DepartmentObject;
import com.hogwarts.wxwork.BaseTest;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 描述：部门列表数据
 *
 * @Author defu
 * @Date 2020/12/21 2:07 上午
 * @Version 1.0
 **/
public class ListTest extends BaseTest {

    @BeforeEach
    @AfterEach
    void each() {
        clearDepartmentTask();
    }

    @DisplayName("部门列表")
    @Test
    void listTest() {
        Response response = DepartmentObject.listDepartment(ACCESS_TOKEN, "1");
        assertAll(
                "部门列表数据校验",
                () -> assertEquals("0", response.path("errcode").toString()),
                () -> assertEquals("ok", response.path("errmsg").toString()),
                () -> assertEquals("1", JsonPath.read(response.jsonPath().getList("department", JSONObject.class).get(0), "$.id").toString()),
                () -> assertEquals("DF测试工厂", JsonPath.read(response.jsonPath().getList("department", JSONObject.class).get(0), "$.name").toString()),
                () -> assertEquals("0", JsonPath.read(response.jsonPath().getList("department", JSONObject.class).get(0), "$.parentid").toString()),
                () -> assertEquals("100000000", JsonPath.read(response.jsonPath().getList("department", JSONObject.class).get(0), "$.order").toString())
        );
    }

}
