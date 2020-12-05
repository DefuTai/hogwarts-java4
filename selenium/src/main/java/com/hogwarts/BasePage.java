package com.hogwarts;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020-11-28 15:27
 * @Version 1.0
 **/
public class BasePage {

    protected WebDriver driver;

    public static BasePage instance = null;

    public HashMap<String, BasePage> pages = new HashMap<>();
    public HashMap<String, List<HashMap<String, Object>>> yamlSource = new HashMap<>();

//    private SeleniumTest seleniumTestCase = new SeleniumTest();

    public BasePage() {
    }

    public BasePage(WebDriver driver) {
        //TODO 本地环境原因，必须手动设置
        System.setProperty("webdriver.chrome.driver", "/Users/defu/Tools/web_drivers/chromedriver");
        this.driver = driver;
    }

    public static BasePage getInstance() {
        if (instance == null) {
            return instance = new BasePage();
        } else {
            return instance;
        }
    }

    public void poInit(String name, String className) {
        BasePage pageClass = new BasePage();
        try {
            //动态创建类，并实例为一个对象
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference<HashMap<String, List<HashMap<String, Object>>>> typeReference =
                    new TypeReference<HashMap<String, List<HashMap<String, Object>>>>() {
                    };
            pageClass.yamlSource = mapper.readValue(
                    Object.class.getResourceAsStream(String.format("/framework/%s", className)),
                    typeReference);
            pages.put(name, pageClass);
            pageClass.stepRun("init");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stepRun(String method) {
        List<HashMap<String, Object>> steps = yamlSource.get(method);
        //TODO 怎样和test下面的测试类解耦
//        seleniumTestCase.steps = steps;
//        seleniumTestCase.data = Arrays.asList("");
//        seleniumTestCase.run();

    }

    public BasePage getPO(String name) {
        return pages.get(name);
    }

    /**
     * 点击事件
     *
     * @param by
     */
    public void click(By by) {
        driver.findElement(by).click();
    }

    /**
     * 输入内容
     *
     * @param by
     * @param content
     */
    public void sendKeys(By by, String content) {
        driver.findElement(by).sendKeys(content);
    }

    /**
     * 根据文本定位元素
     *
     * @param by
     * @return
     */
    public String getText(By by) {
        return driver.findElement(by).getText();
    }

}
