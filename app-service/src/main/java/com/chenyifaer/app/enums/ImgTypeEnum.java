package com.chenyifaer.app.enums;

/**
 * 图片类型枚举
 * @Author:wudh
 * @Date: 2019/7/8 10:19
 */
public enum ImgTypeEnum {

    /** 封面图 */
    IMG_TYPE_001(1, "封面图"),
    /** 内容轮播图 */
    IMG_TYPE_002(2, "内容轮播图");

    private Integer code;
    private String msg;

    ImgTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
