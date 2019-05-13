package com.chenyifaer.web.entity.vo;
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
 * 订单详情 - VO
 * @Author:wudh
 * @Date: 2019/5/13 13:45
 */

@Data
@Accessors(chain = true)
public class OrdersDetailVO {

    /** 订单详情ID */
    private Integer ordersDetailId;

    /** 商品名 */
    private String goodsName;

    /** 商品单价 */
    private BigDecimal goodsPrice;

    /** 商品数量 */
    private Integer goodsCount;

    /** 商品总价 */
    private BigDecimal goodsTotalPrice;

    /** 商品封面图 */
    private String url;

    /** SkuValue */
    private String valueName;

}
