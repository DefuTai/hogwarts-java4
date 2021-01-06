package com.hogwarts.framwork.testcase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.hogwarts.framwork.steps.StepModel;
import com.hogwarts.framwork.steps.StepResult;
import com.hogwarts.utils.FakerUtils;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * 描述：测试用例yaml文件对应的数据对象
 *
 * @Author defu
 * @Date 2021/1/7 2:20 上午
 * @Version 1.0
 **/
public class ApiTestCaseModel {

    public static final Logger logger = LoggerFactory.getLogger(ApiTestCaseModel.class);

    private String name;
    private String description;
    private ArrayList<StepModel> steps;
    private ArrayList<Executable> assertList = new ArrayList<>();
    private HashMap<String, String> testCaseVariables = new HashMap<>();

    public static ApiTestCaseModel load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
//        ApiTestCaseModel apiTestCaseModel = null;
//        try {
//            objectMapper.readValue(new File(path), ApiTestCaseModel.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return apiTestCaseModel;
        return objectMapper.readValue(new File(path),ApiTestCaseModel.class);
    }

    public void run() {
        /**
         * 加载用例层关键字变量
         */
        this.testCaseVariables.put("getTimeStamp", FakerUtils.getTimeStamp());
        logger.info("用例变量更新：" + testCaseVariables);

        /**
         * 遍历step进行执行
         */
        steps.forEach(step -> {
            StepResult stepResult = step.run(testCaseVariables);
            /**
             * 处理step返回的save变量
             */
            if (!stepResult.getStepVariables().isEmpty()) {
                testCaseVariables.putAll(stepResult.getStepVariables());
                logger.info("testcase变量更新：" + testCaseVariables);
            }
            /**
             * 处理assertList，以便统一断言
             */
            if (!stepResult.getAssertList().isEmpty()) {
                assertList.addAll(stepResult.getAssertList());
            }
        });
        /**
         * 进行统一断言
         */
        assertAll("", assertList.stream());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<StepModel> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<StepModel> steps) {
        this.steps = steps;
    }

    public ArrayList<Executable> getAssertList() {
        return assertList;
    }

    public void setAssertList(ArrayList<Executable> assertList) {
        this.assertList = assertList;
    }

    public HashMap<String, String> getTestCaseVariables() {
        return testCaseVariables;
    }

    public void setTestCaseVariables(HashMap<String, String> testCaseVariables) {
        this.testCaseVariables = testCaseVariables;
    }
}
