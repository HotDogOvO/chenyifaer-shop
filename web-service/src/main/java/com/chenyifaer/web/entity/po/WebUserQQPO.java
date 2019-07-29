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
 * 前台账号管理 - QQ用户信息表
 * </p>
 *
 * @author wudh
 * @since 2019-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_web_user_qq")
@ApiModel(value="WebUserQqPO对象", description="前台账号管理 - QQ用户信息表")
public class WebUserQQPO extends Model<WebUserQQPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "user_qq_id", type = IdType.AUTO)
    private Integer userQqId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "QQ_OpenId")
    private String openId;

    @ApiModelProperty(value = "QQ性别（1：男 2：女）")
    private Integer qqSex;

    @ApiModelProperty(value = "省")
    private String qqProvince;

    @ApiModelProperty(value = "市")
    private String qqCity;

    @ApiModelProperty(value = "QQ生日")
    private String qqYear;

    @ApiModelProperty(value = "QQ星座")
    private String qqConstellation;

    @ApiModelProperty(value = "QQ头像")
    private String qqHeadImage;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.userQqId;
    }

}
