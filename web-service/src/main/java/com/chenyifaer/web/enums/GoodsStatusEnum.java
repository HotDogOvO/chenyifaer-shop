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
 * 商品状态 - 枚举
 * @Author:wudh
 * @Date: 2019/5/7 22:09
 */
public enum GoodsStatusEnum {

    /** 待审核 */
    STATUS_000(0,"待审核"),
    /** 上架 */
    STATUS_001(1,"上架"),
    /** 下架 */
    STATUS_002(2,"下架"),
    /** 审核失败 */
    STATUS_009(9,"审核失败")
    ;

    private Integer code;
    private String msg;

    GoodsStatusEnum(Integer code, String msg) {
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
