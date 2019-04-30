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
 * 分类管理 - sku分类属性表
 * </p>
 *
 * @author wudh
 * @since 2019-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_shop_sku")
@ApiModel(value="ShopSkuPO对象", description="分类管理 - sku分类属性表")
public class ShopSkuPO extends Model<ShopSkuPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "shop_sku_id", type = IdType.AUTO)
    private Integer shopSkuId;

    @ApiModelProperty(value = "sku_key_id")
    private Integer skuKeyId;

    @ApiModelProperty(value = "sku_value_id")
    private Integer skuValueId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.shopSkuId;
    }

}
