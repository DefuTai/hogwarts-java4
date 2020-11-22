package com.hogwarts.app.wechat.po;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020-11-22 18:45
 * @Version 1.0
 **/
public class BasePage {

    protected AppiumDriver driver;

    protected BasePage() {
    }

    protected BasePage(AppiumDriver driver) {
        this.driver = driver;
    }

    protected void click(By by) {
        driver.findElement(by).click();
    }

    protected void sendKey(By by, String content) {
        driver.findElement(by).sendKeys(content);
    }

    protected void clear(By by) {
        driver.findElement(by).clear();
    }

    protected String getText(By by) {
        return driver.findElement(by).getText();
    }

}
