package com.hogwarts.wxwork.department;

import com.hogwarts.workwechat.DepartmentObject;
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
 * 描述：更新部门
 *
 * @Author defu
 * @Date 2020/12/21 2:07 上午
 * @Version 1.0
 **/
public class UpdateTest extends BaseTest {

    private static String name;
    private static String enName;
    private static int parentId;
    private static int order;

    @BeforeEach
    @AfterEach
    void each() {
        clearDepartmentTask();
    }

    @DisplayName("更新部门")
    @ParameterizedTest
    @CsvFileSource(resources = "/data/department/UpdateTestData.csv", numLinesToSkip = 1)
    void update_test(String id, String name, String enName, String parentId, String order) {
        //创建一条部门记录
        String departmentId = DepartmentObject.createDepartment(ACCESS_TOKEN);

        //修改刚创建的部门记录
        name = name + Thread.currentThread().getId() + System.currentTimeMillis();
        Response response = DepartmentObject.updateDepartment(ACCESS_TOKEN, departmentId, name, enName, parentId, order);
        assertAll("更新部门返回结果校验",
                () -> assertEquals("0", response.path("errcode").toString()),
                () -> assertEquals("updated", response.path("errmsg").toString())
        );

        this.name = name;
        this.enName = enName;
        this.parentId = Integer.valueOf(parentId);
        this.order = Integer.valueOf(order);
        //通过查询接口对已删除记录进行数据校验
        Response listDepartment = DepartmentObject.listDepartment(ACCESS_TOKEN, departmentId);
        assertAll("校验更新后的部门信息",
                () -> assertEquals(this.name, listDepartment.path("department.name[0]")),
                () -> assertEquals(this.enName, listDepartment.path("department.name_en[0]")),
                () -> assertEquals(this.parentId, (Integer) listDepartment.path("department.parentid[0]")),
                () -> assertEquals(this.order, (Integer) listDepartment.path("department.order[0]"))
        );
    }

}
