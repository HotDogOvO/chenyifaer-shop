package com.chenyifaer.web.constant;
/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

/**
 * 微博第三方 - 请求接口
 * @Author:wudh
 * @Date: 2019/7/30 16:54
 */
public class WeiboConstant {

    /** 获取AccessToken */
    public static final String ACCESS_TOKEN_URL = "https://api.weibo.com/oauth2/access_token?client_id=${appKey}&client_secret=${appSecret}&grant_type=${grantType}&code=${code}&redirect_uri=${redirectUrl}";

    /** 获取用户信息 */
    public static final String USER_DETAIL_URL = "https://api.weibo.com/2/users/show.json?access_token=${accessToken}&uid=${uid}";

}
