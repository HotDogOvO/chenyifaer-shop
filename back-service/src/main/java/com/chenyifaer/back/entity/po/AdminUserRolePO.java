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
 * 账号管理 - 后台账号角色表
 * @author wudh
 * @since 2019-04-06
 */
@Data
@Accessors(chain = true)
@TableName("t_admin_user_role")
@ApiModel(value="AdminUserRolePO对象", description="账号管理 - 后台账号角色表")
public class AdminUserRolePO extends Model<AdminUserRolePO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "admin_user_role_id", type = IdType.AUTO)
    private Integer adminUserRoleId;

    @ApiModelProperty(value = "用户ID")
    private Integer adminUserId;

    @ApiModelProperty(value = "角色ID")
    private Integer adminRoleId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
