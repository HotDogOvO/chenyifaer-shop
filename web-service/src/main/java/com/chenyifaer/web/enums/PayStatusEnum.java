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
 * 系统内支付状态
 * @Author:wudh
 * @Date: 2019/5/20 22:51
 */
public enum PayStatusEnum {

    /** 支付中 */
    PAY_STATUS_000(0,"支付中"),
    /** 支付成功 */
    PAY_STATUS_001(1,"支付成功"),
    /** 支付超时关闭 */
    PAY_STATUS_008(8,"支付超时关闭"),
    /** 支付失败 */
    PAY_STATUS_009(9,"支付失败");

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
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
