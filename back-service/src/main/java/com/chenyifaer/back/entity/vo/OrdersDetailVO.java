package com.chenyifaer.back.entity.vo;
/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单详情 - VO
 * @Author:wudh
 * @Date: 2019/4/25 22:56
 */

@Data
@Accessors(chain = true)
public class OrdersDetailVO {

    /** 订单流水号 */
    private String flowNumber;

    /** 快递公司 */
    private String expressName;

    /** 快递单号 */
    private String expressNumber;

    /** 快递价格 */
    private BigDecimal expressPrice;

    /** 订单价格 */
    private BigDecimal ordersPrice;

    /** 订单状态
     * （0：待付款 1：付款完毕，待发货 2：已发货，待收货 3：已收货，待完成
     * 4：已完成，待评论 7：已取消
     * 8： 超时关闭 9：已关闭 99：退货） */
    private Integer status;

    /** 是否为积分付款（0：否 1：是） */
    private String integralStatus;

    /** 是否为优惠券付款（0：否 1：是） */
    private String couponsStatus;

    /** 订单创建时间 */
    private Date createTime;

    /** 订单支付时间 */
    private Date payTime;

    /** 发货时间 */
    private Date deliverTime;

    /** 收货时间 */
    private Date collectTime;

    /** 关闭时间 */
    private Date closeTime;

    /** 收货人姓名 */
    private String consigneeName;

    /** 收货人手机号 */
    private String consigneePhone;

    /** 省 */
    private String province;

    /** 市 */
    private String city;

    /** 区 */
    private String area;

    /** 详细地址 */
    private String address;

    /** 邮编 */
    private String zip;

    /** 支付流水号 */
    private String payFlowNumber;

    /** 支付类型（1：支付宝 2：微信） */
    private Integer payType;

    /** 一对多集合 */
    private List<OrdersDetailListVO> ordersDetailList;
}
