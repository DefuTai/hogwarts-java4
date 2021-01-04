package com.hogwarts.framwork.actions;

import com.hogwarts.framwork.global.GlobalVariables;
import com.hogwarts.utils.PlaceholderUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * 描述：接口动作对象
 *
 * @Author defu
 * @Date 2021/1/3 11:49 下午
 * @Version 1.0
 **/
public class ApiActionModel {

    /**
     * 请求方法
     */
    private String method = "get";
    /**
     * 请求地址
     */
    private String url;
    /**
     * POST请求，带URL
     */
    private String post;
    /**
     * GET请求，带URL
     */
    private String get;
    /**
     * 请求体
     */
    private String body;
    /**
     * 参数类型
     */
    private String contentType;
    /**
     * 请求参数
     */
    private HashMap<String, String> query;
    /**
     * 请求头
     */
    private HashMap<String, String> headers;
    /**
     * 接口返回
     */
    private Response response;
    /**
     * 形参
     */
    private ArrayList<String> formalParam;
    private HashMap<String, String> actionVariables = new HashMap<>();

    @Override
    public String toString() {
        return "ApiActionModel{" +
                "method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", post='" + post + '\'' +
                ", get='" + get + '\'' +
                ", body='" + body + '\'' +
                ", contentType='" + contentType + '\'' +
                ", query=" + query +
                ", headers=" + headers +
                ", response=" + response +
                ", formalParam=" + formalParam +
                '}';
    }

    public Response run(ArrayList<String> actualParam) {
        HashMap<String, String> finalQuery = new HashMap<>();
        String runBody = this.body;
        String runUrl = this.url;
        /**
         * 1. 确定请求方法和URL
         */
        if (StringUtils.isNotEmpty(post)) {
            runUrl = post;
            method = "post";
        } else if (StringUtils.isNotEmpty(get)) {
            runUrl = get;
            method = "get";
        }

        /**
         * 2. 请求参数、URL中全局变量替换
         * PS：这里需要编写占位符工具类PlaceholderUtils
         */
        if (!query.isEmpty()) {
            finalQuery.putAll(PlaceholderUtils.resolveMap(query, GlobalVariables.getGlobalVariables()));
        }
        //body全局变量替换
        runBody = PlaceholderUtils.resolveString(runBody, GlobalVariables.getGlobalVariables());
        //url全局变量替换
        runUrl = PlaceholderUtils.resolveString(runUrl, GlobalVariables.getGlobalVariables());

        /**
         * 3. 根据形参和实参构建内部变量map
         */
        for (int i = 0; i < formalParam.size(); i++) {
            actionVariables.put(formalParam.get(i), actualParam.get(i));
        }

        /**
         * 4. 请求、URL中的内部变量进行一个替换
         */
        if (!query.isEmpty()) {
            finalQuery.putAll(PlaceholderUtils.resolveMap(query, actionVariables));
        }
        runBody = PlaceholderUtils.resolveString(body, actionVariables);
        runUrl = PlaceholderUtils.resolveString(runUrl, actionVariables);

        /**
         * 5. 拿到了上面完成了变量替换的请求数据，我们接下来要进行请求合并返回
         */
        RequestSpecification requestSpecification = given().log().all();
        if (StringUtils.isNotEmpty(contentType)) {
            requestSpecification.contentType(contentType);
        }
        if (!headers.isEmpty()) {
            requestSpecification.headers(headers);
        }
        if (finalQuery != null && finalQuery.size() > 0) {
            requestSpecification.formParams(finalQuery);
        } else if (StringUtils.isNotEmpty(runBody)) {
            requestSpecification.body(runBody);
        }

        Response response = requestSpecification.request(method, runUrl).then().log().all().extract().response();

        this.response = response;

        return response;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getPost() {
        return post;
    }

    public String getGet() {
        return get;
    }

    public String getBody() {
        return body;
    }

    public String getContentType() {
        return contentType;
    }

    public HashMap<String, String> getQuery() {
        return query;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public Response getResponse() {
        return response;
    }

    public ArrayList<String> getFormalParam() {
        return formalParam;
    }
}
