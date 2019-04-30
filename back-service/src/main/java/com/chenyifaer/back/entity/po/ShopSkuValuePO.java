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
import java.time.LocalDateTime;

/**
 * <p>
 * 分类管理 - sku属性表
 * </p>
 *
 * @author wudh
 * @since 2019-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_shop_sku_value")
@ApiModel(value="ShopSkuValuePO对象", description="分类管理 - sku属性表")
public class ShopSkuValuePO extends Model<ShopSkuValuePO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "shop_sku_value_id", type = IdType.AUTO)
    private Integer shopSkuValueId;

    @ApiModelProperty(value = "分类主键")
    private Integer shopGoodsTypeId;

    @ApiModelProperty(value = "sku值")
    private String valueName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.shopSkuValueId;
    }

}
