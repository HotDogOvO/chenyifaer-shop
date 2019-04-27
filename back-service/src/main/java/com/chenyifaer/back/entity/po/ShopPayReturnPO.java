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
 * 支付管理 - 退款表
 * </p>
 *
 * @author wudh
 * @since 2019-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_shop_pay_return")
@ApiModel(value="ShopPayReturnPO对象", description="支付管理 - 退款表")
public class ShopPayReturnPO extends Model<ShopPayReturnPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "pay_return_id", type = IdType.AUTO)
    private Integer payReturnId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "订单ID")
    private Integer ordersId;

    @ApiModelProperty(value = "支付流水号")
    private String payFlowNumber;

    @ApiModelProperty(value = "退款流水号")
    private String returnFlowNumber;

    @ApiModelProperty(value = "退款类型（1：支付宝 2：微信）")
    private Integer returnType;

    @ApiModelProperty(value = "退款金额")
    private BigDecimal returnMoney;

    @ApiModelProperty(value = "状态（0：退款中 1：退款成功 2：退款失败）")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "退款成功时间")
    private LocalDateTime paySuccessTime;

    @ApiModelProperty(value = "退款失败时间")
    private LocalDateTime payFailTime;

    @ApiModelProperty(value = "退款失败原因")
    private String payFailRemark;


    @Override
    protected Serializable pkVal() {
        return this.payReturnId;
    }

}
