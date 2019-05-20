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
 * 读取支付宝配置文件
 * @Author:wudh
 * @Date: 2019/5/13 23:38
 */
@Component
public class AlipayConfig {

    /** AppId */
    public static String appid;
    @Value("${alipay.appid}")
    public void setAppid(String appid) {
        AlipayConfig.appid = appid;
    }

    /** 请求网关 */
    public static String gateway;
    @Value("${alipay.gateway}")
    public void setGateway(String gateway) {
        AlipayConfig.gateway = gateway;
    }

    /** 编码格式 */
    public static String charset;
    @Value("${alipay.charset}")
    public void setCharset(String charset) {
        AlipayConfig.charset = charset;
    }

    /** 签名格式 */
    public static String signtype;
    @Value("${alipay.signtype}")
    public void setSigntype(String signtype) {
        AlipayConfig.signtype = signtype;
    }

    /** 接口版本 */
    public static String version;
    @Value("${alipay.version}")
    public void setVersion(String version) {
        AlipayConfig.version = version;
    }

    /** 应用私钥 */
    public static String privateKey;
    @Value("${alipay.privateKey}")
    public void setPrivateKey(String privateKey) {
        AlipayConfig.privateKey = privateKey;
    }

    /** 支付宝公钥 */
    public static String alipayPublicKey;
    @Value("${alipay.alipayPublicKey}")
    public void setAlipayPublicKey(String alipayPublicKey) {
        AlipayConfig.alipayPublicKey = alipayPublicKey;
    }

    /** 同步回调地址 */
    public static String returnUrl;
    @Value("${alipay.returnUrl}")
    public void setReturnUrl(String returnUrl) {
        AlipayConfig.returnUrl = returnUrl;
    }

    /** 异步回调地址 */
    public static String notifyUrl;
    @Value("${alipay.notifyUrl}")
    public void setNotifyUrl(String notifyUrl) {
        AlipayConfig.notifyUrl = notifyUrl;
    }


}
