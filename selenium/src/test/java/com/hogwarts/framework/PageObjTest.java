package com.hogwarts.framework;

import com.hogwarts.BasePage;
import com.hogwarts.BaseTest;

import java.util.ArrayList;

/**
 * 描述：
 *
 * @Author defu
 * @Data 2020-11-30 00:29
 * @Version 1.0
 **/
public class PageObjTest extends BaseTest {

    public void run() {
        steps.forEach(step -> {
            String key = step.keySet().iterator().next();
            if (key.contains("init")) {
                ArrayList<String> value = (ArrayList<String>) getValue(step, "init");
                BasePage.getInstance().poInit(value.get(0), value.get(1));
            }

            if (key.contains(".")) {
                String[] objectMethod = key.split("\\.");
                String object = objectMethod[0];
                String method = objectMethod[1];

                //自动找方法
                BasePage.getInstance().getPO(object).stepRun(method);
            }
        });
    }

}
