package com.chenyifaer.basic.common.constant;
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
 * Redis - Key值 - 常量
 * @Author:wudh
 * @Date: 2019/5/11 12:13
 */
public class RedisConstant {

    /************************公用****************************/
    /** 分隔符 */
    public static final String REDIS_SPLIT = ":";
    /** 邮箱验证码存储时间 - 5分钟 */
    public static final Long EMAIL_CODE_TIME = 300L;

    /************************后台****************************/

    /************************前台****************************/
    /** 商品信息 */
    public static final String REDIS_GOODS_R01 = "CYFE:67373:R01:";
    /** 邮箱验证码 */
    public static final String REDIS_EMAIL_CODE_R02 = "CYFE:67373:R02:";

    /** 商品ID集合 */
    public static final String REDIS_GOODS_S01 = "CYFE:67373:S01:";

}
