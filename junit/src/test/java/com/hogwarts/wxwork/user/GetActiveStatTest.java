package com.hogwarts.wxwork.user;

import com.hogwarts.utils.FormatDateUtil;
import com.hogwarts.workwechat.UserObject;
import com.hogwarts.wxwork.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 描述：获取企业活跃成员数
 *
 * @Author defu
 * @Date 2020/12/26 2:06 上午
 * @Version 1.0
 **/
public class GetActiveStatTest extends BaseTest {

    @DisplayName("获取企业活跃成员数")
    @Test
    void getJoinQrCode() {
        Response response = UserObject.getActiveStat(ACCESS_TOKEN, FormatDateUtil.formatDate(-7));
        assertAll("获取企业活跃成员数",
                () -> assertEquals("0", response.path("errcode").toString())
        );
    }

}
