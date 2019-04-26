package com.chenyifaer.back.entity.po;

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
 * 订单管理 - 退单表
 * </p>
 *
 * @author wudh
 * @since 2019-04-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_shop_return_orders")
@ApiModel(value="ShopReturnOrdersPO对象", description="订单管理 - 退单表")
public class ShopReturnOrdersPO extends Model<ShopReturnOrdersPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "return_orders_id", type = IdType.AUTO)
    private Integer returnOrdersId;

    @ApiModelProperty(value = "订单ID")
    private Integer ordersId;

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "退单流水号")
    private String returnFlowNumber;

    @ApiModelProperty(value = "快递公司名称")
    private String expressName;

    @ApiModelProperty(value = "快递单号")
    private String expressNumber;

    @ApiModelProperty(value = "退单商品价格")
    private BigDecimal returnGoodsPrice;

    @ApiModelProperty(value = "运费")
    private BigDecimal expressPrice;

    @ApiModelProperty(value = "退单价格")
    private BigDecimal returnOrdersPrice;

    @ApiModelProperty(value = "退货类型（1：退货退款 2：仅退款）")
    private Integer returnType;

    @ApiModelProperty(value = "商品状态（1：已收到货 2：未收到货）")
    private Integer goodsType;

    @ApiModelProperty(value = "状态（0：待审核 1：审核通过 2：退货中 3：退货成功 4：退货失败）")
    private Integer status;

    @ApiModelProperty(value = "退单备注")
    private String returnRemark;

    @ApiModelProperty(value = "审核失败备注")
    private String checkFailRemark;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "审核通过时间")
    private LocalDateTime checkTime;

    @ApiModelProperty(value = "审核人ID")
    private Integer checkUserId;

    @ApiModelProperty(value = "审核失败时间")
    private LocalDateTime checkFailTime;

    @ApiModelProperty(value = "退货成功时间")
    private LocalDateTime returnSuccessTime;


    @Override
    protected Serializable pkVal() {
        return this.returnOrdersId;
    }

}
