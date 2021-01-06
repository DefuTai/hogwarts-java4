package com.hogwarts.framwork;

import io.restassured.response.Response;

/**
 * 描述：用于保存结果的对象基类
 *
 * @Author defu
 * @Date 2021/1/7 2:07 上午
 * @Version 1.0
 **/
public class BaseResult {

    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
