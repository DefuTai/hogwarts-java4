package com.hogwarts.workwechat;

import com.hogwarts.utils.HttpRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020/12/21 2:59 上午
 * @Version 1.0
 **/
public class DepartmentObject {

    static String currentThread = String.valueOf(Thread.currentThread().getId());
    static String timestamp = String.valueOf(System.currentTimeMillis());

    /**
     * 创建部门
     *
     * @param accessToken 调用接口凭证
     * @param name        部门名称，必填
     * @param enName      英文名称
     * @param parentId    父部门id，必填
     * @param order       在父部门中的次序值
     * @param id          部门id
     * @return
     */
    public static Response creatDepartment(String accessToken, String name, String enName, String parentId, String order, String id) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + accessToken;

        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("name_en", enName);
        map.put("parentid", parentId);
        map.put("order", order);
        map.put("id", id);

        return HttpRequest.post(url, map, ContentType.JSON);
    }

    /**
     * 更新部门
     *
     * @param accessToken 调用接口凭证
     * @param id          部门id
     * @param name        部门名称
     * @param enName      英文名称
     * @param parentId    父部门id
     * @param order       在父部门中的次序值
     * @return
     */
    public static Response updateDepartment(String accessToken, String id, String name, String enName, String parentId, String order) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=" + accessToken;

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("name_en", enName);
        map.put("parentid", parentId);
        map.put("order", order);

        return HttpRequest.post(url, map, ContentType.JSON);
    }

}