package com.chenyifaer.back.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

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
public class OauthUserVO {

    /** 主键 */
    private Integer adminUserId;

    /** 账号 */
    private String adminUserAccount;

    /** 密码 */
    private String adminUserPassword;

    /** 用户名 */
    private String adminUserName;

    /** 状态 */
    private Integer status;

}
