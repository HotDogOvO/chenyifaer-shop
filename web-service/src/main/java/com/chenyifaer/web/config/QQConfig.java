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
 * QQ配置文件
 * @Author:wudh
 * @Date: 2019/7/29 11:42
 */
@Component
public class QQConfig {

    /** App秘钥 */
    public static String appKey;
    @Value("${qq.appKey}")
    public void setAppKey(String appKey) {
        QQConfig.appKey = appKey;
    }

    public static String appSecret;
    @Value("${qq.appSecret}")
    public void setAppSecret(String appSecret) {
        QQConfig.appSecret = appSecret;
    }

    public static String redirectUri;
    @Value("${qq.redirectUri}")
    public void setRedirectUri(String redirectUri) {
        QQConfig.redirectUri = redirectUri;
    }

    public static String grantType;
    @Value("${qq.grantType}")
    public void setGrantType(String grantType) {
        QQConfig.grantType = grantType;
    }
}
