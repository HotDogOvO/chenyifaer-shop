package com.chenyifaer.web.controller.goods;


import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.chenyifaer.web.annotation.RsaAnnotation;
import com.chenyifaer.web.entity.dto.GoodsDTO;
import com.chenyifaer.web.entity.vo.*;
import com.chenyifaer.web.service.ShopGoodsService;
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
 * 商品管理 - 商品表 前端控制器
 * @author wudh
 * @since 2019-05-07
 */

@Slf4j
@RestController
@RequestMapping("/goods")
@Api(value = "商品管理",tags = {"商品管理 - 商品管理"})
public class ShopGoodsController {

    @Autowired
    private ShopGoodsService shopGoodsService;

    @ApiOperation(value = "首页 - 根据分类查询商品")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "parentTypeId", value = "分类ID", dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/getGoodsByType" , method = RequestMethod.POST)
    public JsonResult getGoodsByType(@RequestBody @Validated(GoodsDTO.getGoodsByType.class) GoodsDTO goodsDTO, BindingResult br) {
        log.debug("【START】 - function ShopGoodsController - getGoodsByType");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopGoodsController - getGoodsByType 参数校验失败");
            return check;
        }
        List<GoodsByTypeVO> list = this.shopGoodsService.getGoodsByType(goodsDTO);
        log.debug("【END】 - function end ShopGoodsController - getGoodsByType，查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, list);
    }

    @ApiOperation(value = "首页 - 根据分类查询推荐商品")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "parentTypeId", value = "分类ID", dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/getRecommendGoodsByType" , method = RequestMethod.POST)
    public JsonResult getRecommendGoodsByType(@RequestBody @Validated(GoodsDTO.getGoodsByType.class) GoodsDTO goodsDTO, BindingResult br) {
        log.debug("【START】 - function ShopGoodsController - getRecommendGoodsByType");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopGoodsController - getRecommendGoodsByType 参数校验失败");
            return check;
        }
        List<GoodsByTypeVO> list = this.shopGoodsService.getRecommendGoodsByType(goodsDTO);
        log.debug("【END】 - function end ShopGoodsController - getRecommendGoodsByType，查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, list);
    }

    @ApiOperation(value = "首页 - 查询首页推荐商品")
    @RsaAnnotation
    @RequestMapping(value = "/getRecommendGoods" , method = RequestMethod.POST)
    public JsonResult getRecommendGoods() {
        log.debug("【START】 - function ShopGoodsController - getRecommendGoods");

        List<GoodsRecommendedVO> list = this.shopGoodsService.getRecommendedList();

        log.debug("【END】 - function end ShopGoodsController - getRecommendGoods");
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, list);
    }

    @ApiOperation(value = "首页 - 查询首页支持积分商品")
    @RsaAnnotation
    @RequestMapping(value = "/getIntegralGoods" , method = RequestMethod.POST)
    public JsonResult getIntegralGoods() {
        log.debug("【START】 - function ShopGoodsController - getIntegralGoods");

        List<GoodsIntegralVO> list = this.shopGoodsService.getIntegralList();

        log.debug("【END】 - function end ShopGoodsController - getIntegralGoods");
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, list);
    }

    @ApiOperation(value = "首页 - 查询首页支持优惠券商品")
    @RsaAnnotation
    @RequestMapping(value = "/getCouponsGoods" , method = RequestMethod.POST)
    public JsonResult getCouponsGoods() {
        log.debug("【START】 - function ShopGoodsController - getCouponsGoods");

        List<GoodsCouponsVO> list = this.shopGoodsService.getCouponsList();

        log.debug("【END】 - function end ShopGoodsController - getCouponsGoods");
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, list);
    }

    @ApiOperation(value = "商品详情页 - 根据商品ID查询商品详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "goodsId", value = "分类ID", dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/getDetail" , method = RequestMethod.POST)
    public JsonResult getDetail(@RequestBody @Validated(GoodsDTO.getDetail.class) GoodsDTO goodsDTO, BindingResult br) {
        log.debug("【START】 - function ShopGoodsController - getDetail");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopGoodsController - getDetail 商品详情参数校验失败");
            return check;
        }
        List<GoodsDetailReturnVO> list = this.shopGoodsService.getDetail(goodsDTO);
        log.debug("【END】 - function end ShopGoodsController - getDetail，查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, list);
    }

    @ApiOperation(value = "商品详情页 - 根据销量查询商品")
    @RsaAnnotation
    @RequestMapping(value = "/getGoodsBySales" , method = RequestMethod.POST)
    public JsonResult getGoodsBySales() {
        log.debug("【START】 - function ShopGoodsController - getGoodsBySales");
        List<GoodsSalesVO> list = this.shopGoodsService.getGoodsBySales();
        log.debug("【END】 - function end ShopGoodsController - getGoodsBySales，查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, list);
    }

    @ApiOperation(value = "商品详情页 - 根据SKU查询商品")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "shopSkuId", value = "SKUID", dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/getGoodsBySku" , method = RequestMethod.POST)
    public JsonResult getGoodsBySku(@RequestBody @Validated(GoodsDTO.getGoodsBySku.class) GoodsDTO goodsDTO,BindingResult br) {
        log.debug("【START】 - function ShopGoodsController - getGoodsBySku");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopGoodsController - getGoodsBySku 参数校验失败");
            return check;
        }
        List<GoodsBySkuVO> list = this.shopGoodsService.getGoodsBySku(goodsDTO);
        log.debug("【END】 - function end ShopGoodsController - getGoodsBySku，查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, list);
    }
}
