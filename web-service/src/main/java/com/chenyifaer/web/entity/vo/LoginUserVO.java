package com.chenyifaer.web.entity.vo;
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

/**
 * 用户登录返回值 - VO
 * @Author:wudh
 * @Date: 2019/5/9 17:39
 */
@Data
@Accessors(chain = true)
public class LoginUserVO {

    /** 主键 */
    private Integer userId;

    /** 用户昵称 */
    private String userNickName;

    /** 用户状态 */
    private Integer status;

    /** 用户角色名 */
    private String roleName;

    /** 用户头像 */
    private String userHeadImage;

}
