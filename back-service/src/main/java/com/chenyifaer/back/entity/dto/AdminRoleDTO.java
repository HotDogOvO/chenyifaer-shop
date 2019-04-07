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
public class AdminRoleDTO extends PageDTO {

    public interface Disable{};

    public interface Update{};

    /** 主键 */
    @NotNull(groups = {Disable.class , Update.class} , message = "adminRoleId不能为空")
    private Integer adminRoleId;

    /** 角色名 */
    @Length(max = 30 , message = "角色名不能超过30个字符")
    private String adminRoleName;

    /** 角色简介 */
    @Length(max = 100 , message = "角色简介不能超过100个字符")
    private String adminRoleText;

    /** 状态 */
    private Integer status;

}
