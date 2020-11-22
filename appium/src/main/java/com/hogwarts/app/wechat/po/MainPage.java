package com.hogwarts.app.wechat.po;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * 描述：企业微信主页面
 *
 * @Author defu
 * @Data 2020-11-22 18:45
 * @Version 1.0
 **/
public class MainPage extends BasePage {

    public MainPage(AppiumDriver driver) {
        super(driver);
    }

    public MainPage() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "192.168.56.101:5555");    //网易MuMu 设备号 emulator-5554
        capabilities.setCapability("appActivity", ".launch.WwMainActivity");
        capabilities.setCapability("appPackage", "com.tencent.wework");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("dontStopAppOnReset", true);

        driver = new AppiumDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    /**
     * 跳转通讯录
     *
     * @return
     */
    public ContactPage goToContactPage() {
        click(By.xpath("//*[@text='通讯录']"));
        return new ContactPage(driver);
    }

    /**
     * 跳转工作台
     *
     * @return
     */
    public WorkBenchPage goToWorkBenchPage() {
        click(By.xpath("//*[@text='工作台']"));
        return new WorkBenchPage(driver);
    }

    /**
     * 跳转我
     *
     * @return
     */
    public MySelfPage goToMySelfPage() {
        click(By.xpath("//*[@text='我']"));
        return new MySelfPage(driver);
    }

    /**
     * 跳转其他企业
     *
     * @return
     */
    public OtherCompaniesPage goToOtherCompaniesPage() {
        click(By.xpath("//*[@text='其他企业']"));
        return new OtherCompaniesPage(driver);
    }

    /**
     * 跳转日程
     *
     * @return
     */
    public SchedulePage goToSchedulePage() {
        click(By.xpath("//*[@text='日程']"));
        return new SchedulePage(driver);
    }

    /**
     * 跳转待办
     *
     * @return
     */
    public UpcomePage goToUpcomePage() {
        click(By.xpath("//*[@text='待办']"));
        return new UpcomePage(driver);
    }

}
