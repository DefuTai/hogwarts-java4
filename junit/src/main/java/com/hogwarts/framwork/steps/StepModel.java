package com.hogwarts.framwork.steps;

import com.hogwarts.framwork.api.ApiLoader;
import com.hogwarts.framwork.global.GlobalVariables;
import com.hogwarts.utils.PlaceholderUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 描述：用例执行步骤对象
 *
 * @Author defu
 * @Date 2021/1/7 1:19 上午
 * @Version 1.0
 **/
public class StepModel {
    public static final Logger logger = LoggerFactory.getLogger(StepModel.class);

    private String api;
    private String action;
    private ArrayList<String> actualParameter;
    private ArrayList<AssertModel> asserts;
    private HashMap<String, String> save;
    private HashMap<String, String> saveGlobal;
    private ArrayList<String> finalActualParameter = new ArrayList<>();
    private HashMap<String, String> stepVariables = new HashMap<>();
    private StepResult stepResult = new StepResult();
    private ArrayList<Executable> assertList = new ArrayList<>();

    public StepResult run(HashMap<String, String> testCaseVariables) {
        if (actualParameter != null) {
            finalActualParameter.addAll(PlaceholderUtils.resolveList(actualParameter, testCaseVariables));
        }
        /**
         * 根据case中配置的API对象和action信息，取出并执行相应的action
         */
        Response response = ApiLoader.getAction(api, action).run(finalActualParameter);

        if (save != null) {
            save.forEach((variablesName, path) -> {
                String value = String.valueOf(response.path(path));
                stepVariables.put(variablesName, value);
                logger.info("step变量更新： " + stepVariables);
            });
        }

        if (saveGlobal != null) {
            saveGlobal.forEach((variablesName, path) -> {
                String value = String.valueOf(response.path(path));
                GlobalVariables.getGlobalVariables().put(variablesName, value);
                logger.info("全局变量更新： " + GlobalVariables.getGlobalVariables());
            });
        }

        /**
         * 根据case中的配置对返回结果进行软断言，但不会终结测试将断言结果存入断言结果列表中，在用例最后进行统一结果输出
         */
        if (asserts != null) {
            asserts.stream().forEach(assertModel -> {
                assertList.add(() -> {
                    assertThat(assertModel.getReason(), String.valueOf(response.path(assertModel.getActual())), equalTo(assertModel.getExpect()));
                });
            });
        }

        /**
         * 将response和断言结果存储到stepResult对象中并返回
         */
        stepResult.setAssertList(assertList);
        stepResult.setStepVariables(stepVariables);
        stepResult.setResponse(response);

        return stepResult;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<String> getActualParameter() {
        return actualParameter;
    }

    public void setActualParameter(ArrayList<String> actualParameter) {
        this.actualParameter = actualParameter;
    }

    public ArrayList<AssertModel> getAsserts() {
        return asserts;
    }

    public void setAsserts(ArrayList<AssertModel> asserts) {
        this.asserts = asserts;
    }

    public HashMap<String, String> getSave() {
        return save;
    }

    public void setSave(HashMap<String, String> save) {
        this.save = save;
    }

    public HashMap<String, String> getSaveGlobal() {
        return saveGlobal;
    }

    public void setSaveGlobal(HashMap<String, String> saveGlobal) {
        this.saveGlobal = saveGlobal;
    }

}
