package com.chenyifaer.back.controller.orders;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.back.annotation.LogAnnotation;
import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.constant.LogConstant;
import com.chenyifaer.back.entity.dto.OrdersConsigneeDTO;
import com.chenyifaer.back.entity.po.ShopOrdersConsigneePO;
import com.chenyifaer.back.service.ShopOrdersConsigneeService;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.ResponseResult;
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
 * @since 2019-04-25
 */

@Slf4j
@RestController
@RequestMapping("/ordersConsignee")
public class ShopOrdersConsigneeController {

    @Autowired
    private ShopOrdersConsigneeService shopOrdersConsigneeService;

    @ApiOperation(value = "修改订单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页码", required = true, paramType = "query", dataType = "int"),
    })
    @LogAnnotation(
            menuName = LogConstant.ORDERS_MENU_NAME,
            action = LogConstant.UPDATE,
            operation = LogConstant.OPERATION_ORDERS_UPDATE)
    @RsaAnnotation
    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    public JsonResult update(@RequestBody @Validated OrdersConsigneeDTO ordersConsigneeDTO , BindingResult br){
        log.debug("【START】 - function ShopOrdersConsigneeController - update");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("【ERROR】 - function ShopOrdersConsigneeController - update 参数校验失败");
            return check;
        }

        boolean flag = this.shopOrdersConsigneeService.update(new ShopOrdersConsigneePO()
                //插入更新数据
                .setConsigneeName(ordersConsigneeDTO.getConsigneeName())
                .setConsigneePhone(ordersConsigneeDTO.getConsigneePhone())
                .setAddress(ordersConsigneeDTO.getAddress())
                .setArea(ordersConsigneeDTO.getArea())
                .setCity(ordersConsigneeDTO.getCity())
                .setProvince(ordersConsigneeDTO.getProvince()),
                    //创建更新条件
                    new QueryWrapper<>(new ShopOrdersConsigneePO()
                        .setOrdersId(ordersConsigneeDTO.getOrdersId())));
        if(flag){
            log.debug("【END】 - function ShopOrdersConsigneeController - update , 更新订单成功");
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_003);
        }
        log.debug("【END】 - function ShopOrdersConsigneeController - update 更新订单失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
    }

}
