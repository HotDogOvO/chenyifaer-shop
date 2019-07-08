package com.chenyifaer.app.entity.po;

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
 * 小程序端 - 轮播图表
 * </p>
 *
 * @author wudh
 * @since 2019-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_app_banner")
@ApiModel(value="AppBannerPO对象", description="小程序端 - 轮播图表")
public class AppBannerPO extends Model<AppBannerPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "app_banner_id", type = IdType.AUTO)
    private Integer appBannerId;

    @ApiModelProperty(value = "其余4个模块的主键")
    private Integer appId;

    @ApiModelProperty(value = "类型（1：最新动态 2：每日推荐 3：新歌发布 4：茶话会）")
    private Integer appType;

    @ApiModelProperty(value = "轮播图名称")
    private String appBannerName;

    @ApiModelProperty(value = "轮播图路径")
    private String appBannerImageUrl;

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
        return this.appBannerId;
    }

}
