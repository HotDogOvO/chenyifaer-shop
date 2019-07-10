package com.chenyifaer.app.config;

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
 * 读取微信小程序配置文件
 * @Author:wudh
 * @Date: 2019/7/6 18:06
 */
@Component
public class WechatConfig {

    public static String appId;
    @Value("${wechat.appid}")
    public void setAppId(String appId) {
        WechatConfig.appId = appId;
    }

    public static String appSecret;
    @Value("${wechat.appSecret}")
    public void setAppSecret(String appSecret) {
        WechatConfig.appSecret = appSecret;
    }

    public static String grantType;
    @Value("${wechat.grant_type}")
    public void setGrantType(String grantType) {
        WechatConfig.grantType = grantType;
    }

    public static String env;
    @Value("${wechat.env}")
    public void setEnv(String env) {
        WechatConfig.env = env;
    }

    public static String imgPath;
    @Value("${wechat.imgPath}")
    public void setImgPath(String imgPath) {
        WechatConfig.imgPath = imgPath;
    }

    public static String musicPath;
    @Value("${wechat.musicPath}")
    public void setMusicPath(String musicPath) {
        WechatConfig.musicPath = musicPath;
    }





}
