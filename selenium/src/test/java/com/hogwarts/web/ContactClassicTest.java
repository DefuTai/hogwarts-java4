package com.hogwarts.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述：实现网页版企业微信登录
 *
 * @Author defu
 * @Data 2020-11-11 20:26
 * @Version 1.0
 **/
public class ContactClassicTest {

    static WebDriver driver;

    @BeforeAll
    static void before() throws IOException {
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
            try {
                needLogin();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @AfterAll
    static void afterAll() throws InterruptedException {
        Thread.sleep(10 * 1000);
        driver.quit();
    }

    static void needLogin() throws InterruptedException, IOException {
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

    @Test
    void addContact() {
        driver.findElement(By.cssSelector("[node-type=\"addmember\"]")).click();
//        driver.findElement(By.linkText("添加成员")).click();
        driver.findElement(By.name("username")).sendKeys("Test01");
        driver.findElement(By.name("acctid")).sendKeys("Test01");
        driver.findElement(By.name("mobile")).sendKeys("15988888888");
        driver.findElement(By.linkText("保存")).click();
    }

    @Test
    void addContact2() {
        click(By.cssSelector("[node-type=\"addmember\"]"));
        sendKeys(By.name("username"), "Test02");
        sendKeys(By.name("acctid"), "Test02");
        sendKeys(By.name("mobile"), "15988888888");
        click(By.linkText("保存"));
    }

    @Test
    void searchDepartment() {
        click(By.id("menu_contacts"));
        sendKeys(By.id("memberSearchInput"), "销售部");
        click(By.cssSelector(".ww_icon_AddMember"));
        String content = driver.findElement(By.cssSelector(".js_party_info")).getText();
        assertTrue(content.contains("无任何成员"));
    }

    @Test
    void test_addEmployee() {
        //开始进行添加成员操作
        Actions actions = new Actions(driver);
        actions.click(driver.findElement(By.cssSelector("[class=\"index_service_cnt js_service_list\"]>a:nth-child(1)")));
        actions.perform();

        //用户名
        driver.findElement(By.cssSelector("[id=\"username\"]")).sendKeys("Test001");
        //别名
        driver.findElement(By.cssSelector("[id=\"memberAdd_english_name\"]")).sendKeys("001");
        //员工账号
        driver.findElement(By.cssSelector("[id=\"memberAdd_acctid\"]")).sendKeys("T" + System.currentTimeMillis());
        //性别
        driver.findElements(By.cssSelector("[class=\"ww_label ww_label_Middle\"]>[name=\"gender\"]")).get(1).click();
        //手机号码
        driver.findElement(By.cssSelector("[id=\"memberAdd_phone\"]")).sendKeys("13067888562");
        //固定电话
        driver.findElement(By.cssSelector("[id=\"memberAdd_telephone\"]")).sendKeys("0571-8888888");
        //邮箱
        driver.findElement(By.cssSelector("[id=\"memberAdd_mail\"]")).sendKeys("defu6579@vip.qq.com");
        //地址
        driver.findElement(By.cssSelector("[id=\"memberEdit_address\"]")).sendKeys("浙江省杭州市西湖区");
        //职务
        driver.findElement(By.cssSelector("[id=\"memberAdd_title\"]")).sendKeys("测试工程师");
        //身份
        driver.findElements(By.cssSelector("[class=\"ww_label ww_label_Middle\"]>[name=\"identity_stat\"]")).get(0).click();
        //点击保存
        driver.findElements(By.cssSelector("[class=\"member_colRight_operationBar ww_operationBar\"]>[class=\"qui_btn ww_btn js_btn_save\"]")).get(0).click();
    }

    void click(By by) {
        driver.findElement(by).click();
    }

    void sendKeys(By by, String content) {
        driver.findElement(by).sendKeys(content);
    }


}
