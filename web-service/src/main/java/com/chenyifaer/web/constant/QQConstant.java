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
 * QQ第三方 - 请求接口
 * @Author:wudh
 * @Date: 2019/7/29 11:50
 */
public class QQConstant {

    /** 获取AccessToken */
    public static final String ACCESS_TOKEN_URL = "https://graph.qq.com/oauth2.0/token?grant_type=${grantType}&client_id=${appKey}&client_secret=${appSecret}&code=${code}&redirect_uri=${redirectUrl}";

    /** 获取OpenId */
    public static final String OPEN_ID_URL = "https://graph.qq.com/oauth2.0/me?access_token=${accessToken}";

    /** 获取用户详细信息 */
    public static final String USER_DETAIL_URL = "https://graph.qq.com/user/get_user_info?access_token=${accessToken}&oauth_consumer_key=${appKey}&openid=${openId}";
}
