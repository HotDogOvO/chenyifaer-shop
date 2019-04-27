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

/**
 * 支付信息 - VO
 * @Author:wudh
 * @Date: 2019/4/27 20:29
 */

@Data
@Accessors(chain = true)
public class PayVO {

    /** 订单名称 */
    private String ordersName;

    /** 用户昵称 */
    private String userNickName;

    /** 订单流水号 */
    private String flowNumber;

    /** 支付流水号 */
    private String payFlowNumber;

    /** 支付类型（1：支付宝支付 2：微信支付） */
    private String payType;

    /** 支付金额 */
    private BigDecimal payMoney;

    /** 状态（0：支付中 1：支付成功 8：支付超时关闭 9：支付失败） */
    private String status;

    /** 支付创建时间 */
    private Date createTime;

    /** 支付成功时间 */
    private Date paySuccessTime;

    /** 支付失败时间 */
    private Date payFailTime;

    /** 支付失败信息 */
    private String payFailRemark;

}
