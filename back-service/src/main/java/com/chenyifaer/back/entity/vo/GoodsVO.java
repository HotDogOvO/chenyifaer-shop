package com.chenyifaer.back.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

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
 * 商品列表 - VO
 * @Author:wudh
 * @Date: 2019/5/4 19:27
 */
@Data
@Accessors(chain = true)
public class GoodsVO {

    /** 主键 */
    private Integer goodsId;

    /** 商品分类ID */
    private Integer goodsTypeId;

    /** 商品名 */
    private String goodsName;

    /** 商品简介 */
    private String goodsText;

    /** 商品价格 */
    private BigDecimal goodsPrice;

    /** 商品折扣 */
    private String goodsDiscount;

    /** 商品折扣价 */
    private BigDecimal goodsDiscountPrice;

    /** 商品销量 */
    private Integer goodsSales;

    /** 商品库存 */
    private Integer goodsInventory;

    /** 状态（0：待审核 1：上架 2：下架 9：审核失败） */
    private String status;

    /** 是否推荐 */
    private String recommendedStatus;

    /** 是否支持积分 */
    private String integralStatus;

    /** 是否支持优惠券 */
    private String couponsStatus;

    /** 商品类别 */
    private String typeName;

    /** 商品图片URL */
    private String url;

    /** 创建时间 */
    private Date createTime;

}
