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
 * 订单状态 - 枚举
 * @Author:wudh
 * @Date: 2019/4/27 11:28
 */
public enum OrdersStatusEnum {

    /** 待付款 */
    STATUS_000(0, "待付款"),
    /** 付款完毕，待发货 */
    STATUS_001(1, "付款完毕，待发货"),
    /** 已发货，待收货 */
    STATUS_002(2, "已发货，待收货"),
    /** 已收货，待完成 */
    STATUS_003(3, "已收货，待完成"),
    /** 已完成，待评论 */
    STATUS_004(4, "已完成，待评论"),
    /** 已取消 */
    STATUS_007(7, "已取消"),
    /** 超时关闭 */
    STATUS_008(8, "超时关闭"),
    /** 已关闭 */
    STATUS_009(9, "已关闭"),
    /** 退货 */
    STATUS_099(99, "退货")
    ;

    private Integer code;
    private String msg;

    OrdersStatusEnum(Integer code, String msg) {
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
