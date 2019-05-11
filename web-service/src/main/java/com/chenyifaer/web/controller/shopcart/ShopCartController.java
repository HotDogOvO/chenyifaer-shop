package com.chenyifaer.web.controller.shopcart;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.chenyifaer.web.annotation.RsaAnnotation;
import com.chenyifaer.web.entity.dto.ShopCartDTO;
import com.chenyifaer.web.entity.po.ShopCartPO;
import com.chenyifaer.web.entity.vo.ShopCartVO;
import com.chenyifaer.web.service.ShopCartService;
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
 * 商品管理 - 购物车表 前端控制器
 * @author wudh
 * @since 2019-05-11
 */

@Slf4j
@RestController
@RequestMapping("/shopcart")
@Api(value = "购物车管理",tags = {"购物车管理 - 购物车管理"})
public class ShopCartController {

    @Autowired
    private ShopCartService shopCartService;

    @ApiOperation(value = "查询购物车")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(@RequestBody @Validated(ShopCartDTO.Select.class) ShopCartDTO shopCartDTO, BindingResult br) {
        log.debug("【START】 - function ShopCartController - list");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopCartController - list 参数校验失败");
            return check;
        }
        List<ShopCartVO> list = this.shopCartService.getList(shopCartDTO);

        log.debug("【END】 - function end ShopCartController - list，查询购物车成功，查询的数据为："+list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001,list);
    }

    @ApiOperation(value = "新增购物车")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "int"),
        @ApiImplicitParam(name = "goodsId", value = "商品ID", dataType = "int"),
        @ApiImplicitParam(name = "shopSkuId", value = "商品SKU - ID", dataType = "int"),
        @ApiImplicitParam(name = "goodsCount", value = "商品数量", dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public JsonResult add(@RequestBody @Validated(ShopCartDTO.Add.class) ShopCartDTO shopCartDTO, BindingResult br) {
        log.debug("【START】 - function ShopCartController - add");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopCartController - add 参数校验失败");
            return check;
        }

        //查询当前购物车是否存在
        int count = this.shopCartService.count(new QueryWrapper<>(new ShopCartPO()
                .setGoodsId(shopCartDTO.getGoodsId())
                .setUserId(shopCartDTO.getUserId())
                .setGoodsShopcartId(shopCartDTO.getShopSkuId())));
        if(count > 0){
            log.error("【END】 - function end ShopCartController - add，新增购物车失败，原因是：当前商品已存在");
            return ResponseResult.Fail(ResultCodeEnums.FAIL_11006);
        }

        //插入数据库
        boolean flag = this.shopCartService.save(new ShopCartPO()
                .setUserId(shopCartDTO.getUserId())
                .setGoodsId(shopCartDTO.getGoodsId())
                .setGoodsSkuId(shopCartDTO.getShopSkuId())
                .setCount(shopCartDTO.getGoodsCount()));
        if(flag){
            log.debug("【END】 - function end ShopCartController - add，新增购物车成功，插入的数据为：" + shopCartDTO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_002);
        }
        log.error("【END】 - function end ShopCartController - add，新增购物车失败，原因是：插入数据库失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10002);
    }

    @ApiOperation(value = "更新购物车")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "shopCartId", value = "主键", dataType = "int"),
        @ApiImplicitParam(name = "shopSkuId", value = "SKUID", dataType = "int"),
        @ApiImplicitParam(name = "count", value = "数量", dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    public JsonResult update(@RequestBody @Validated(ShopCartDTO.Update.class) ShopCartDTO shopCartDTO, BindingResult br) {
        log.debug("【START】 - function ShopCartController - update");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopCartController - update 参数校验失败");
            return check;
        }
        boolean flag = this.shopCartService.updateById(new ShopCartPO()
                .setGoodsShopcartId(shopCartDTO.getShopCartId())
                .setGoodsSkuId(shopCartDTO.getShopSkuId())
                .setCount(shopCartDTO.getGoodsCount()));
        if(flag){
            log.debug("【END】 - function end ShopCartController - update，更新购物车成功，更新的数据为：" + shopCartDTO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_004);
        }
        log.error("【END】 - function end ShopCartController - update，更新购物车失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10004);
    }

    @ApiOperation(value = "删除购物车")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "shopCartId", value = "主键", dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/delete" , method = RequestMethod.POST)
    public JsonResult delete(@RequestBody @Validated(ShopCartDTO.Delete.class) ShopCartDTO shopCartDTO, BindingResult br) {
        log.debug("【START】 - function ShopCartController - delete");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopCartController - delete 参数校验失败");
            return check;
        }
        boolean flag = this.shopCartService.removeById(shopCartDTO.getShopCartId());
        if(flag){
            log.debug("【END】 - function end ShopCartController - delete，删除购物车成功，删除的数据为：" + shopCartDTO.getShopCartId());
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_004);
        }
        log.error("【END】 - function end ShopCartController - delete，删除购物车失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10004);
    }
}
