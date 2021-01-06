package com.hogwarts.framwork;

import com.hogwarts.framwork.api.ApiLoader;
import com.hogwarts.framwork.testcase.ApiTestCaseModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 描述：
 *
 * @Author defu
 * @Date 2021/1/7 2:36 上午
 * @Version 1.0
 **/
public class ApiTestCaseModelTest {

    public static final Logger logger = LoggerFactory.getLogger(ApiTestCaseModelTest.class);

    @BeforeAll
    static void beforeAll() {
        ApiLoader.load("src/main/resources/api");
        logger.debug("Debugger!");
    }

    @Test
    void runTest() throws IOException {
        ApiTestCaseModel apiTestCaseModel = ApiTestCaseModel.load("src/test/resources/testcase/creatdepartment.yaml");
        apiTestCaseModel.run();
        logger.debug("Debugger!");
    }

}
