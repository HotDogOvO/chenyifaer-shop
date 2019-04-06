package com.chenyifaer.back.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 账号管理 - 后台账号表
 * @author wudh
 * @since 2019-04-06
 */
@Data
@Accessors(chain = true)
@TableName("t_admin_user")
@ApiModel(value="AdminUserPO对象", description="账号管理 - 后台账号表")
public class AdminUserPO extends Model<AdminUserPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "admin_user_id", type = IdType.AUTO)
    private String adminUserId;

    @ApiModelProperty(value = "账号")
    private String adminUserAccount;

    @ApiModelProperty(value = "密码")
    private String adminUserPassword;

    @ApiModelProperty(value = "用户名")
    private String adminUserName;

    @ApiModelProperty(value = "手机号")
    private String adminUserPhone;

    @ApiModelProperty(value = "邮箱")
    private String adminUserEmail;

    @ApiModelProperty(value = "状态（0：禁用 1：启用）")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
