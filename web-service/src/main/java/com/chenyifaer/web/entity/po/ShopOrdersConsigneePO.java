package com.chenyifaer.web.entity.po;

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
 * 订单管理 - 订单收货人表
 * </p>
 *
 * @author wudh
 * @since 2019-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_shop_orders_consignee")
@ApiModel(value="ShopOrdersConsigneePO对象", description="订单管理 - 订单收货人表")
public class ShopOrdersConsigneePO extends Model<ShopOrdersConsigneePO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "orders_consignee_id", type = IdType.AUTO)
    private Integer ordersConsigneeId;

    @ApiModelProperty(value = "订单ID")
    private Integer ordersId;

    @ApiModelProperty(value = "收货人姓名")
    private String consigneeName;

    @ApiModelProperty(value = "收货人手机号")
    private String consigneePhone;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String area;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "邮编")
    private String zip;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.ordersConsigneeId;
    }

}
