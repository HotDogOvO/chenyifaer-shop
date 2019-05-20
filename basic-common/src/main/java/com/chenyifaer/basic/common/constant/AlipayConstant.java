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
 * 支付宝请求接口 - 常量
 * @Author:wudh
 * @Date: 2019/5/13 23:46
 */
public class AlipayConstant {

    /******************接口名*********************/
    /** 统一下单接口 */
    public static final String ALIPAY_PAY = "alipay.trade.page.pay";


    /*****************固定参数********************/
    /** 签名编码格式 */
    public static final String FORMAT = "json";

    /** 销售产品码 */
    public static final String PRODUCT_CODE = "FAST_INSTANT_TRADE_PAY";
}
