package com.chenyifaer.web.enums;

/**
 * _____ _            __     ___ ______                ________ ____ ______ ____
 * / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 * | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 * | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 * | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 * \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 */

/**
 * 用户渠道枚举
 * @Author:wudh
 * @Date: 2019/4/18 17:53
 */
public enum UserTypeEnum {

    /** 注册用户 */
    REGISTER(1, "注册用户"),
    /** 微信用户 */
    WECHAT(2, "微信用户"),
    /** QQ用户 */
    QQ(3, "QQ用户"),
    /** 支付宝用户 */
    APPLY(4,"支付宝用户"),
    /** 微博用户 */
    WEIBO(5,"微博用户");


    private Integer code;
    private String msg;

    UserTypeEnum(Integer code, String msg) {
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
