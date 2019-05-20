package com.chenyifaer.web.enums;
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
 * 支付宝支付状态 - 枚举
 * @Author:wudh
 * @Date: 2019/5/20 22:51
 */
public enum AlipayPayStatusEnum {

    /** 交易创建，等待买家付款 */
    WAIT_BUYER_PAY(1,"WAIT_BUYER_PAY"),
    /** 未付款交易超时关闭，或支付完成后全额退款 */
    TRADE_CLOSED(2,"TRADE_CLOSED"),
    /** 交易支付成功 */
    TRADE_SUCCESS(3,"TRADE_SUCCESS"),
    /** 交易结束，不可退款 */
    TRADE_FINISHED(4,"TRADE_FINISHED");

    private Integer code;
    private String msg;

    AlipayPayStatusEnum(Integer code, String msg) {
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
