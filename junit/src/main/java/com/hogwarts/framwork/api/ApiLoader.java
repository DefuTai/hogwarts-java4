package com.hogwarts.framwork.api;

import com.hogwarts.framwork.actions.ApiActionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述：接口对象加载器
 *
 * @Author defu
 * @Date 2021/1/7 12:35 上午
 * @Version 1.0
 **/
public class ApiLoader {

    public static final Logger logger = LoggerFactory.getLogger(ApiLoader.class);

    /**
     * 家在所有API Object对象，并保存到列表中
     */
    private static List<ApiObjectModel> apiList = new ArrayList<>();

    /**
     * 加载指定目录下所有文件
     *
     * @param dir 目录地址
     */
    public static void load(String dir) {
        Arrays.stream(new File(dir).list()).forEach(path -> {
            apiList.add(ApiObjectModel.load(dir + "/" + path));
        });
    }

    /**
     * 从yaml文件列表中，通过apiName获取actionName
     *
     * @param apiName
     * @param actionName
     * @return
     */
    public static ApiActionModel getAction(String apiName, String actionName) {
        final ApiActionModel[] apiActionModels = {new ApiActionModel()};
        apiList.stream().filter(api -> api.getName().equals(apiName)).forEach(api -> {
            apiActionModels[0] = api.getActions().get(actionName);
        });
        if (apiActionModels[0] == null) {
            logger.warn("没有找到接口对象[" + apiName + "]中的action: " + actionName);
            return null;
        }
        return apiActionModels[0];
    }

}
