package com.chenyifaer.web.controller.orders;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.chenyifaer.web.annotation.RsaAnnotation;
import com.chenyifaer.web.entity.dto.OrdersDTO;
import com.chenyifaer.web.entity.po.ShopOrdersConsigneePO;
import com.chenyifaer.web.entity.po.ShopOrdersPO;
import com.chenyifaer.web.service.ShopOrdersConsigneeService;
import com.chenyifaer.web.service.ShopOrdersService;
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
 * 订单管理 - 订单收货人表 前端控制器
 * @author wudh
 * @since 2019-05-13
 */

@Slf4j
@RestController
@RequestMapping("/orders/consignee")
@Api(value = "订单管理",tags = {"订单管理 - 订单收货人管理"})
public class ShopOrdersConsigneeController {

    @Autowired
    private ShopOrdersConsigneeService shopOrdersConsigneeService;

    @Autowired
    private ShopOrdersService shopOrdersService;

    @ApiOperation(value = "查询收货人")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "flowNumber", value = "订单流水号", dataType = "string"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/getConsignee" , method = RequestMethod.POST)
    public JsonResult getConsignee(@RequestBody @Validated(OrdersDTO.GetConsignee.class) OrdersDTO ordersDTO, BindingResult br) {
        log.debug("【START】 - function ShopOrdersConsigneeController - getConsignee");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopOrdersConsigneeController - getConsignee 参数校验失败");
            return check;
        }
        List<ShopOrdersPO> ordersList = this.shopOrdersService.list(new QueryWrapper<>(
                new ShopOrdersPO().setFlowNumber(ordersDTO.getFlowNumber())));
        //获取当前订单ID
        Integer ordersId = ordersList.get(0).getOrdersId();
        //获取订单收货人信息
        List<ShopOrdersConsigneePO> consigneeList = this.shopOrdersConsigneeService.list(new QueryWrapper<>(
                new ShopOrdersConsigneePO().setOrdersId(ordersId)));

        log.debug("【END】 - function end ShopOrdersConsigneeController - getConsignee，查询订单收货人成功");
        return ResponseResult.Success(ResultCodeEnums.SUCCESS,consigneeList);
    }
    
}
