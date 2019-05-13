package com.chenyifaer.web.controller.orders;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.chenyifaer.web.annotation.RsaAnnotation;
import com.chenyifaer.web.entity.dto.OrdersDetailDTO;
import com.chenyifaer.web.entity.po.ShopOrdersPO;
import com.chenyifaer.web.entity.vo.OrdersDetailReturnVO;
import com.chenyifaer.web.entity.vo.OrdersDetailVO;
import com.chenyifaer.web.service.ShopOrdersDetailsService;
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

import java.util.ArrayList;
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
 * @since 2019-05-13
 */

@Slf4j
@RestController
@RequestMapping("/orders/detail")
@Api(value = "订单管理",tags = {"订单管理 - 订单管理"})
public class ShopOrdersDetailsController {

    @Autowired
    private ShopOrdersDetailsService shopOrdersDetailsService;

    @Autowired
    private ShopOrdersService shopOrdersService;

    @ApiOperation(value = "订单详情页 - 根据订单流水号查询商品")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "flowNumber", value = "流水号", dataType = "bigDecimal"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/getOrdersDetail" , method = RequestMethod.POST)
    public JsonResult getOrdersDetail(@RequestBody @Validated(OrdersDetailDTO.GetOrdersDetail.class) OrdersDetailDTO ordersDetailDTO, BindingResult br) {
        log.debug("【START】 - function ShopOrdersDetailsController - getOrdersDetail");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopOrdersDetailsController - getOrdersDetail 参数校验失败");
            return check;
        }
        List<OrdersDetailVO> list = this.shopOrdersDetailsService.getOrdersDetail(ordersDetailDTO);
        //获取订单总价
        List<ShopOrdersPO> ordersList = this.shopOrdersService.list(new QueryWrapper<>(new ShopOrdersPO().setFlowNumber(ordersDetailDTO.getFlowNumber())));
        //接口返回值
        List<OrdersDetailReturnVO> returnList = new ArrayList<>();
        returnList.add(new OrdersDetailReturnVO()
                .setOrdersDetailList(list)
                .setOrdersPrice(ordersList.get(0).getOrdersPrice()));

        log.debug("【END】 - function end ShopOrdersDetailsController - getOrdersDetail，查询订单详情成功");
        return ResponseResult.Success(ResultCodeEnums.SUCCESS, returnList);
    }

}
