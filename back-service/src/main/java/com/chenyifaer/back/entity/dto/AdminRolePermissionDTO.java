package com.chenyifaer.back.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

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
public class AdminRolePermissionDTO {

    public interface Select{};
    public interface Permission{};

    /** 角色ID */
    @NotNull(groups = {Select.class,Permission.class},message = "角色ID不能为空")
    private Integer adminRoleId;


    /** 菜单ID */
    @NotNull(groups = {Permission.class},message = "菜单ID集合不能为空")
    private List<AdminRoleMenuDTO> adminMenuList;

}
