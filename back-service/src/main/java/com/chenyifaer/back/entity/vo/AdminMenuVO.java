package com.chenyifaer.back.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

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
public class AdminMenuVO {

    /** 主键 */
    private Integer parentAdminMenuId;

    /** 父菜单名 */
    private String parentMenuName;

    /** 父菜单图标 */
    private String parentIcon;

    /** 父菜单路由 */
    private String parentUrl;

    /** 子菜单集合 */
    private List<AdminChildMenuVO> childMenuList;
}
