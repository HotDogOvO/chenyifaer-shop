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
 * 用户订单详情 - VO
 * @Author:wudh
 * @Date: 2019/5/21 12:10
 */

@Data
@Accessors(chain = true)
public class UserOrdersDetailVO {

    /** 商品数量 */
    private Integer goodsCount;

    /** 商品价格 */
    private BigDecimal goodsPrice;

    /** 商品名称 */
    private String goodsName;

    /** 商品图片 */
    private String url;

    /** SKU Key名称 */
    private String keyName;

    /** SKU Value名称 */
    private String valueName;

}
