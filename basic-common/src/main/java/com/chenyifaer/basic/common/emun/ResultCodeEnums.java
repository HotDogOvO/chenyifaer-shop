package com.chenyifaer.basic.common.emun;

/**
 * 系统返回值 枚举
 * @Author:wudh
 * @Date: 2019/4/6 17:52
 */
public enum ResultCodeEnums {
    /*********************************************/
    /** 请求成功 */
    SUCCESS("200", "请求成功"),
    /** 查询成功 */
    SUCCESS_001("200", "查询成功"),
    /** 新增成功 */
    SUCCESS_002("200", "新增成功"),
    /** 更新成功 */
    SUCCESS_003("200", "更新成功"),
    /** 删除成功 */
    SUCCESS_004("200", "删除成功"),

    /*********************************************/
    /** 请求失败 */
    FAIL("10000", "请求失败"),
    /** 查询失败 */
    FAIL_10001("10001","查询失败"),
    /** 新增失败 */
    FAIL_10002("10001","新增失败"),
    /** 更新 */
    FAIL_10003("10002","更新失败"),
    /** 删除 */
    FAIL_10004("10003","删除失败"),


    /** 用户名、密码错误 */
    FAIL_11001("11001", "用户名、密码错误"),


    /*********************************************/
    /** 请求参数不全 */
    CHECK("19001","请求失败，请求参数不全"),

    ;
    
    private ResultCodeEnums(String value, String msg) {
        this.val = value;
        this.msg = msg;
    }

    public String val() {
        return val;
    }

    public String msg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String val;
    private String msg;
}