package com.chenyifaer.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
 * 微博配置文件
 * @Author:wudh
 * @Date: 2019/7/30 16:29
 */
@Component
public class WeiboConfig {

    /** App秘钥 */
    public static String appKey;
    @Value("${weibo.appKey}")
    public void setAppKey(String appKey) {
        WeiboConfig.appKey = appKey;
    }

    public static String appSecret;
    @Value("${weibo.appSecret}")
    public void setAppSecret(String appSecret) {
        WeiboConfig.appSecret = appSecret;
    }

    public static String redirectUrl;
    @Value("${weibo.redirectUrl}")
    public void setRedirectUrl(String redirectUrl) {
        WeiboConfig.redirectUrl = redirectUrl;
    }

    public static String grantType;
    @Value("${weibo.grantType}")
    public void setGrantType(String grantType) {
        WeiboConfig.grantType = grantType;
    }
}
