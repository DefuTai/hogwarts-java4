package com.hogwarts.framework;

import com.hogwarts.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020-11-28 15:49
 * @Version 1.0
 **/
public class SearchPage extends BasePage {

    SearchPage(WebDriver driver) {
        super(driver);
    }

    public void search(String keyword) {
        click(By.id("search-button"));
        sendKeys(By.id("search-term"), keyword);
    }

}
