package com.hogwarts.web;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述：通讯录页面功能测试
 *
 * @Author defu
 * @Data 2020-11-21 01:34
 * @Version 1.0
 **/
public class ContactPOTest {

    private static MainPage mainPage;
    private static ContactPage contactPage;

    @BeforeAll
    static void beforeAll() throws IOException, InterruptedException {
        mainPage = new MainPage();
        //进入通讯录页面
        contactPage = mainPage.menuContact();
    }

    @Test
    void searchDepartTest() {
        assertTrue(contactPage.searchMemberInput("DF-测试部门01").getPartyInfo().contains("无任何成员"));
    }

    @Test
    void addDepartTest() {
        String departName = "DF-测试部门01";
        assertTrue(contactPage.addDepart(departName).searchMemberInput(departName).getPartyInfo().contains(departName));
    }

    @Test
    void modifyDepartTest() {
        String newDepartName = "DF-测试部门01 NEW";
        assertTrue(contactPage.searchMemberInput("DF-测试部门01").modifyDepart(newDepartName).deleteMemberInput().searchMemberInput(newDepartName).getPartyInfo().contains(newDepartName));
    }

    @Test
    void addMemberInDepart() {
        assertTrue(contactPage.searchMemberInput("DF-测试部门01").addMember("测试员工002", "TestMember002", "15987654322").deleteMemberInput().searchMemberInput("测试员工002").getPartyInfo().contains("测试员工001"));
    }

}
