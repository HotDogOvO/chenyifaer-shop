package com.chenyifaer.back.entity.dto;
/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

import com.chenyifaer.basic.common.dto.PageDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

/**
 * 订单列表 - DTO
 * @Author:wudh
 * @Date: 2019/4/25 20:17
 */
@Data
@Accessors(chain = true)
public class OrdersDTO extends PageDTO {

    /** 订单流水号 */
    @Length(max = 32 , message = "订单流水号不能超过32个字符")
    private String flowNumber;

    /** 快递单号 */
    @Length(max = 32 , message = "快递单号不能超过32个字符")
    private String expressNumber;

    /** 订单价格 */
    private BigDecimal ordersPrice;

    /** 订单名称 */
    @Length(max = 30 , message = "订单名不能超过30个字符")
    private String ordersName;

    /** 状态
     * （0：待付款 1：付款完毕，待发货 2：已发货，待收货 3：已收货，待完成
     * 4：已完成，待评论 7：已取消
     * 8： 超时关闭 9：已关闭 99：退货） */
    private String status;

    /** 是否为积分付款（0：否 1：是） */
    private String integralStatus;

    /** 是否为优惠券付款（0：否 1：是） */
    private String couponsStatus;

    /** 收货人姓名 */
    @Length(max = 30 , message = "收货人姓名不能超过30个字符")
    private String consigneeName;

    /** 支付流水号 */
    @Length(max = 32 , message = "支付流水号不能超过32个字符")
    private String payFlowNumber;

    /** 起始时间 */
    private String startTime;

    /** 结束时间 */
    private String endTime;

}
