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
 * 前台账号管理 - 前台用户表
 * </p>
 *
 * @author wudh
 * @since 2019-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_web_user")
@ApiModel(value="WebUserPO对象", description="前台账号管理 - 前台用户表")
public class WebUserPO extends Model<WebUserPO> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "账号")
    private String userAccount;

    @ApiModelProperty(value = "密码")
    private String userPassword;

    @ApiModelProperty(value = "用户昵称")
    private String userNickName;

    @ApiModelProperty(value = "用户渠道（1：注册 2：微信用户 3：QQ用户 4：支付宝用户 5：微博用户）")
    private Integer userType;

    @ApiModelProperty(value = "状态（0：禁用 1：启用 9：注销）")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "注销时间")
    private LocalDateTime cancelledTime;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
