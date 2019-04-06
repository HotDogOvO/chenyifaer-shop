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
 * 权限管理 - 后台菜单表
 * @author wudh
 * @since 2019-04-06
 */
@Data
@Accessors(chain = true)
@TableName("t_admin_menu")
@ApiModel(value="AdminMenuPO对象", description="权限管理 - 后台菜单表")
public class AdminMenuPO extends Model<AdminMenuPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "admin_menu_id", type = IdType.AUTO)
    private String adminMenuId;

    @ApiModelProperty(value = "菜单名")
    private String adminMenuName;

    @ApiModelProperty(value = "父节点ID")
    private String adminMenuParentId;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "菜单路由")
    private String url;

    @ApiModelProperty(value = "状态（0：禁用 1：启用）")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
