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
 * 分类管理 - 商品分类表
 * </p>
 *
 * @author wudh
 * @since 2019-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_shop_goods_type")
@ApiModel(value="ShopGoodsTypePO对象", description="分类管理 - 商品分类表")
public class ShopGoodsTypePO extends Model<ShopGoodsTypePO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "shop_goods_type_id", type = IdType.AUTO)
    private Integer shopGoodsTypeId;

    @ApiModelProperty(value = "分类名称")
    private String typeName;

    @ApiModelProperty(value = "分类父ID")
    private Integer parentTypeId;

    @ApiModelProperty(value = "分类级别（一/二/三级）")
    private Integer rank;

    @ApiModelProperty(value = "分类状态（0：禁用 1：启用）")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.shopGoodsTypeId;
    }

}
