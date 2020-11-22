package com.hogwarts.app.wechat;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020-11-18 20:32
 * @Version 1.0
 **/
public class WeWorkClassicTest {

    static AppiumDriver driver;

    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(MobileCapabilityType.APPLICATION_NAME, "Android");
        capabilities.setCapability("platformName", "android");
//        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("deviceName", "192.168.56.101:5555");
        capabilities.setCapability("appActivity", ".launch.WwMainActivity");
        capabilities.setCapability("appPackage", "com.tencent.wework");
        capabilities.setCapability("noReset", true);

        driver = new AppiumDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    void test(){
        //添加员工"Test002"
        add();
        //查询员工"Test002"
        search();
        //删除员工"Test002"
//        remove();
    }

    /**
     * 删除
     */
    void remove() {
        //选择指定员工
        //点击右上角更多按钮
        //点击编辑成员
        //滑动屏幕到最底部
        //点击删除成员
        //弹窗中点击确认按钮
    }

    /**
     * 查询
     */
    @Test
    void search() {
        driver.findElement(MobileBy.id("i6n")).click();
//        driver.findElement(MobileBy.id("i68")).click();
        driver.findElement(MobileBy.id("gpg")).sendKeys("Test002");
    }

    /**
     * 添加员工
     */
    @Test
    void add(){
        //点击切换到通讯录页面
        driver.findElement(MobileBy.id("egd")).click();
        //点击添加成员
        driver.findElement(MobileBy.id("icn")).click();
        //点击手动输入添加
        driver.findElement(MobileBy.id("cox")).click();
        //输入姓名
        driver.findElement(MobileBy.id("b4t")).sendKeys("Test002");
        //输入手机号
        driver.findElement(MobileBy.id("fow")).sendKeys("15987654321");
        //点击地址输入框
        driver.findElement(MobileBy.id("b5n")).click();
        //输入地址
        driver.findElement(MobileBy.id("iz")).sendKeys("阿里巴巴");
        //选择指定地址
        driver.findElement(MobileBy.linkText("阿里巴巴西溪园区")).click();
        //点击确认按钮，返回员工信息编辑页
        driver.findElement(MobileBy.id("i6k")).click();
        //点击保存
        driver.findElement(MobileBy.id("i6k")).click();
    }

}
