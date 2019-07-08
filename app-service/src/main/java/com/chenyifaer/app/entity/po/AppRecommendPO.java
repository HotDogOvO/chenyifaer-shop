package com.chenyifaer.app.entity.po;

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
 * 小程序端 - 每日推荐表
 * </p>
 *
 * @author wudh
 * @since 2019-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_app_recommend")
@ApiModel(value="AppRecommendPO对象", description="小程序端 - 每日推荐表")
public class AppRecommendPO extends Model<AppRecommendPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "recommend_id", type = IdType.AUTO)
    private Integer recommendId;

    @ApiModelProperty(value = "新闻标题")
    private String recommendName;

    @ApiModelProperty(value = "新闻内容")
    private String recommendContent;

    @ApiModelProperty(value = "权重")
    private Integer weight;

    @ApiModelProperty(value = "状态（1：启用 0：禁用）")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.recommendId;
    }

}
