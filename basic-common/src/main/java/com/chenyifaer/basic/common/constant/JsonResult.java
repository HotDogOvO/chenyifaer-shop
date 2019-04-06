package com.chenyifaer.basic.common.constant;

import java.io.Serializable;

/**
 * 前后端统一消息定义协议
 * @Author:wudh
 * @Date: 2019/4/6 17:46
 */
public class JsonResult implements Serializable {

    private static final long serialVersionUID = 1330909718058118801L;

    private String state;
    /** 错误消息 */
    private String message;
    /** 返回正确时候的数据 */
    private Object data;


    public JsonResult() {}

    /**
     * 无数据的场合返回格式
     */
    public JsonResult(String state, String message) {
        this.setState(state);
        this.setMessage(message);
    }

    /**
     * 有数据的场合返回格式
     */
    public JsonResult(String state, String message, Object data) {
        this.setState(state);
        this.setMessage(message);
        this.setData(data);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
