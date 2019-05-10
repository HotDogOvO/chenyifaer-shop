package com.chenyifaer.web.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品管理 - 商品表
 * </p>
 *
 * @author wudh
 * @since 2019-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_shop_goods")
@ApiModel(value="ShopGoodsPO对象", description="商品管理 - 商品表")
public class ShopGoodsPO extends Model<ShopGoodsPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    @ApiModelProperty(value = "商品分类ID")
    private Integer goodsTypeId;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品简介")
    private String goodsText;

    @ApiModelProperty(value = "商品图片详情")
    private String goodsImgContent;

    @ApiModelProperty(value = "商品SKU详情")
    private String goodsSkuContent;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "商品折扣")
    private String goodsDiscount;

    @ApiModelProperty(value = "商品折扣价")
    private BigDecimal goodsDiscountPrice;

    @ApiModelProperty(value = "销量")
    private Integer goodsSales;

    @ApiModelProperty(value = "库存")
    private Integer goodsInventory;

    @ApiModelProperty(value = "状态（0：待审核 1：上架 9：下架）")
    private Integer status;

    @ApiModelProperty(value = "是否推荐（0：否 1：是）")
    private Integer recommendedStatus;

    @ApiModelProperty(value = "是否支持积分（0：否 1：是）")
    private Integer integralStatus;

    @ApiModelProperty(value = "是否支持优惠券（0：否 1：是）")
    private Integer couponsStatus;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.goodsId;
    }

}
