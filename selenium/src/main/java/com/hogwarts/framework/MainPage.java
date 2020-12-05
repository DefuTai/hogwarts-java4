package com.hogwarts.framework;

import com.hogwarts.BasePage;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020-11-28 15:26
 * @Version 1.0
 **/
public class MainPage extends BasePage {

    public MainPage() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://ceshiren.com");
    }

    SearchPage search() {
        return new SearchPage(driver);
    }

    /**
     * 注册方法
     *
     * @param method
     */
    @Deprecated
    public void stepRun(String method) {
        if (method.equals("search")) {
            this.search();
        }
    }

}
