package com.chenyifaer.basic.common.emuns;
/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

/**
 * 用户状态
 * @Author:wudh
 * @Date: 2019/4/13 18:33
 */
public enum UserStatusEnum {

    DISABLE(0,"禁用"),
    ENABLE(1,"启用");

    private int code;
    private String val;

    UserStatusEnum(int code, String val) {
        this.code = code;
        this.val = val;
    }

    public int getCode() {
        return code;
    }

    public String getVal() {
        return val;
    }
}
