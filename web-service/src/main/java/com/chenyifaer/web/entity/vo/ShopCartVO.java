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
 * 购物车 - VO
 * @Author:wudh
 * @Date: 2019/5/11 12:23
 */

@Data
@Accessors(chain = true)
public class ShopCartVO {

    /** 主键 */
    private Integer goodsShopCartId;

    /** 商品ID */
    private Integer goodsId;

    /** 商品SKU - ID */
    private Integer goodsSkuId;

    /** 商品SKU名 */
    private String valueName;

    /** 商品名 */
    private String goodsName;

    /** 商品图片路径 */
    private String url;

    /** 商品数量 */
    private Integer goodsCount;

    /** 商品价格 */
    private BigDecimal goodsPrice;

    /** 商品折扣价 */
    private BigDecimal goodsDiscountPrice;

    /** 商品总价 */
    private BigDecimal goodsTotalPrice;

}
