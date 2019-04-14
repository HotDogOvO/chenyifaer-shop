package com.chenyifaer.basic.common.util;

import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;

/**
 * 系统返回信息
 * @Author:wudh
 * @Date: 2019/4/6 17:52
 */
public class ResponseResult {
    /**
     * 成功返回值（默认信息）
     * @return
     */
    public static JsonResult Success() {
        JsonResult result = new JsonResult(ResultCodeEnums.SUCCESS.val(), ResultCodeEnums.SUCCESS.msg());
        return result;
    }
    
    /**
     * 成功返回值（无数据场合、自定义信息）
     * @param resultCode
     * @return
     */
    public static JsonResult Success(ResultCodeEnums resultCode) {
        JsonResult result = new JsonResult(resultCode.val(), resultCode.msg());
        return result;
    }
    
    /**
     * 成功返回值（有数据场合、默认信息）
     * @param data
     * @return
     */
    public static JsonResult Success(Object data) {
        JsonResult result = new JsonResult(ResultCodeEnums.SUCCESS.val(), ResultCodeEnums.SUCCESS.msg(), data);
        return result;
    }
    
    /**
     * 成功返回值（有数据场合，msg自定义）
     * @param data
     * @return
     */
    public static JsonResult Success(ResultCodeEnums resultCode, Object data) {
        JsonResult result = new JsonResult(resultCode.val(), resultCode.msg(), data);
        return result;
    }

    /**
     * 失败返回值（无数据场合，msg自定义）
     * @param data
     * @return
     */
    public static JsonResult Fail(ResultCodeEnums resultCode) {
        JsonResult result = new JsonResult(resultCode.val(), resultCode.msg());
        return result;
    }
    
    /**
     * 失败返回值（有数据场合，msg自定义）
     * @param data
     * @return
     */
    public static JsonResult Fail(ResultCodeEnums resultCode, Object data) {
        JsonResult result = new JsonResult(resultCode.val(), resultCode.msg(), data);
        return result;
    }
    
    /**
     * 失败返回值（有数据场合，msg自定义）
     * @param data
     * @return
     */
    public static JsonResult Fail(ResultCodeEnums resultCode, String message) {
        JsonResult result = new JsonResult(resultCode.val(), message);
        return result;
    }
    
    /**
     * 判断返回结果，如果正确更新返回结果
     * @param result
     * @param resultCode
     * @return
     */
    public static JsonResult getJsonResult(JsonResult result, ResultCodeEnums resultCode) {
        JsonResult returnResult;
        
        // 处理结果为正确的场合
        if (ResultCodeEnums.SUCCESS.val().equals(result.getState())) {
            returnResult = new JsonResult(resultCode.val(), resultCode.msg(), result.getData());
        } else {
            returnResult = new JsonResult(result.getState(), result.getMessage(), result.getData());
        }
        return returnResult;
    }
}
