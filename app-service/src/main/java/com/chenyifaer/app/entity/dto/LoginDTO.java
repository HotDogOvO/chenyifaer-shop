package com.chenyifaer.app.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

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
public class LoginDTO {

    /** 微信授权code */
    @NotNull(message = "code不能为空")
    private String code;

    /** 昵称 */
    private String nickName;

    /** 头像url */
    private String avatarUrl;

    /** 国家 */
    private String country;

    /** 省 */
    private String province;

    /** 市 */
    private String city;

    /** 性别（1：男 2：女） */
    private Integer gender;

    /** 语言 */
    private String language;

}
