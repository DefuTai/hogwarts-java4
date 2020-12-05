package com.hogwarts.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020-11-20 22:41
 * @Version 1.0
 **/
public class BasePage {

    WebDriver driver;

    protected BasePage() {
    }

    protected BasePage(WebDriver driver) {
        //TODO 本地环境原因，必须手动设置
        System.setProperty("webdriver.chrome.driver", "/Users/defu/Tools/web_drivers/chromedriver");
        this.driver = driver;
    }

    void click(By by) {
        driver.findElement(by).click();
    }

    void sendKeys(By by, String content) {
        driver.findElement(by).sendKeys(content);
    }

    String getText(By by) {
        return driver.findElement(by).getText();
    }

}
