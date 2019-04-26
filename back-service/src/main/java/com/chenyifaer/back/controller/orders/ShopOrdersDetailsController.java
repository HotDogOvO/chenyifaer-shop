package com.chenyifaer.back.controller.orders;


import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.entity.dto.OrdersDetailDTO;
import com.chenyifaer.back.entity.vo.OrdersDetailVO;
import com.chenyifaer.back.service.ShopOrdersDetailsService;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.ResponseResult;
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
 * 订单管理 - 订单详情表 前端控制器
 * @author wudh
 * @since 2019-04-25
 */

@Slf4j
@RestController
@RequestMapping("/ordersDetail")
@Api(value = "订单管理",tags = {"订单管理 - 订单管理"})
public class ShopOrdersDetailsController {
    
    @Autowired
    private ShopOrdersDetailsService shopOrdersDetailsService;

    @ApiOperation(value = "查询订单详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ordersId", value = "主键", required = true, paramType = "query", dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/getDetail" , method = RequestMethod.POST)
    public JsonResult getDetail(@RequestBody @Validated OrdersDetailDTO ordersDetailDTO , BindingResult br){
        log.debug("【START】 - function ShopOrdersDetailsController - list");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("【ERROR】 - function ShopOrdersDetailsController - list 参数校验失败");
            return check;
        }
        List<OrdersDetailVO> list = this.shopOrdersDetailsService.getOrdersDetail(ordersDetailDTO);
        log.debug("【END】 - function ShopOrdersDetailsController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001,list);
    }
    
}
