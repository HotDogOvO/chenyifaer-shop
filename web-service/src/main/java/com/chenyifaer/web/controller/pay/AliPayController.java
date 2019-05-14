package com.chenyifaer.web.controller.pay;

import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.chenyifaer.web.annotation.RsaAnnotation;
import com.chenyifaer.web.entity.dto.PayDTO;
import com.chenyifaer.web.hanlder.AliPayHanlder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

@Slf4j
@RestController
@RequestMapping("/aliPay")
@Api(value = "支付对接", tags = {"支付对接 - 支付宝对接"})
public class AliPayController {

    @Autowired
    private AliPayHanlder alipayHanlder;

    @ApiOperation(value = "支付宝支付")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "flowNumber", value = "订单流水号", dataType = "string"),
        @ApiImplicitParam(name = "ordersPrice", value = "订单价格", dataType = "bigDecimal"),
        @ApiImplicitParam(name = "ordersName", value = "订单名称", dataType = "string"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public JsonResult pay(@RequestBody @Validated(PayDTO.Pay.class) PayDTO payDTO, BindingResult br) {
        log.debug("【START】 - function AliPayController - pay ");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function AliPayController - pay 参数校验失败");
            return check;
        }

        String responseFrom;
        //进入支付接口
        try {
            responseFrom = alipayHanlder.aliPayPay(payDTO.getFlowNumber(),payDTO.getOrdersPrice(),payDTO.getOrdersName());
        } catch (ServletException e) {
            e.printStackTrace();
            log.error("【ERROR】 - function AliPayController - pay 支付请求失败，原因是：{}",e);
            return ResponseResult.Fail(ResultCodeEnums.FAIL);
        } catch (IOException e) {
            log.error("【ERROR】 - function AliPayController - pay 支付请求失败，原因是：{}",e);
            e.printStackTrace();
            return ResponseResult.Fail(ResultCodeEnums.FAIL);
        }
        log.error("【END】 - function AliPayController - pay 支付请求成功");
        return ResponseResult.Success(ResultCodeEnums.SUCCESS,responseFrom);
    }

}
