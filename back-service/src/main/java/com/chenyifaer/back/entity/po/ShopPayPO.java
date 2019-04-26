package com.chenyifaer.back.entity.po;

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
 * 支付管理 - 支付表
 * </p>
 *
 * @author wudh
 * @since 2019-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_shop_pay")
@ApiModel(value="ShopPayPO对象", description="支付管理 - 支付表")
public class ShopPayPO extends Model<ShopPayPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "pay_id", type = IdType.AUTO)
    private Integer payId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "订单ID")
    private Integer ordersId;

    @ApiModelProperty(value = "支付流水号")
    private String payFlowNumber;

    @ApiModelProperty(value = "支付类型（1：支付宝 2：微信）")
    private Integer payType;

    @ApiModelProperty(value = "支付金额")
    private BigDecimal payMoney;

    @ApiModelProperty(value = "状态（0：支付中 1：支付成功 8：支付超时关闭 9：支付失败）")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "支付成功时间")
    private LocalDateTime paySuccessTime;

    @ApiModelProperty(value = "支付失败时间")
    private LocalDateTime payFailTime;

    @ApiModelProperty(value = "支付失败原因")
    private String payFailRemark;


    @Override
    protected Serializable pkVal() {
        return this.payId;
    }

}
