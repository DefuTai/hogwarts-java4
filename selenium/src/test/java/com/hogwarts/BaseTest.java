package com.hogwarts;

import com.hogwarts.framework.PageObjTest;
import com.hogwarts.framework.SeleniumTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020-11-30 00:10
 * @Version 1.0
 **/
public class BaseTest {

    private SeleniumTest seleniumTestCase = new SeleniumTest();

    protected int index = 0;
    protected List<String> data;
    protected List<HashMap<String, Object>> steps;

    /**
     * 用例裂变，基于数据自动生成多分测试用例
     *
     * @return
     */
    public List<BaseTest> testGenerate() {
        List<BaseTest> testList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            PageObjTest testNew = new PageObjTest();
            testNew.index = i;
            testNew.steps = steps;
            testNew.data = data;

            testList.add(testNew);
        }
        return testList;
    }

    /**
     * 获取yaml中指定ke对应的值
     *
     * @param step
     * @param key
     * @param defaultValue
     * @return
     */
    public Object getValue(HashMap<String, Object> step, String key, Object defaultValue) {
        return step.getOrDefault(key, defaultValue);
    }

    /**
     * 替换yaml中的变量
     *
     * @param step
     * @param key
     * @return
     */
    public Object getValue(HashMap<String, Object> step, String key) {
        Object value = step.get(key);
        if (value instanceof String) {
            //TODO 复杂结构需要用递归
            return ((String) value).replace("${data}", data.get(index));
        } else {
            return value;
        }
    }

    /**
     * 空实现，需要被子类实现
     */
    public void run() {

    }

    public void stepRun(String method) {
        System.out.println(method);
//        List<HashMap<String, Object>> steps = yamlSource.get(method);
        //上课的时候最后遇到的问题是这块重新初始化了
        seleniumTestCase.steps = steps;
        seleniumTestCase.data = Arrays.asList("");
        seleniumTestCase.run();
    }

}
