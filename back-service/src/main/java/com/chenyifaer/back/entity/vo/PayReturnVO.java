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
 * 退款列表 - VO
 * @Author:wudh
 * @Date: 2019/4/27 20:48
 */

@Data
@Accessors(chain = true)
public class PayReturnVO {

    /** 订单名称 */
    private String ordersName;

    /** 用户昵称 */
    private String userNickName;

    /** 订单流水号 */
    private String flowNumber;

    /** 支付流水号 */
    private String payFlowNumber;

    /** 退款流水号 */
    private String returnFlowNumber;

    /** 退款类型（1：支付宝支付 2：微信支付） */
    private String returnType;

    /** 退款金额 */
    private BigDecimal returnMoney;

    /** 状态（0：退款中 1：退款成功 2：退款失败） */
    private String status;

    /** 退款创建时间 */
    private Date createTime;

    /** 退款成功时间 */
    private Date paySuccessTime;

    /** 退款失败时间 */
    private Date payFailTime;

    /** 退款失败信息 */
    private String payFailRemark;

}
