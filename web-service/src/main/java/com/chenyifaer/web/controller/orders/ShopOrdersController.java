package com.chenyifaer.web.controller.orders;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.chenyifaer.web.annotation.RsaAnnotation;
import com.chenyifaer.web.entity.dto.OrdersDTO;
import com.chenyifaer.web.entity.dto.OrdersGoodsDTO;
import com.chenyifaer.web.entity.po.*;
import com.chenyifaer.web.entity.vo.ConfirmOrdersReturnVO;
import com.chenyifaer.web.enums.PayTypeEnum;
import com.chenyifaer.web.service.*;
import com.chenyifaer.web.util.SystemUtil;
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

import java.math.BigDecimal;
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
 * @since 2019-05-13
 */

@Slf4j
@RestController
@RequestMapping("/orders")
@Api(value = "订单管理",tags = {"订单管理 - 订单管理"})
public class ShopOrdersController {

    @Autowired
    private ShopOrdersService shopOrdersService;

    @Autowired
    private ShopOrdersDetailsService shopOrdersDetailsService;

    @Autowired
    private WebUserAddressService webUserAddressService;

    @Autowired
    private ShopOrdersConsigneeService shopOrdersConsigneeService;

    @Autowired
    private ShopPayService shopPayService;

    @Autowired
    private ShopGoodsService shopGoodsService;

