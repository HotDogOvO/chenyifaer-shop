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
 * 订单管理 - 订单表
 * </p>
 *
 * @author wudh
 * @since 2019-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_shop_orders")
@ApiModel(value="ShopOrdersPO对象", description="订单管理 - 订单表")
public class ShopOrdersPO extends Model<ShopOrdersPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "orders_id", type = IdType.AUTO)
    private Integer ordersId;

    @ApiModelProperty(value = "订单流水号")
    private String flowNumber;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "快递公司名称")
    private String expressName;

    @ApiModelProperty(value = "快递单号")
    private String expressNumber;

    @ApiModelProperty(value = "订单商品价格")
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "运费")
    private BigDecimal expressPrice;

    @ApiModelProperty(value = "订单名称")
    private String ordersName;

    @ApiModelProperty(value = "订单价格")
    private BigDecimal ordersPrice;

    @ApiModelProperty(value = "订单商品总数量")
    private Integer ordersCount;

    @ApiModelProperty(value = "状态（0：待付款 1：付款完毕，待发货 2：已发货，待收货 3：已收货，待完成 4：已完成，待评论 7：已取消 8： 超时关闭 9：已关闭 99：退货）")
    private Integer status;

    @ApiModelProperty(value = "是否为积分付款（0：否 1：是）")
    private Integer integralStatus;

    @ApiModelProperty(value = "是否为优惠券付款（0：否 1：是）")
    private Integer couponsStatus;

    @ApiModelProperty(value = "订单备注")
    private String ordersRemark;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "付款时间")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "发货时间")
    private LocalDateTime deliverTime;

    @ApiModelProperty(value = "收货时间")
    private LocalDateTime collectTime;

    @ApiModelProperty(value = "关闭时间")
    private LocalDateTime closeTime;

    @ApiModelProperty(value = "取消时间")
    private LocalDateTime cancelledTime;

    @ApiModelProperty(value = "申请退货时间")
    private LocalDateTime returnTime;


    @Override
    protected Serializable pkVal() {
        return this.ordersId;
    }

}
