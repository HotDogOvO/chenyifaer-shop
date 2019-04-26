package com.chenyifaer.back.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

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
 * 订单列表 - VO
 * @Author:wudh
 * @Date: 2019/4/25 20:06
 */
@Data
@Accessors(chain = true)
public class OrdersVO {

    /** 主键 */
    private Integer ordersId;

    /** 订单流水号 */
    private String flowNumber;

    /** 快递单号 */
    private String expressNumber;

    /** 商品价格 */
    private BigDecimal goodsPrice;

    /** 快递价格 */
    private BigDecimal expressPrice;

    /** 订单价格 */
    private BigDecimal ordersPrice;

    /** 订单名称 */
    private String ordersName;

    /** 订单商品总数 */
    private Integer ordersCount;

    /** 订单状态
     * （0：待付款 1：付款完毕，待发货 2：已发货，待收货 3：已收货，待完成
     * 4：已完成，待评论 7：已取消
     * 8： 超时关闭 9：已关闭 99：退货） */
    private String status;

    /** 是否为积分付款（0：否 1：是） */
    private String integralStatus;

    /** 是否为优惠券付款（0：否 1：是） */
    private String couponsStatus;

    /** 收货人姓名 */
    private String consigneeName;

    /** 支付流水号 */
    private String payFlowNumber;

    /** 创建时间 */
    private String createTime;
}
