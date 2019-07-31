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
 * 前台账号管理 - 微博用户信息表
 * </p>
 *
 * @author wudh
 * @since 2019-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_web_user_weibo")
@ApiModel(value="WebUserWeiboPO对象", description="前台账号管理 - 微博用户信息表")
public class WebUserWeiboPO extends Model<WebUserWeiboPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "user_weibo_id", type = IdType.AUTO)
    private Integer userWeiboId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "微博用户标识")
    private String uid;

    @ApiModelProperty(value = "微博名")
    private String screenName;

    @ApiModelProperty(value = "省ID")
    private Integer provinceId;

    @ApiModelProperty(value = "市ID")
    private Integer cityId;

    @ApiModelProperty(value = "所在地")
    private String location;

    @ApiModelProperty(value = "个性签名")
    private String description;

    @ApiModelProperty(value = "头像")
    private String weiboHeadImage;

    @ApiModelProperty(value = "性别（1：男 2：女）")
    private Integer weiboSex;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.userWeiboId;
    }

}
