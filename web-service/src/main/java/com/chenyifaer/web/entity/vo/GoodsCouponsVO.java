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

/**
 * 商城首页 - 支持优惠券商品 - VO
 * @Author:wudh
 * @Date: 2019/5/7 22:04
 */

@Data
@Accessors(chain = true)
public class GoodsCouponsVO {

    /** 主键 */
    private Integer goodsId;

    /** 商品名 */
    private String goodsName;

    /** 商品简介 */
    private String goodsText;

    /** 商品图片URL */
    private String url;

}
