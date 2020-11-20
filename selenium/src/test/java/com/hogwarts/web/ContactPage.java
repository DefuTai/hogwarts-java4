package com.hogwarts.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020-11-21 01:29
 * @Version 1.0
 **/
public class ContactPage extends BasePage {

    ContactPage(WebDriver driver) {
        super(driver);
    }

    public ContactPage() {

    }

    /**
     * 添加成员
     *
     * @param userName 用户名
     * @param acctId   账户ID
     * @param mobile   电话号码
     * @return
     */
    ContactPage addMember(String userName, String acctId, String mobile) {
        driver.findElement(By.name("username")).sendKeys(userName);
        driver.findElement(By.name("acctid")).sendKeys(acctId);
        driver.findElement(By.name("mobile")).sendKeys(mobile);
        driver.findElement(By.linkText("保存")).click();
        return this;
    }

    /**
     * 添加部门
     *
     * @param departName 部门名称
     * @return
     */
    ContactPage addDepart(String departName) {
        return this;
    }

    /**
     * 输入部门搜索关键词
     *
     * @param departName
     * @return
     */
    ContactPage searchMemberInput(String departName) {
        sendKeys(By.id("memberSearchInput"), departName);
        click(By.cssSelector(".ww_icon_AddMember"));
        return this;
    }

    /**
     * 获取文本
     *
     * @return
     */
    String getPartyInfo() {
        String content = getText(By.cssSelector(".js_party_info"));
        return content;
    }

    /**
     * 输入成员名称
     *
     * @param userName
     * @return
     */
    ContactPage searchMember(String userName) {
        sendKeys(By.id("memberSearchInput"), userName);
        click(By.cssSelector(".member_display_cover_detail_name"));
        return this;
    }

}
