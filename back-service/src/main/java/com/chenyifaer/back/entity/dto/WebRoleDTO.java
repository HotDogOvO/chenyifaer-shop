package com.chenyifaer.back.entity.dto;
/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 前台角色管理 - DTO
 * @Author:wudh
 * @Date: 2019/4/19 11:12
 */
@Data
@Accessors(chain = true)
public class WebRoleDTO {

    public interface Add{};

    public interface Update{};

    public interface Disable{};

    /** 角色ID */
    @NotNull(groups = {Update.class,Disable.class} , message = "roleId不能为空")
    private Integer roleId;

    /** 角色名 */
    @Length(max = 30 , message = "角色名不能超过30个字符")
    @NotNull(groups = {Add.class} , message = "角色名不能为空")
    private String roleName;

    /** 角色简介 */
    @NotNull(groups = {Add.class} , message = "角色简介不能为空")
    private String roleText;

    /** 状态 */
    @NotNull(groups = {Disable.class} , message = "状态不能为空")
    private Integer status;

}
