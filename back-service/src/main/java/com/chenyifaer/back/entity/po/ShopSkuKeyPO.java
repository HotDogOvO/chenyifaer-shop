package com.chenyifaer.back.entity.po;

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
 * 分类管理 - sku分类表
 * </p>
 *
 * @author wudh
 * @since 2019-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_shop_sku_key")
@ApiModel(value="ShopSkuKeyPO对象", description="分类管理 - sku分类表")
public class ShopSkuKeyPO extends Model<ShopSkuKeyPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "shop_sku_key_id", type = IdType.AUTO)
    private Integer shopSkuKeyId;

    @ApiModelProperty(value = "sku名称")
    private String keyName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.shopSkuKeyId;
    }

}
