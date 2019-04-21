package com.chenyifaer.back.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

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
public class AdminUserVO {

    /** 主键 */
    private Integer adminUserId;
    /** 账号 */
    private String adminUserAccount;
    /** 姓名 */
    private String adminUserName;
    /** 角色名 */
    private String adminRoleName;
    /** 手机号 */
    private String adminUserPhone;
    /** 邮箱 */
    private String adminUserEmail;
    /** 状态 */
    private String status;
    /** 创建时间 */
    private Date createTime;

}
