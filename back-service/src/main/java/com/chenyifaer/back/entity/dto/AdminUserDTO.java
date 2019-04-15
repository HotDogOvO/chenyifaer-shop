package com.chenyifaer.back.entity.dto;

import com.chenyifaer.basic.common.dto.PageDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

@Data
@Accessors(chain = true)
public class AdminUserDTO extends PageDTO {

    public interface Add{};

    public interface Update{};

    public interface Disable{};

    public interface Reset{};

    /** 主键 */
    @NotNull(groups = {Update.class,Disable.class,Reset.class} , message = "userId不能为空")
    private Integer adminUserId;

    /** 账号 */
    @Length(max = 30 , message = "账号不能超过30个字符")
    @NotNull(groups = {Add.class},message = "账号不能为空")
    private String adminUserAccount;

    /** 姓名 */
    @Length(max = 30 , message = "姓名不能超过30个字符")
    @NotNull(groups = {Add.class},message = "姓名不能为空")
    private String adminUserName;

    /** 角色ID */
    @NotNull(groups = {Add.class},message = "角色不能为空")
    private Integer adminRoleId;

    /** 角色名 */
    @Length(max = 20 , message = "角色名不能超过20个字符")
    private String adminRoleName;

    /** 手机号 */
    @Length(max = 11 , message = "手机号不能超过11个字符")
    private String adminUserPhone;

    /** 邮箱 */
    @Length(max = 50 , message = "邮箱不能超过50个字符")
    private String adminUserEmail;

    /** 状态 */
    @NotNull(groups = {Disable.class},message = "状态不能为空")
    private Integer status;

}
