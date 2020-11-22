package com.hogwarts.app.wechat.po;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 描述：通讯录
 *
 * @Author defu
 * @Data 2020-11-22 20:47
 * @Version 1.0
 **/
public class ContactPage extends BasePage {

    private By menu = By.id("i6i");
    private By searchButton = By.id("i6n");
    private By editText = By.className("android.widget.EditText");
    private By clearButton = By.id("gpf");
    private By backButton = By.id("i63");
    private By addDepartment = By.xpath("//*[@text='添加子部门']");
    private By submitButton = By.xpath("//*[@text='确定']");
    private By closeButton = By.xpath("//*[contains(@resource-id, 'gpf') or contains(@resource-id, 'i6d')]");
    private By currentList = By.xpath("//android.widget.ListView//android.widget.TextView");
    private By moreManagement = By.xpath("//*[@text='更多管理']");
    private By removeButton = By.xpath("//*[@resource-id='egd' or @text='删除部门']");
    private By confirmButton = By.xpath("//*[@resource-id='blx' or @text='确定']");

    public ContactPage(AppiumDriver driver) {
        super(driver);
    }

    /**
     * 搜索
     * 1. 点击搜索按钮
     * 2. 输入关键词
     *
     * @param keywords
     * @return
     */
    public ContactPage search(String keywords) {
        click(searchButton);
        sendKey(editText, keywords);
        return this;
    }

    public boolean containsFromCurrentList(String keywords) {
        StringBuilder sb = new StringBuilder();
        driver.findElements(currentList).forEach(element -> sb.append(((WebElement) element).getText()));
        return sb.toString().contains(keywords);
    }

    /**
     * 清除关键词
     *
     * @return
     */
    public ContactPage clearEditText() {
        click(clearButton);
        return this;
    }

    /**
     * 返回
     *
     * @return
     */
    public ContactPage goBack() {
        click(backButton);
        return this;
    }

    /**
     * 添加部门
     * 1. 点击右上角菜单
     * 2. 点击添加子部门
     * 3. 输入部门名称
     * 4. 点击确定按钮
     * 5. 点击关闭管理通讯录页面
     *
     * @return
     */
    public ContactPage addDepartment(String departmentName) {
        click(menu);
        click(addDepartment);
        sendKey(editText, departmentName);
        click(submitButton);
        click(closeButton);
        click(backButton);
        return this;
    }

    /**
     * 删除部门
     * 1. 获取通讯录列表全部记录
     * 2. 匹配与要删除的部门名称相同的记录
     * 3. 匹配成功后，点击当前记录，进入部门管理页面
     * 4. 点击右上角菜单
     * 5. 点击更多管理
     * 6. 点击删除部门
     * 7. 点击确认按钮
     * 8. 点击返回（TODO 企业微信删除部门成功后，交互存在Bug，删除成功并关闭部门管理页面后，会停留在所删除的部门详情页面）
     *
     * @return
     */
    public ContactPage removeDepartment(String departmentName) {
        driver.findElements(currentList).forEach(department -> {
            if (((WebElement) department).getText().equals(departmentName)) {
                ((WebElement) department).click();
                click(menu);
                click(moreManagement);
                click(removeButton);
                click(confirmButton);
                click(closeButton);
                click(backButton);
            }
        });
        return this;
    }
}
