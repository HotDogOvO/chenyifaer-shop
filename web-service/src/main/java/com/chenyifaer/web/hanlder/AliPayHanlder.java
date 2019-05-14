package com.chenyifaer.web.hanlder;
/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.chenyifaer.basic.common.constant.AlipayConstant;
import com.chenyifaer.web.config.AlipayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * 支付宝对接 - 工具类
 * @Author:wudh
 * @Date: 2019/5/13 23:32
 */

@Slf4j
@Service
public class AliPayHanlder {

    /** AppId */
    public final String appId = AlipayConfig.appid;
    /** 请求网关 */
    public final String gateWay = AlipayConfig.gateway;
    /** 编码格式 */
    public final String charSet = AlipayConfig.charset;
    /** 签名格式 */
    public final String signType = AlipayConfig.signtype;
    /** 接口版本 */
    public final String version = AlipayConfig.version;
    /** 应用私钥 */
    public final String privateKey = AlipayConfig.privateKey;
    /** 支付宝公钥 */
    public final String aliPayPublicKey = AlipayConfig.alipayPublicKey;

    /**
     * 支付宝下单 - 进入支付
     * @Author:wudh
     * @Date: 2019/5/13 23:34
     */
    public String aliPayPay(String flowNumber, BigDecimal ordersPrice,String ordersName) throws ServletException, IOException {
        log.debug("【START】 - function AliPayHanlder - aliPayPay");
        //创建支付宝请求
        AlipayClient alipayClient = new DefaultAlipayClient(
            gateWay,appId,privateKey,AlipayConstant.FORMAT,charSet,aliPayPublicKey,signType
        );
        log.debug("【RUN】 - function AliPayHanlder - aliPayPay - 支付宝请求 - 【AlipayClient】 - 创建成功");
        //支付宝支付接口 - 请求参数
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        //订单流水号
        model.setOutTradeNo(flowNumber);
        //订单金额 - 由于支付宝只支持String 所以此处转为String
        model.setTotalAmount(ordersPrice.toString());
        //销售产品码
        model.setProductCode(AlipayConstant.PRODUCT_CODE);
        //订单名
        model.setSubject(ordersName);
        log.debug("【RUN】 - function AliPayHanlder - aliPayPay - 支付宝请求参数 -【model】为："+model.toString());

        //支付宝请求体
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //回调地址
        request.setReturnUrl("http://www.chenyifaer67373.com");
        //异步回调地址
        request.setNotifyUrl("http://www.chenyifaer67373.com");
        //请求参数
        request.setBizModel(model);
        log.debug("【RUN】 - function AliPayHanlder - aliPayPay - 支付宝请求体 - 【AlipayTradePagePayRequest】 - 创建成功");

        String form = "";
        try {
            //支付宝请求响应体
            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
            log.debug("【RUN】 - function AliPayHanlder - aliPayPay - 支付宝请求响应体 - 【AlipayTradePagePayResponse】 - 创建成功");
            if(response.isSuccess()){
                form = response.getBody();
                log.debug("【RUN】 - function AliPayHanlder - aliPayPay - 支付宝请求下单接口调用成功");
            } else {
                log.error("【ERROR】 - function AliPayHanlder - aliPayPay - 支付宝请求下单接口调用失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.debug("【RUN】 - function AliPayHanlder - aliPayPay - 支付宝请求下单接口调用异常，原因是：{}",e);
        }
        log.debug("【END】 - function end AliPayHanlder - aliPayPay");
        return form;
    }


}
