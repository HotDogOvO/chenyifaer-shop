package com.chenyifaer.back.entity.dto;

import com.chenyifaer.basic.common.dto.PageDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

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
 * 支付信息 - DTO
 * @Author:wudh
 * @Date: 2019/4/27 20:33
 */
@Data
@Accessors(chain = true)
public class PayDTO extends PageDTO {

    /** 订单名称 */
    @Length(max = 30 , message = "订单名称不能超过30个字符")
    private String ordersName;

    /** 用户昵称 */
    @Length(max = 30 , message = "用户昵称不能超过30个字符")
    private String userNickName;

    /** 订单流水号 */
    @Length(max = 32 , message = "订单流水号不能超过30个字符")
    private String flowNumber;

    /** 支付流水号 */
    @Length(max = 32 , message = "支付流水号不能超过30个字符")
    private String payFlowNumber;

    /** 支付类型（1：支付宝支付 2：微信支付） */
    private String payType;

    /** 状态（0：支付中 1：支付成功 8：支付超时关闭 9：支付失败） */
    private String status;

    /** 起始时间 */
    private String startTime;

    /** 结束时间 */
    private String endTime;

}
