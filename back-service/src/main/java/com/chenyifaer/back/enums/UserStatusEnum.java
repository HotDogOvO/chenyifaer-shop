package com.chenyifaer.back.enums;

/**
 * _____ _            __     ___ ______                ________ ____ ______ ____
 * / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 * | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 * | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 * | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 * \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 */

/**
 * 用户状态枚举
 * @Author:wudh
 * @Date: 2019/4/18 17:53
 */
public enum UserStatusEnum {

    /** 禁用 */
    DISABLE("0", "禁用"),
    /** 启用 */
    ENABLE("1", "启用"),
    /** 注销 */
    CANCELL("9", "注销");

    private String code;
    private String msg;

    UserStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
