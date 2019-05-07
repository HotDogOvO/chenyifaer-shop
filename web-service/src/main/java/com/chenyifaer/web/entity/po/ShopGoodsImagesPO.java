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
 * 商品管理 - 商品图片表
 * </p>
 *
 * @author wudh
 * @since 2019-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_shop_goods_images")
@ApiModel(value="ShopGoodsImagesPO对象", description="商品管理 - 商品图片表")
public class ShopGoodsImagesPO extends Model<ShopGoodsImagesPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "goods_images_id", type = IdType.AUTO)
    private Integer goodsImagesId;

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    @ApiModelProperty(value = "图片类型（1：封面图 2：内容图）")
    private Integer type;

    @ApiModelProperty(value = "图片地址")
    private String url;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.goodsImagesId;
    }

}
