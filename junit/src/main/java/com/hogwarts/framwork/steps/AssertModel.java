package com.hogwarts.framwork.steps;

/**
 * 描述：用例断言对象
 *
 * @Author defu
 * @Date 2021/1/7 1:21 上午
 * @Version 1.0
 **/
public class AssertModel {

    /**
     * 实际结果
     */
    private String actual;
    /**
     * 匹配规则
     */
    private String matcher;
    /**
     * 期望结果
     */
    private String expect;
    /**
     * 原因
     */
    private String reason;

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getMatcher() {
        return matcher;
    }

    public void setMatcher(String matcher) {
        this.matcher = matcher;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
