package com.hogwarts.workwechat;

import com.hogwarts.utils.HttpRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：成员管理
 *
 * @Author defu
 * @Data 2020/12/23 1:22 下午
 * @Version 1.0
 **/
public class UserObject {

    public static Response createUser(String accessToken) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=" + accessToken;

        Map<String, Object> map = new HashMap<>();
        map.put("name", "");

        return HttpRequest.post(url, map, ContentType.JSON);
    }

}
