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
 * 权限管理 - 后台权限表
 * @author wudh
 * @since 2019-04-06
 */
@Data
@Accessors(chain = true)
@TableName("t_admin_permission")
@ApiModel(value="AdminPermissionPO对象", description="权限管理 - 后台权限表")
public class AdminPermissionPO extends Model<AdminPermissionPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "admin_permission_id", type = IdType.AUTO)
    private String adminPermissionId;

    @ApiModelProperty(value = "菜单ID")
    private String adminMenuId;

    @ApiModelProperty(value = "权限code")
    private String adminPermissionCode;

    @ApiModelProperty(value = "权限路由")
    private String adminPermissionUrl;

    @ApiModelProperty(value = "权限描述")
    private String adminPermissionText;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
