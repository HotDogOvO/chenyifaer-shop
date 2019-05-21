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
 * RabbitMq系统配置 - 常量
 * @Author:wudh
 * @Date: 2019/5/21 0:03
 */
public class RabbitMqConstant {

    /** 统一交换机 */
    public static final String EXCHANGE = "chenyifaer-exchange";
    /** 统一交换模式 */
    public static final String EXCHANGE_TYPE = "topic";

    /*****************Queue******************/
    /** 支付宝异步回调队列 */
    public static final String PAY_NOTIFY_QUEUE = "payNotify-queue";
    /** 邮件发送队列 */
    public static final String EMAIL_QUEUE = "Email-queue";

    /*****************Routing-Key******************/
    /** 支付宝异步回调口令 */
    public static final String PAY_NOTIFY_ROUTING_KEY = "payNotify.*";
    /** 发送邮件口令 */
    public static final String EMAIL_ROUTING_KEY = "email.*";

}
