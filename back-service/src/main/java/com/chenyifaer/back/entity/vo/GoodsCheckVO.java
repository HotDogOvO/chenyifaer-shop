package com.chenyifaer.back.entity.vo;
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
import java.util.Date;

/**
 * 商品审核 - VO
 * @Author:wudh
 * @Date: 2019/5/5 22:50
 */
@Data
@Accessors(chain = true)
public class GoodsCheckVO {

    private Integer goodsCheckId;

    private Integer goodsId;

    private String goodsName;

    private String goodsText;

    private String typeName;

    private BigDecimal goodsPrice;

    private String goodsDiscount;

    private BigDecimal goodsDiscountPrice;

    private Integer goodsInventory;

    private String url;

    private String recommendedStatus;

    private String integralStatus;

    private String couponsStatus;

    private String status;

    private String failRemark;

    private Date createTime;

    private Date checkTime;

}
