package com.chenyifaer.back.controller.pay;


import com.chenyifaer.back.annotation.LogAnnotation;
import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.constant.LogConstant;
import com.chenyifaer.back.entity.dto.PayReturnDTO;
import com.chenyifaer.back.entity.vo.PayReturnVO;
import com.chenyifaer.back.service.ShopPayReturnService;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
 * 支付管理 - 退款表 前端控制器
 * @author wudh
 * @since 2019-04-27
 */
@Slf4j
@RestController
@RequestMapping("/payReturn")
@Api(value = "退款管理",tags = {"退款管理 - 退款管理"})
public class ShopPayReturnController {

    @Autowired
    private ShopPayReturnService shopPayReturnService;

    @ApiOperation(value = "查询退款列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "当前页条数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "flowNumber", value = "订单流水号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "payFlowNumber", value = "支付流水号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "returnFlowNumber", value = "退款流水号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "ordersName", value = "订单名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "userNickName", value = "用户昵称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "returnType", value = "退款类型（1：支付宝支付 2：微信支付）", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "status", value = "状态（0：退款中 1：退款成功 2：退款失败）", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "string"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(@RequestBody @Validated PayReturnDTO payReturnDTO , BindingResult br){
        log.debug("【START】 - function ShopPayReturnController - list");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("【ERROR】 - function ShopPayReturnController - list 参数校验失败");
            return check;
        }
        PageHelper.startPage(payReturnDTO.getPageIndex(),payReturnDTO.getPageSize());
        List<PayReturnVO> list = this.shopPayReturnService.getList(payReturnDTO);
        PageInfo<PayReturnVO> pageList = new PageInfo<>(list);
        log.debug("【END】 - function ShopPayReturnController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001,pageList);
    }

    @ApiOperation(value = "导出支付列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "flowNumber", value = "订单流水号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "payFlowNumber", value = "支付流水号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "returnFlowNumber", value = "退款流水号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "ordersName", value = "订单名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "userNickName", value = "用户昵称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "returnType", value = "退款类型（1：支付宝支付 2：微信支付）", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "status", value = "状态（0：退款中 1：退款成功 2：退款失败）", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "string"),
    })
    @LogAnnotation(
            menuName = LogConstant.PAY_RETURN_MENU_NAME,
            action = LogConstant.EXPORT,
            operation = LogConstant.OPERATION_PAY_RETURN_EXPORT)
    @RsaAnnotation
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void export(@RequestBody @Validated PayReturnDTO payReturnDTO, HttpServletResponse response) {
        log.debug("【START】 - function ShopPayReturnController - export");
        this.shopPayReturnService.export(payReturnDTO, response);
        log.debug("【END】 - function ShopPayReturnController - export");
    }

}
