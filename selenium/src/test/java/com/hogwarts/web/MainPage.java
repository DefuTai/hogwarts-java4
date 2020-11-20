package com.hogwarts.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020-11-21 01:28
 * @Version 1.0
 **/
public class MainPage extends BasePage {

    MainPage() throws IOException, InterruptedException {
        this.initLogin();
    }

    /**
     * 企业微信WEB首页点击"菜单-添加成员"
     *
     * @return 跳转添加成员页面
     */
    ContactPage menuContact() {
        click(By.id("menu_contacts"));
        return new ContactPage(driver);
    }

    void initLogin() throws IOException, InterruptedException {
        //本地环境问题，需要手动设置驱动地址
        System.setProperty("webdriver.chrome.driver", "/Users/defu/Tools/web_drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        File cookieFile = new File("cookies.yaml");
        if (cookieFile.exists()) {
            //登录企业微信
            driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx");
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference<List<HashMap<String, Object>>> reference = new TypeReference<List<HashMap<String, Object>>>() {
            };
            //读取cookies.ymal文件中的cookie信息
            List<HashMap<String, Object>> cookies = mapper.readValue(cookieFile, reference);
            cookies.forEach(cookieMap -> {
                //将cookie信息设置给chrome驱动
                driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(), cookieMap.get("value").toString()));
            });
            //刷新进入企业微信首页
            driver.navigate().refresh();
        } else {
            needLogin();
        }
    }

    private void needLogin() throws InterruptedException, IOException {
        driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx?from=myhome");
        //sleep 15
        Thread.sleep(12 * 1000);
        Set<Cookie> cookies = driver.manage().getCookies();
        /**
         * 关于ObjectMapper对象maven依赖有个坑
         * 对于新建工程的同学pom中没有引用allure-junit5包的，需要手动引用jackson-databind
         * 且版本必须小于2.11.0，否则会抛出如下异常
         * java.lang.NoSuchMethodError: com.fasterxml.jackson.core.JsonGenerator.writeStartArray(Ljava/lang/Object;I)V
         */
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"), cookies);
    }

}
