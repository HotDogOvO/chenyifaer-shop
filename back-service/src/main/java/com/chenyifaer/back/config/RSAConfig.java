package com.chenyifaer.back.config;

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
 * 读取config配置中心的RSA配置
 * @Author:wudh
 * @Date: 2019/4/14 16:23
 */

@Component
public class RSAConfig {

    public static String publicKey;
    @Value("${rsa.publicKey}")
    public void setPublicKey(String publicKey) {
        RSAConfig.publicKey = publicKey;
    }

    public static String privateKey;
    @Value("${rsa.privateKey}")
    public void setPrivateKey(String privateKey) {
        RSAConfig.privateKey = privateKey;
    }

    public static boolean isRSA;
    @Value("${rsa.isRSA}")
    public void setIsRSA(boolean isRSA) {
        RSAConfig.isRSA = isRSA;
    }
}
