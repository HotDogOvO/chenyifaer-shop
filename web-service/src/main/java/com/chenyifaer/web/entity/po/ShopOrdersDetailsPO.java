package com.chenyifaer.web.entity.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单管理 - 订单详情表
 * </p>
 *
 * @author wudh
 * @since 2019-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_shop_orders_details")
@ApiModel(value="ShopOrdersDetailsPO对象", description="订单管理 - 订单详情表")
public class ShopOrdersDetailsPO extends Model<ShopOrdersDetailsPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "orders_detail_id", type = IdType.AUTO)
    private Integer ordersDetailId;

    @ApiModelProperty(value = "订单ID")
    private Integer ordersId;

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    @ApiModelProperty(value = "商品SKUID")
    private Integer goodsSkuId;

    @ApiModelProperty(value = "商品数量")
    private Integer goodsCount;

    @ApiModelProperty(value = "商品单价")
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "商品总价")
    private BigDecimal goodsTotalPrice;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.ordersDetailId;
    }

}
