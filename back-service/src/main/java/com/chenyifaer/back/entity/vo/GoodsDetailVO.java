package com.chenyifaer.back.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
 * 商品详情 - VO
 * @Author:wudh
 * @Date: 2019/5/6 11:28
 */
@Data
@Accessors(chain = true)
public class GoodsDetailVO {

    private String goodsName;

    private String goodsText;

    private BigDecimal goodsPrice;

    private String goodsDiscount;

    private BigDecimal goodsDiscountPrice;

    private Integer goodsSales;

    private Integer goodsInventory;

    private Integer status;

    private Integer recommendedStatus;

    private Integer integralStatus;

    private Integer couponsStatus;

    private Date createTime;

    private Date updateTime;

    private String url;

    private String typeName;

    private String keyName;

    private List<String> valueNameList;

}
