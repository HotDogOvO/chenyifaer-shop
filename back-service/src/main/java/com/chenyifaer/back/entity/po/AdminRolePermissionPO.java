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
 * 角色管理 - 后台角色权限表
 * @author wudh
 * @since 2019-04-06
 */
@Data
@Accessors(chain = true)
@TableName("t_admin_role_permission")
@ApiModel(value="AdminRolePermissionPO对象", description="角色管理 - 后台角色权限表")
public class AdminRolePermissionPO extends Model<AdminRolePermissionPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "admin_role_permission_id", type = IdType.AUTO)
    private Integer adminRolePermissionId;

    @ApiModelProperty(value = "角色ID")
    private Integer adminRoleId;

    @ApiModelProperty(value = "菜单ID")
    private Integer adminMenuId;

    @ApiModelProperty(value = "权限ID")
    private String adminPermissionId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
