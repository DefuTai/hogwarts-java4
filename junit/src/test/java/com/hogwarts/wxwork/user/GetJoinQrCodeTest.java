package com.hogwarts.wxwork.user;

import com.hogwarts.workwechat.UserObject;
import com.hogwarts.wxwork.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 描述：获取加入企业二维码
 *
 * @Author defu
 * @Date 2020/12/26 1:53 上午
 * @Version 1.0
 **/
public class GetJoinQrCodeTest extends BaseTest {

    @DisplayName("获取加入企业二维码")
    @ParameterizedTest
    @CsvFileSource(resources = "/data/user/GetJoinQrCodeTestData.csv", numLinesToSkip = 1)
    void getJoinQrCode(String id, String description, String sizeType, String errCode) {
        Response response = UserObject.getJoinQrCode(ACCESS_TOKEN, sizeType);
        assertAll(description,
                () -> assertEquals(errCode, response.path("errcode").toString())
        );
    }

}
