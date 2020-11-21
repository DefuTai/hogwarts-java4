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

    private By partyInfo = By.cssSelector(".js_party_info");

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
        click(By.linkText("添加成员"));
        sendKeys(By.name("username"), userName);
        sendKeys(By.name("acctid"), acctId);
        sendKeys(By.name("mobile"), mobile);
        click(By.linkText("保存"));
        return this;
    }

    /**
     * 添加部门
     * <p>
     * 操作步骤：
     * 1. 点击左上方搜索框右边的添加按钮
     * 2. 点击添加部门
     * 3. 输入部门名称
     * 4. 点击下拉框
     * 5. 点击所属部门
     * 6. 点击确认按钮
     *
     * @param departName 部门名称
     * @return
     */
    ContactPage addDepart(String departName) {
        click(By.linkText("添加"));
        click(By.linkText("添加部门"));

        sendKeys(By.name("name"), departName);
        click(By.linkText("选择所属部门"));
        driver.findElements(By.linkText("DF测试工厂")).get(1).click();

        click(By.linkText("确定"));
        return this;
    }

    /**
     * 修改部门信息
     * 1. 点击修改名称
     * 2. 输入新的部门名称
     * 3. 点击保存
     *
     * @param newDepartName
     * @return
     */
    ContactPage modifyDepart(String newDepartName) {
        click(By.linkText("修改名称"));
        sendKeys(By.name("name"), newDepartName);
        click(By.linkText("保存"));

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
        click(By.linkText(departName));
        return this;
    }

    /**
     * 删除搜索输入框中的关键字
     *
     * @return
     */
    ContactPage deleteMemberInput() {
        click(By.id("clearMemberSearchInput"));
        return this;
    }

    /**
     * 获取文本
     *
     * @return
     */
    String getPartyInfo() {
        String content = getText(partyInfo);
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
