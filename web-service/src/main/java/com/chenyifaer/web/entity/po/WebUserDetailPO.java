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
 * 前台账号管理 - 前台用户详情表
 * </p>
 *
 * @author wudh
 * @since 2019-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_web_user_detail")
@ApiModel(value="WebUserDetailPO对象", description="前台账号管理 - 前台用户详情表")
public class WebUserDetailPO extends Model<WebUserDetailPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "user_detail_id", type = IdType.AUTO)
    private Integer userDetailId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "性别（0：保密 1：男 2：女）")
    private Integer userSex;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String userPhone;

    @ApiModelProperty(value = "邮箱")
    private String userEmail;

    @ApiModelProperty(value = "生日")
    private String userBirthday;

    @ApiModelProperty(value = "用户头像")
    private String userHeadImage;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.userDetailId;
    }

}
