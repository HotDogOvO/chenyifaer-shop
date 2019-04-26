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
 * @Date: 2019/4/26 13:05
 */
public enum ReturnOrdersStatusEnum {

    /** 待审核 */
    WAIT_CHECK("0", "待审核"),
    /** 审核通过 */
    CHECK_SUCCESS("1", "审核通过"),
    /** 退货中 */
    RETURN_GOODS("2","退货中"),
    /** 退货成功 */
    RETURN_GOODS_SUCCESS("3","退货成功"),
    /** 退货失败 */
    RETURN_GOODS_FAIL("4","退货失败")

    ;

    private String code;
    private String msg;

    ReturnOrdersStatusEnum(String code, String msg) {
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
