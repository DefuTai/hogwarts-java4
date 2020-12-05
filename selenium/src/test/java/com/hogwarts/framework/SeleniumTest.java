package com.hogwarts.framework;

import com.hogwarts.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020-11-30 00:19
 * @Version 1.0
 **/
public class SeleniumTest extends BaseTest {

    private ChromeDriver driver;
    private WebElement currentElement;

    public void run() {
        steps.forEach(step -> {
            if (step.keySet().contains("chrome")) {
                System.setProperty("webdriver.chrome.driver", "/Users/defu/Tools/web_drivers/chromedriver");
                driver = new ChromeDriver();
            }

            if (step.keySet().contains("quit")) {
                driver.quit();
            }

            if (step.keySet().contains("sleep")) {
                driver.manage().timeouts().implicitlyWait((Long) getValue(step, "sleep"), TimeUnit.SECONDS);
            }

            if (step.keySet().contains("implicitly_wait")) {
                driver.manage().timeouts().implicitlyWait((Long) getValue(step, "implicitly_wait", 5), TimeUnit.SECONDS);
            }

            if (step.keySet().contains("get")) {
                driver.get(getValue(step, "get").toString());
            }

            if (step.keySet().contains("find")) {
                ArrayList<By> bys = new ArrayList<>();
                ((HashMap<String, String>) getValue(step, "find")).entrySet().forEach(stringStringEntry -> {
                    if (stringStringEntry.getKey().contains("id")) {
                        bys.add(By.id(stringStringEntry.getValue()));
                    }
                    if (stringStringEntry.getKey().contains("xpath")) {
                        bys.add(By.xpath(stringStringEntry.getValue()));
                    }
                });
                currentElement = driver.findElement(bys.get(0));
            }

            if (step.keySet().contains("click")) {
                currentElement.click();
            }

            if (step.keySet().contains("send_keys")) {
                currentElement.sendKeys(getValue(step, "send_key").toString());
            }
        });
    }

}
