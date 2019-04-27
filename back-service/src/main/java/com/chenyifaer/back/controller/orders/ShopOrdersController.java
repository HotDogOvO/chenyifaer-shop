package com.chenyifaer.back.controller.orders;


import com.chenyifaer.back.annotation.LogAnnotation;
import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.constant.LogConstant;
import com.chenyifaer.back.entity.dto.OrdersDTO;
import com.chenyifaer.back.entity.vo.OrdersVO;
import com.chenyifaer.back.service.ShopOrdersService;
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
 * 订单管理 - 订单表 前端控制器
 * @author wudh
 * @since 2019-04-25
 */

@Slf4j
@RestController
@RequestMapping("/orders")
@Api(value = "订单管理",tags = {"订单管理 - 订单管理"})
public class ShopOrdersController {

    @Autowired
    private ShopOrdersService shopOrdersService;

    @ApiOperation(value = "查询订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "当前页条数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "flowNumber", value = "订单流水号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "expressNumber", value = "快递单号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "payFlowNumber", value = "支付流水号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "ordersPrice", value = "订单价格", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "status", value = "状态 （0：待付款 1：付款完毕，待发货 2：已发货，待收货 3：已收货，待完成\n" +
                    " 4：已完成，待评论 7：已取消\n" +
                    " 8： 超时关闭 9：已关闭 99：退货）", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "integralStatus", value = "是否积分支付 （0：否 1：是）", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "couponsStatus", value = "是否优惠券支付 （0：否 1：是）", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "ordersName", value = "订单名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "consigneeName", value = "收货人姓名", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "string"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(@RequestBody @Validated OrdersDTO ordersDTO , BindingResult br){
        log.debug("【START】 - function ShopOrdersController - list");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("【ERROR】 - function ShopOrdersController - list 参数校验失败");
            return check;
        }
        PageHelper.startPage(ordersDTO.getPageIndex(),ordersDTO.getPageSize());
        List<OrdersVO> list = this.shopOrdersService.getList(ordersDTO);
        PageInfo<OrdersVO> pageList = new PageInfo<>(list);
        log.debug("【END】 - function ShopOrdersController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001,pageList);
    }

    @ApiOperation(value = "导出订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "flowNumber", value = "订单流水号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "expressNumber", value = "快递单号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "payFlowNumber", value = "支付流水号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "ordersPrice", value = "订单价格", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "status", value = "状态 （0：待付款 1：付款完毕，待发货 2：已发货，待收货 3：已收货，待完成\n" +
                    " 4：已完成，待评论 7：已取消\n" +
                    " 8： 超时关闭 9：已关闭 99：退货）", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "integralStatus", value = "是否积分支付 （0：否 1：是）", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "couponsStatus", value = "是否优惠券支付 （0：否 1：是）", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "ordersName", value = "订单名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "consigneeName", value = "收货人姓名", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "string"),
    })
    @LogAnnotation(
            menuName = LogConstant.ORDERS_MENU_NAME,
            action = LogConstant.EXPORT,
            operation = LogConstant.OPERATION_ORDERS_EXPORT)
    @RsaAnnotation
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void export(@RequestBody @Validated OrdersDTO ordersDTO, HttpServletResponse response) {
        log.debug("【START】 - function ShopOrdersController - export");
        this.shopOrdersService.export(ordersDTO, response);
        log.debug("【END】 - function ShopOrdersController - export");
    }

}
