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
import java.util.List;

/**
 * 商品详情 - VO
 * @Author:wudh
 * @Date: 2019/5/10 16:28
 */

@Data
@Accessors(chain = true)
public class GoodsDetailVO {

    /** 主键 */
    private Integer goodsId;

    /** 商品名 */
    private String goodsName;

    /** 商品简介 */
    private String goodsText;

    /** 商品图片详情 */
    private String goodsImgContent;

    /** 商品SKU详情 */
    private String goodsSkuContent;

    /** 商品价格 */
    private BigDecimal goodsPrice;

    /** 商品折扣价 */
    private BigDecimal goodsDiscountPrice;

    /** 销量 */
    private Integer goodsSales;

    /** 库存 */
    private Integer goodsInventory;

    /** 商品图片集合 */
    private List<String> imgList;


}
