package com.chenyifaer.back.entity.dto;

import com.chenyifaer.basic.common.dto.PageDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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
 * 商品模块 - DTO
 * @Author:wudh
 * @Date: 2019/5/4 19:33
 */
@Data
@Accessors(chain = true)
public class GoodsDTO extends PageDTO {

    public interface Add{};

    public interface Update{};

    public interface GetDetail{};

    /** 主键 */
    @NotNull(groups = {GoodsDTO.Update.class,GoodsDTO.GetDetail.class} , message = "商品名不能为空")
    private Integer goodsId;

    /** 商品名 */
    @Length(max = 30 , message = "商品名不能超过30个字符")
    @NotNull(groups = {GoodsDTO.Add.class} , message = "商品名不能为空")
    private String goodsName;

    /** 商品分类ID */
    @NotNull(groups = {GoodsDTO.Add.class} , message = "商品分类ID不能为空")
    private Integer goodsTypeId;

    /** 商品简介 */
    @NotNull(groups = {GoodsDTO.Add.class} , message = "商品简介不能为空")
    private String goodsText;

    /** 商品详情 */
    //@NotNull(groups = {GoodsDTO.Add.class} , message = "商品详情不能为空")
    private String goodsContent;

    /** 商品价格 */
    private BigDecimal goodsPrice;

    /** 商品折扣 */
    private String goodsDiscount;

    /** 商品折扣价 */
    private BigDecimal goodsDiscountPrice;

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

    /** 商品图片URL */
    @NotNull(groups = {GoodsDTO.Add.class} , message = "商品图片不能为空")
    private List<GoodsImageDTO> imgList;

    /** 商品类别 */
    @Length(max = 30 , message = "商品类别不能超过30个字符")
    private String typeName;

    /** 图片类型 */
    private String imgType;

    /** 起始时间 */
    private String startTime;

    /** 结束时间 */
    private String endTime;
}
