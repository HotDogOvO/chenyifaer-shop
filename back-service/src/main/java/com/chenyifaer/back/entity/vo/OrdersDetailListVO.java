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
 * 订单详情一对多 - VO
 * @Author:wudh
 * @Date: 2019/4/25 22:42
 */
@Data
@Accessors(chain = true)
public class OrdersDetailListVO {

    /** 商品总数量 */
    private Integer goodsCount;

    /** 商品单价 */
    private BigDecimal goodsPrice;

    /** 商品总价格 */
    private BigDecimal goodsTotalPrice;

    /** 商品名称 */
    private String goodsName;

}
