package com.chenyifaer.back.entity.dto;

import com.chenyifaer.basic.common.dto.PageDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

/**
 * 前台用户 - DTO
 * @Author:wudh
 * @Date: 2019/4/18 14:14
 */
@Data
@Accessors(chain = true)
public class WebUserDTO extends PageDTO {

    /** 账号 */
    @Length(max = 30 , message = "账号不能超过30个字符")
    private String userAccount;

    /** 昵称 */
    @Length(max = 30 , message = "昵称不能超过30个字符")
    private String userNickName;

    /** 用户关注渠道 （1：注册 2：微信用户 3：QQ用户 4：支付宝用户 5：微博用户） */
    private Integer userType;

    /** 用户状态 （0：禁用 1：启用 9：注销）*/
    private Integer status;

    /** 用户性别（0：保密 1：男 2：女） */
    private Integer userSex;

    /** 角色ID */
    private Integer roleId;

    /** 创建起始时间 */
    private String startTime;

    /** 创建结束时间 */
    private String endTime;



}
