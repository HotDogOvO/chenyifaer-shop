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
import java.util.Date;

/**
 * <p>
 * 小程序端 - 茶话会表
 * </p>
 *
 * @author wudh
 * @since 2019-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_app_tea")
@ApiModel(value="AppTeaPO对象", description="小程序端 - 茶话会表")
public class AppTeaPO extends Model<AppTeaPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "tea_id", type = IdType.AUTO)
    private Integer teaId;

    @ApiModelProperty(value = "标题")
    private String teaName;

    @ApiModelProperty(value = "内容")
    private String teaContent;

    @ApiModelProperty(value = "图片路径")
    private String url;

    @ApiModelProperty(value = "状态（1：启用 0：禁用）")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.teaId;
    }

}
