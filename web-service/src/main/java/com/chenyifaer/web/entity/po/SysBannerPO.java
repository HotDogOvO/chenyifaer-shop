package com.chenyifaer.web.entity.po;

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
 * 系统管理 - 轮播图管理表
 * </p>
 *
 * @author wudh
 * @since 2019-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_banner")
@ApiModel(value="SysBannerPO对象", description="系统管理 - 轮播图管理表")
public class SysBannerPO extends Model<SysBannerPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "banner_id", type = IdType.AUTO)
    private Integer bannerId;

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    @ApiModelProperty(value = "轮播图名称")
    private String bannerName;

    @ApiModelProperty(value = "轮播图路径")
    private String bannerImageUrl;

    @ApiModelProperty(value = "权重")
    private Integer weight;

    @ApiModelProperty(value = "状态（0：禁用 1：启用）")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.bannerId;
    }

}
