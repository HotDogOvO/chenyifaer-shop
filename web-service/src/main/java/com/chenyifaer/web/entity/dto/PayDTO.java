package com.chenyifaer.web.entity.dto;
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

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 支付请求 - DTO
 * @Author:wudh
 * @Date: 2019/5/14 10:56
 */

@Data
@Accessors(chain = true)
public class PayDTO {

    public interface Pay{};

    /** 流水号 */
    @NotNull(groups = {PayDTO.Pay.class} , message = "参数不能为空")
    private String flowNumber;

    /** 订单金额 */
    @NotNull(groups = {PayDTO.Pay.class} , message = "参数不能为空")
    private BigDecimal ordersPrice;

    /** 订单名称 */
    @NotNull(groups = {PayDTO.Pay.class} , message = "参数不能为空")
    private String ordersName;

}
