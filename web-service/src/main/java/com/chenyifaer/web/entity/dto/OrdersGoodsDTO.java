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

import java.math.BigDecimal;

/**
 * 订单商品 - DTO
 * @Author:wudh
 * @Date: 2019/5/13 12:30
 */

@Data
@Accessors(chain = true)
public class OrdersGoodsDTO {

    /** 商品详情ID */
    private Integer goodsDetailId;

    /** 商品ID */
    private Integer goodsId;

    /** SkuId */
    private Integer goodsSkuId;

    /** 商品名 */
    private String goodsName;

    /** 商品数量 */
    private Integer goodsCount;

    /** 商品价格 */
    private BigDecimal goodsPrice;



}
