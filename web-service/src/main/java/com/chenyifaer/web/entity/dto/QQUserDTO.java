package com.chenyifaer.web.entity.dto;
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
 * 接收QQ登录返回值
 * @Author:wudh
 * @Date: 2019/7/29 14:46
 */
@Data
@Accessors(chain = true)
public class QQUserDTO {

    /** 昵称 */
    private String nickname;

    /** 性别 */
    private String gender;

    /** 省 */
    private String province;

    /** 市 */
    private String city;

    /** 生日 */
    private String year;

    /** 星座 */
    private String constellation;

    /** 头像 */
    private String figureurl_qq;

}
