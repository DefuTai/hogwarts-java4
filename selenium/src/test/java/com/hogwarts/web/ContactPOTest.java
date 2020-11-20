package com.hogwarts.web;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020-11-21 01:34
 * @Version 1.0
 **/
public class ContactPOTest {

    @Test
    void memberAdd() throws IOException, InterruptedException {
        MainPage mainPage = new MainPage();

        ContactPage contactPage = mainPage.menuContact();
        contactPage.searchMemberInput("销售部");
        String content = contactPage.getPartyInfo();

        assertTrue(content.contains("无任何成员"));
    }

    /**
     * 搜索部门
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    void departSearchChainTest() throws IOException, InterruptedException {
        assertTrue(new MainPage().menuContact().searchMemberInput("销售部").getPartyInfo().contains("无任何成员"));
    }

    //添加员工
    @Test
    void addMemberTest() throws IOException, InterruptedException {
        new MainPage().menuContact().addMember("Test01", "Test01", "15987654321");
        new ContactPage().searchMember("Test001");
    }

}
