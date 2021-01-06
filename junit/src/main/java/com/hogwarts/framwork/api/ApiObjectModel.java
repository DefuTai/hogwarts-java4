package com.hogwarts.framwork.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.hogwarts.framwork.actions.ApiActionModel;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * 描述：接口文件反序列化
 *
 * @Author defu
 * @Date 2021/1/7 12:06 上午
 * @Version 1.0
 **/
public class ApiObjectModel {

    private String name;
    private HashMap<String, ApiActionModel> actions;
    private HashMap<String, String> obVariables = new HashMap<>();

    /**
     * 读取yaml接口文件，并反序列化为ApiObjectModel对象
     *
     * @param path yaml文件路径
     * @return
     */
    public static ApiObjectModel load(String path) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        ApiObjectModel apiObjectModel = null;
        try {
            apiObjectModel = objectMapper.readValue(new File(path), ApiObjectModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apiObjectModel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, ApiActionModel> getActions() {
        return actions;
    }

    public void setActions(HashMap<String, ApiActionModel> actions) {
        this.actions = actions;
    }

    public HashMap<String, String> getObVariables() {
        return obVariables;
    }

    public void setObVariables(HashMap<String, String> obVariables) {
        this.obVariables = obVariables;
    }
}
