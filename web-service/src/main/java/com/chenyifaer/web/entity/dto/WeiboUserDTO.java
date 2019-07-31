package com.chenyifaer.web.entity.dto;

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
public class WeiboUserDTO {

    /** 昵称 */
    private String screen_name;

    /** 省ID */
    private Integer province;

    /** 市ID */
    private Integer city;

    /** 用户所在地 */
    private String location;

    /** 个人描述 */
    private String description;

    /** 头像 */
    private String profile_image_url;

    /** 性别 */
    private String gender;

}
