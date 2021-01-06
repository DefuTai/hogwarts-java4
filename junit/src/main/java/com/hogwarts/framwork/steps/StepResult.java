package com.hogwarts.framwork.steps;

import com.hogwarts.framwork.BaseResult;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 描述：用例执行结果对象
 *
 * @Author defu
 * @Date 2021/1/7 1:21 上午
 * @Version 1.0
 **/
public class StepResult extends BaseResult {

    private ArrayList<Executable> assertList;
    private HashMap<String, String> stepVariables = new HashMap<>();

    public ArrayList<Executable> getAssertList() {
        return assertList;
    }

    public void setAssertList(ArrayList<Executable> assertList) {
        this.assertList = assertList;
    }

    public HashMap<String, String> getStepVariables() {
        return stepVariables;
    }

    public void setStepVariables(HashMap<String, String> stepVariables) {
        this.stepVariables = stepVariables;
    }
}
