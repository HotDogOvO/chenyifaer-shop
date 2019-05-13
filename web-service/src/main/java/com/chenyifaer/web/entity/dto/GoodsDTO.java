package com.chenyifaer.web.entity.dto;

import com.chenyifaer.basic.common.dto.PageDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

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
 * 商城首页商品 - DTO
 * @Author:wudh
 * @Date: 2019/5/7 22:12
 */
@Data
@Accessors(chain = true)
public class GoodsDTO extends PageDTO {

    public interface getGoodsByType{};

    public interface getGoodsBySku{};

    public interface getDetail{};

    public interface getSkuByGoods{};


    /** 主键 */
    @NotNull(groups = {GoodsDTO.getDetail.class,GoodsDTO.getSkuByGoods.class} , message = "参数不能为空")
    private Integer goodsId;

    /** 商品分类父ID */
    @NotNull(groups = {GoodsDTO.getGoodsByType.class} , message = "参数不能为空")
    private Integer parentTypeId;

    /** 是否推荐 */
    private Integer recommendedStatus;

    /** 是否支持优惠券 */
    private Integer couponsStatus;

    /** 是否支持积分 */
    private Integer integralStatus;

    /** 商品状态 */
    private Integer status;

    /** 商品图片类型（1：封面图 2：内容图） */
    private Integer type;

    /** 商品SKUID */
    @NotNull(groups = {GoodsDTO.getGoodsBySku.class} , message = "参数不能为空")
    private Integer shopSkuId;

    /** 获取商品起始位置 */
    private Integer startSize;

    /** 获取商品结束位置 */
    private Integer endSize;

}
