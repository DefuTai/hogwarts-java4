package com.hogwarts.framework;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.hogwarts.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020-11-28 12:26
 * @Version 1.0
 **/
public class ParamsTest {

//    private static ChromeDriver driver;
//
//    @BeforeAll
//    static void beforeAll() {
//        System.setProperty("webdriver.chrome.driver", "/Users/defu/Tools/web_drivers/chromedriver");
//        driver = new ChromeDriver();
//    }
//
//    @ParameterizedTest
//    @MethodSource(value = "keyWord")
//    void search(String keyword) {
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//
//        driver.get("https://ceshiren.com/");
//        driver.findElement(By.id("search-button")).click();
//        driver.findElement(By.id("search-term")).sendKeys(keyword);
//    }
//
//    static Stream<String> keyWord() {
//        return Stream.of("定向班3期", "定向班四期");
//    }

    @ParameterizedTest
    @MethodSource(value = "searchToParam")
    void searchToParam(String keyword) {
        System.out.println(keyword);
    }

    static List<String> searchToParam() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference reference = new TypeReference<List<String>>() {
        };
        List<String> keyword = mapper.readValue(
                ParameterizedTest.class.getResourceAsStream("/framework/search_param.yaml"),
                reference
        );
        return keyword;
    }

    @ParameterizedTest
    @MethodSource(value = "search")
    public void search(BaseTest baseTest) {
        System.out.println("testCase = [" + baseTest + "]");
        baseTest.run();
    }

    static List<BaseTest> search() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        BaseTest baseTest = mapper.readValue(
                ParamsTest.class.getResourceAsStream("/framework/search_po_test.yaml"),
                PageObjTest.class);
        return baseTest.testGenerate();

    }

}
