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
 * 快递鸟配置文件
 * @Author:wudh
 * @Date: 2019/5/21 0:46
 */
@Component
public class kdBirdConfig {

    /** 商户ID */
    public static String eBusinessID;
    @Value("${kdBird.EBusinessID}")
    public void setEBusinessID(String eBusinessID) {
        kdBirdConfig.eBusinessID = eBusinessID;
    }

    /** App秘钥 */
    public static String appKey;
    @Value("${kdBird.AppKey}")
    public void setAppKey(String appKey) {
        kdBirdConfig.appKey = appKey;
    }

}
