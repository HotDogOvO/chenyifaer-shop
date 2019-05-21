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
 * 邮件发送固定参数 - 常量
 * @Author:wudh
 * @Date: 2019/5/21 16:14
 */
public class EmailConstant {
    /**发送邮件使用的固定参数*/
    public static final String MAIL_HOST = "mail.host";
    public static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
    public static final String MAIL_TRANSPORT_PROTOCOL_VALUE = "smtp";
    public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    public static final String MAIL_SMTP_AUTH_VALUE = "true";

    /** 发送标题 */
    public static final String EMAIL_TITLE = "欢迎使用喜瑞斯商城，请查看您的验证码";
    /** 发送内容 */
    public static final String EMAIL_TEXT = "欢迎使用喜瑞斯商城，您本次的验证码为：${code}";

}
