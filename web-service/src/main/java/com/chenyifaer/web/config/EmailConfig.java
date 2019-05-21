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
 * 发送邮件配置文件
 * @Author:wudh
 * @Date: 2019/5/21 16:09
 */
@Component
public class EmailConfig {

    /** 邮件发送者 */
    public static String emailFrom;
    @Value("${email.emailFrom}")
    public void setEmailFrom(String emailFrom) {
        EmailConfig.emailFrom = emailFrom;
    }

    /** 邮箱秘钥 */
    public static String emailFromKey;
    @Value("${email.emailFromKey}")
    public void setEmailFromKey(String emailFromKey) {
        EmailConfig.emailFromKey = emailFromKey;
    }

    /** 邮箱域名地址 */
    public static String emailHost;
    @Value("${email.emailHost}")
    public void setEmailHost(String emailHost) {
        EmailConfig.emailHost = emailHost;
    }

}
