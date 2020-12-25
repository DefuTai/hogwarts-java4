package com.hogwarts.workwechat;

import com.hogwarts.utils.HttpRequest;
import io.restassured.response.Response;

/**
 * 描述：消息推送
 *
 * @Author defu
 * @Date 2020/12/26 2:41 上午
 * @Version 1.0
 **/
public class MessageObject {

    /**
     * 发送应用消息
     *
     * @param accessToken 调用接口凭证
     * @param param       消息内容
     * @return
     */
    public static Response sendMessage(String accessToken, String param) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + accessToken;

        return HttpRequest.post(url, param);
    }

}
