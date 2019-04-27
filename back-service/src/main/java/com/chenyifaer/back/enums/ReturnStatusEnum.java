package com.chenyifaer.back.enums;
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
 * 退单状态 - 枚举
 * @Author:wudh
 * @Date: 2019/4/27 13:34
 */
public enum ReturnStatusEnum {

    /** 待审核 */
    RETURN_STATUS_000("0", "待审核"),
    /** 审核通过 */
    RETURN_STATUS_001("1", "审核通过"),
    /** 退货中 */
    RETURN_STATUS_002("2", "退货中"),
    /** 退货通过 */
    RETURN_STATUS_003("3", "退货通过"),
    /** 退货失败 */
    RETURN_STATUS_004("4", "退货失败")

    ;

    private String code;
    private String msg;

    ReturnStatusEnum(String code, String msg) {
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
