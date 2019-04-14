package com.chenyifaer.basic.common.emuns;

/**
 * 系统返回值 枚举
 * @Author:wudh
 * @Date: 2019/4/6 17:52
 */
public enum ResultCodeEnums {
    /*********************************************/
    /** 登录成功 */
    SUCCESS_LOGIN("200","登录成功"),
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
    FAIL_11001("11001", "用户名或密码错误，请重新输入"),
    /** 登录用户被禁用 */
    FAIL_11002("11002","用户被禁用，请联系管理员"),

    /*********************************************/
    /** Check验证 */
    /** 请求参数不全 */
    CHECK_001("19001","请求失败，请求参数不全"),
    /** 菜单父ID与主键重复 */
    CHECK_002("19001","传递的父菜单ID与主键重复，操作失败"),
    /** 菜单下拥有子菜单 */
    CHECK_003("19003","菜单下拥有子菜单，无法删除"),


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
