package com.hogwarts.workwechat;

import com.hogwarts.utils.HttpRequest;
import io.restassured.response.Response;

/**
 * 描述：获取access_token
 *
 * @Author defu
 * @Data 2020/12/23 12:13 下午
 * @Version 1.0
 **/
public class TokenHelper {

    /**
     * 获取access_token
     *
     * @param corpId     企业ID
     * @param corpSecret 应用的凭证密钥
     * @return
     */
    public static Response getToken(String corpId, String corpSecret) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpId + "&corpsecret=" + corpSecret;
        return HttpRequest.get(url);
    }

}
