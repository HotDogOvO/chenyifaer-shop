package com.chenyifaer.back.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 角色管理 - 后台角色表
 * @author wudh
 * @since 2019-04-06
 */
@Data
@Accessors(chain = true)
@TableName("t_admin_role")
@ApiModel(value="AdminRolePO对象", description="角色管理 - 后台角色表")
public class AdminRolePO extends Model<AdminRolePO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "admin_role_id", type = IdType.AUTO)
    private Integer adminRoleId;

    @ApiModelProperty(value = "角色名")
    private String adminRoleName;

    @ApiModelProperty(value = "角色描述")
    private String adminRoleText;

    @ApiModelProperty(value = "状态（0：禁用 1：启用）")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