    @ApiOperation(value = "商品下单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "int"),
        @ApiImplicitParam(name = "goodsId", value = "商品ID", dataType = "int"),
        @ApiImplicitParam(name = "goodsSkuId", value = "SKUID", dataType = "int"),
        @ApiImplicitParam(name = "goodsName", value = "商品名", dataType = "string"),
        @ApiImplicitParam(name = "goodsCount", value = "商品数量", dataType = "int"),
        @ApiImplicitParam(name = "goodsPrice", value = "商品价格", dataType = "bigDecimal"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/addOrders" , method = RequestMethod.POST)
    public JsonResult addOrders(@RequestBody @Validated(OrdersDTO.AddOrders.class) OrdersDTO ordersDTO, BindingResult br) {
        log.debug("【START】 - function ShopOrdersController - addOrders");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopOrdersController - addOrders 参数校验失败");
            return check;
        }
        ShopOrdersPO shopOrdersPO = new ShopOrdersPO()
                .setFlowNumber(SystemUtil.getFlowNumber())
                .setUserId(ordersDTO.getUserId())
                .setOrdersName(ordersDTO.getGoodsList().get(0).getGoodsName());

        //新增订单
        boolean flag = this.shopOrdersService.save(shopOrdersPO);
        if(flag){
            //订单商品总价格
            BigDecimal goodsPrice = BigDecimal.valueOf(0);
            //TODO 快递价格
            BigDecimal expressPrice = BigDecimal.valueOf(12);
            //订单商品总数量
            int ordersCount = 0;
            //商品总金额
            BigDecimal goodsTotalPrice = BigDecimal.valueOf(0);
            //新增订单详情
            for(int i=0;i<ordersDTO.getGoodsList().size();i++){
                OrdersGoodsDTO x = ordersDTO.getGoodsList().get(i);
                //计算商品单价
                goodsPrice = goodsPrice.add(x.getGoodsPrice());
                //计算商品总数量
                ordersCount += x.getGoodsCount();
                //计算商品总价
                goodsTotalPrice = goodsPrice.multiply(BigDecimal.valueOf(ordersCount));
                boolean detailFlag = this.shopOrdersDetailsService.save(new ShopOrdersDetailsPO()
                        .setOrdersId(shopOrdersPO.getOrdersId())
                        .setGoodsId(x.getGoodsId())
                        .setGoodsSkuId(x.getGoodsSkuId())
                        .setGoodsPrice(x.getGoodsPrice())
                        .setGoodsCount(x.getGoodsCount())
                        .setGoodsTotalPrice(goodsTotalPrice));
                if(detailFlag == false){
                    log.error("【ERROR】 - function error ShopOrdersController - addOrders，新增订单失败，原因是：新增订单详情失败");
                    //如果新增失败，结束循环
                    return ResponseResult.Fail(ResultCodeEnums.FAIL);
                }
            }
            //更新订单信息
            flag = this.shopOrdersService.updateById(new ShopOrdersPO()
                    .setOrdersId(shopOrdersPO.getOrdersId())
                    .setGoodsPrice(goodsPrice)
                    .setExpressPrice(expressPrice)
                    .setOrdersCount(ordersCount)
                    //订单价格为商品总价格 + 快递价格
                    .setOrdersPrice(goodsTotalPrice.add(expressPrice)));
            //新增支付信息
            this.shopPayService.save(new ShopPayPO()
                    .setOrdersId(shopOrdersPO.getOrdersId())
                    .setUserId(ordersDTO.getUserId())
                    .setPayMoney(goodsTotalPrice.add(expressPrice))
                    .setPayType(PayTypeEnum.APPLY.getCode()));
            if(flag){
                log.debug("【END】 - function end ShopOrdersController - addOrders，新增订单成功");
                return ResponseResult.Success(ResultCodeEnums.SUCCESS,shopOrdersPO.getFlowNumber());
            }
            log.error("【ERROR】 - function error ShopOrdersController - addOrders，新增订单失败，原因是：更新订单信息失败");
            return ResponseResult.Fail(ResultCodeEnums.FAIL);
        }
        log.error("【ERROR】 - function error ShopOrdersController - addOrders，新增订单失败，原因是：新增订单失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL);
    }

    @ApiOperation(value = "确认下单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId", value = "收货地址ID", dataType = "int"),
            @ApiImplicitParam(name = "goodsDetailId", value = "商品详情ID", dataType = "int"),
            @ApiImplicitParam(name = "goodsCount", value = "商品数量", dataType = "int"),
            @ApiImplicitParam(name = "goodsPrice", value = "商品价格", dataType = "bigDecimal"),
            @ApiImplicitParam(name = "integralStatus", value = "是否使用积分", dataType = "int"),
            @ApiImplicitParam(name = "couponsStatus", value = "是否使用优惠券", dataType = "int"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "string"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/confirmOrders" , method = RequestMethod.POST)
    public JsonResult confirmOrders(@RequestBody @Validated(OrdersDTO.ConfirmOrders.class) OrdersDTO ordersDTO, BindingResult br) {
        log.debug("【START】 - function ShopOrdersController - confirmOrders");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopOrdersController - confirmOrders 参数校验失败");
            return check;
        }

        //订单商品总价格
        BigDecimal goodsPrice = BigDecimal.valueOf(0);
        //TODO 快递价格
        BigDecimal expressPrice = BigDecimal.valueOf(12);
        //订单商品总数量
        int ordersCount = 0;
        //订单ID
        int ordersId = 0;
        for(int i=0;i<ordersDTO.getGoodsList().size();i++){
            OrdersGoodsDTO x = ordersDTO.getGoodsList().get(i);
            //重新计算商品价格与商品数量
            goodsPrice = goodsPrice.add(x.getGoodsPrice());
            ordersCount += x.getGoodsCount();

            //获取原来的订单信息
            List<ShopOrdersDetailsPO> list = this.shopOrdersDetailsService.list(new QueryWrapper<>(
                    new ShopOrdersDetailsPO().setOrdersDetailId(x.getGoodsDetailId())));
            ordersId = list.get(0).getOrdersId();
            //判断订单信息是否改变
            list.forEach(k -> {
                //获取商品信息
                List<ShopGoodsPO> goodsList = this.shopGoodsService.list(new QueryWrapper<>(
                        new ShopGoodsPO().setGoodsId(k.getGoodsId())));
                //更新商品信息 库存-1 销量+1
                this.shopGoodsService.updateById(new ShopGoodsPO()
                        .setGoodsId(k.getGoodsId())
                        //销量+1
                        .setGoodsSales(goodsList.get(0).getGoodsSales() + 1)
                        //库存-1
                        .setGoodsInventory(goodsList.get(0).getGoodsInventory() -1));
                if(!x.getGoodsCount().equals(k.getGoodsCount())){
                    //更新订单详情
                    this.shopOrdersDetailsService.updateById(new ShopOrdersDetailsPO()
                            .setOrdersDetailId(x.getGoodsDetailId())
                            .setGoodsCount(x.getGoodsCount())
                            //原有的商品价格 * 新改变的数量
                            .setGoodsTotalPrice(k.getGoodsPrice().multiply(BigDecimal.valueOf(x.getGoodsCount()))));
                }
            });
        }

        //更新订单主表
        boolean flag = this.shopOrdersService.update(new ShopOrdersPO()
                .setGoodsPrice(goodsPrice)
                .setOrdersPrice(goodsPrice.add(expressPrice))
                .setOrdersCount(ordersCount)
                .setIntegralStatus(ordersDTO.getIntegralStatus())
                .setCouponsStatus(ordersDTO.getCouponsStatus()),
                new QueryWrapper<>(new ShopOrdersPO().setFlowNumber(ordersDTO.getFlowNumber())));
        if(flag){
            //获取用户地址信息
            List<WebUserAddressPO> addressList = this.webUserAddressService.list(new QueryWrapper<>(
                    new WebUserAddressPO().setAddressId(ordersDTO.getAddressId())));
            //为了使用Lambda表达式，此处重新给ordersId赋值
            int newOrdersId = ordersId;
            addressList.forEach(x -> {
                //插入收货人表
                this.shopOrdersConsigneeService.save(new ShopOrdersConsigneePO()
                        .setOrdersId(newOrdersId)
                        .setConsigneeName(x.getConsigneeName())
                        .setAddress(x.getAddress())
                        .setArea(x.getArea())
                        .setCity(x.getCity())
                        .setConsigneePhone(x.getConsigneePhone())
                        .setLatitude(x.getLatitude())
                        .setLongitude(x.getLongitude())
                        .setProvince(x.getProvince())
                        .setZip(x.getZip()));
            });
            log.debug("【END】 - function end ShopOrdersController - confirmOrders，确定订单成功");
            return ResponseResult.Success(ResultCodeEnums.SUCCESS,new ConfirmOrdersReturnVO()
                    .setFlowNumber(ordersDTO.getFlowNumber())
                    .setOrdersPrice(goodsPrice.add(expressPrice)));
        }
        log.error("【ERROR】 - function error ShopOrdersController - confirmOrders，确认订单失败，原因是：更新订单主表失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL);
    }

}
