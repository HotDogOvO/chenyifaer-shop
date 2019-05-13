package com.chenyifaer.web.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

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
 * 订单详情模块 - DTO
 * @Author:wudh
 * @Date: 2019/5/13 12:30
 */
@Data
@Accessors(chain = true)
public class OrdersDetailDTO {

    public interface GetOrdersDetail{};

    /** 订单流水号 */
    @NotNull(groups = {OrdersDetailDTO.GetOrdersDetail.class} , message = "参数不能为空")
    private String flowNumber;

    /** 商品图片类型 */
    private Integer type;

}
