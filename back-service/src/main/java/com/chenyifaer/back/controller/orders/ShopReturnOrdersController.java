package com.chenyifaer.back.controller.orders;


import com.chenyifaer.back.annotation.LogAnnotation;
import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.constant.LogConstant;
import com.chenyifaer.back.controller.BaseController;
import com.chenyifaer.back.entity.dto.ReturnOrdersDTO;
import com.chenyifaer.back.entity.po.ShopReturnOrdersPO;
import com.chenyifaer.back.entity.vo.ReturnOrdersDetailVO;
import com.chenyifaer.back.entity.vo.ReturnOrdersVO;
import com.chenyifaer.back.enums.ReturnOrdersStatusEnum;
import com.chenyifaer.back.service.ShopReturnOrdersService;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.DateUtil;
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
 * 订单管理 - 退单表 前端控制器
 * @author wudh
 * @since 2019-04-26
 */

@Slf4j
@RestController
@RequestMapping("/orders/return")
@Api(value = "退单管理",tags = {"退单管理 - 退单管理"})
public class ShopReturnOrdersController extends BaseController {
    
    @Autowired
    private ShopReturnOrdersService shopReturnOrdersService;

    @ApiOperation(value = "查询退单列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页码", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "当前页条数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "returnFlowNumber", value = "退单流水号", required = false, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = "returnType", value = "退單類型（1：退貨退款 2：僅退款）", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "goodsType", value = "商品狀態（1：已收到貨 2：未收到貨）", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "status", value = "状态（0：待审核 1：审核通过 2：退货中 3：退货成功 4：退货失败）", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "goodsName", value = "商品名稱", required = false, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = "userNickName", value = "購買用戶名", required = false, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = "flowNumber", value = "訂單流水號", required = false, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = "startTime", value = "起始时间", required = false, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "string"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(@RequestBody @Validated ReturnOrdersDTO returnOrdersDTO , BindingResult br){
        log.debug("【START】 - function ShopReturnOrdersController - list");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("【ERROR】 - function ShopReturnOrdersController - list 参数校验失败");
            return check;
        }
        PageHelper.startPage(returnOrdersDTO.getPageIndex(),returnOrdersDTO.getPageSize());
        List<ReturnOrdersVO> list = this.shopReturnOrdersService.getList(returnOrdersDTO);
        PageInfo<ReturnOrdersVO> pageList = new PageInfo<>(list);
        log.debug("【END】 - function ShopReturnOrdersController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001,pageList);
    }

    @ApiOperation(value = "查询退单详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "returnOrdersId", value = "主键", required = true, paramType = "query", dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/getDetail" , method = RequestMethod.POST)
    public JsonResult getDetail(@RequestBody @Validated(ReturnOrdersDTO.getDetail.class) ReturnOrdersDTO returnOrdersDTO , BindingResult br){
        log.debug("【START】 - function ShopReturnOrdersController - getDetail");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("【ERROR】 - function ShopReturnOrdersController - getDetail 参数校验失败");
            return check;
        }
        List<ReturnOrdersDetailVO> list = this.shopReturnOrdersService.getDetail(returnOrdersDTO);
        log.debug("【END】 - function ShopReturnOrdersController - getDetail 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001,list);
    }

    @ApiOperation(value = "审核退单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "returnOrdersId", value = "主键", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "status", value = "状态（0：待审核 1：审核通过 2：退货中 3：退货成功 4：退货失败）", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "checkFailRemark", value = "审核失败原因", required = false, paramType = "query", dataType = "int"),
    })
    @LogAnnotation(
            menuName = LogConstant.RETURN_ORDERS_MENU_NAME,
            action = LogConstant.CHECK,
            operation = LogConstant.OPERATION_RETURN_ORDERS_CHECK)
    @RsaAnnotation
    @RequestMapping(value = "/check" , method = RequestMethod.POST)
    public JsonResult check(@RequestBody @Validated(ReturnOrdersDTO.check.class) ReturnOrdersDTO returnOrdersDTO , BindingResult br){
        log.debug("【START】 - function ShopReturnOrdersController - check");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("【ERROR】 - function ShopReturnOrdersController - check 参数校验失败");
            return check;
        }

        ShopReturnOrdersPO shopReturnOrdersPO = new ShopReturnOrdersPO()
                .setReturnOrdersId(returnOrdersDTO.getReturnOrdersId())
                .setStatus(Integer.parseInt(returnOrdersDTO.getStatus()))
                .setCheckUserId(this.getUserId());

        if(returnOrdersDTO.getStatus().equals(ReturnOrdersStatusEnum.CHECK_SUCCESS.getCode())){
            shopReturnOrdersPO.setCheckTime(DateUtil.getTime());
        }else if(returnOrdersDTO.getStatus().equals(ReturnOrdersStatusEnum.RETURN_GOODS_FAIL.getCode())){
            shopReturnOrdersPO.setCheckFailTime(DateUtil.getTime())
                    .setCheckFailRemark(returnOrdersDTO.getCheckFailRemark());
        }

        boolean flag = this.shopReturnOrdersService.updateById(shopReturnOrdersPO);

        if(flag){
            log.debug("【END】 - function ShopReturnOrdersController - check 审核退单成功，审核的数据为：" + shopReturnOrdersPO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_007);
        }

        log.debug("【END】 - function ShopReturnOrdersController - check 审核失败");
        return ResponseResult.Success(ResultCodeEnums.FAIL_10007);
    }

    @ApiOperation(value = "导出退单列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "returnFlowNumber", value = "退单流水号", required = false, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = "returnType", value = "退單類型（1：退貨退款 2：僅退款）", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "goodsType", value = "商品狀態（1：已收到貨 2：未收到貨）", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "status", value = "状态（0：待审核 1：审核通过 2：退货中 3：退货成功 4：退货失败）", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "goodsName", value = "商品名稱", required = false, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = "userNickName", value = "購買用戶名", required = false, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = "flowNumber", value = "訂單流水號", required = false, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = "startTime", value = "起始时间", required = false, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "string"),
    })
    @LogAnnotation(
            menuName = LogConstant.RETURN_ORDERS_MENU_NAME,
            action = LogConstant.EXPORT,
            operation = LogConstant.OPERATION_RETURN_ORDERS_EXPORT)
    @RsaAnnotation
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void export(@RequestBody @Validated ReturnOrdersDTO returnOrdersDTO, HttpServletResponse response) {
        log.debug("【START】 - function ShopReturnOrdersController - export");
        this.shopReturnOrdersService.export(returnOrdersDTO, response);
        log.debug("【END】 - function ShopReturnOrdersController - export");
    }

}
