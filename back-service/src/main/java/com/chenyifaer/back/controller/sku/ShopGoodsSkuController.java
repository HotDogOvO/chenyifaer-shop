package com.chenyifaer.back.controller.sku;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.entity.dto.GoodsSkuDTO;
import com.chenyifaer.back.entity.po.ShopGoodsSkuPO;
import com.chenyifaer.back.service.ShopGoodsSkuService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分类管理 - sku商品表 前端控制器
 *
 * @author wudh
 * @since 2019-05-06
 */
@Slf4j
@RestController
@RequestMapping("/goods/goodsSku")
@Api(value = "商品管理", tags = {"商品管理 - 商品SKU管理"})
public class ShopGoodsSkuController {

    @Autowired
    private ShopGoodsSkuService shopGoodsSkuService;

    @ApiOperation(value = "查询商品已有的SKU")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", value = "主键", required = true, dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public JsonResult getList(@RequestBody @Validated(GoodsSkuDTO.Select.class) GoodsSkuDTO goodsSkuDTO, BindingResult br) {
        log.debug("【START】 - function ShopGoodsSkuController - getList");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopGoodsSkuController - getList 参数校验失败");
            return check;
        }
        List<ShopGoodsSkuPO> list = this.shopGoodsSkuService.list(new QueryWrapper<>(
                new ShopGoodsSkuPO().setGoodsId(goodsSkuDTO.getGoodsId())));
        List<String> skuList = new ArrayList<>();

        list.stream().filter(x -> skuList.add(x.getShopSkuId())).collect(Collectors.toList());

        log.debug("【END】 - function end ShopGoodsSkuController - getList 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, skuList);
    }

    @ApiOperation(value = "分配SKU")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", value = "主键", required = true, dataType = "int"),
            @ApiImplicitParam(name = "shopSkuId", value = "SkuID", required = true, dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult add(@RequestBody @Validated(GoodsSkuDTO.Add.class) GoodsSkuDTO goodsSkuDTO, BindingResult br) {
        log.debug("【START】 - function ShopGoodsSkuController - add");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopGoodsSkuController - add 参数校验失败");
            return check;
        }

        //清空当前商品对应的SKU
        this.shopGoodsSkuService.remove(new QueryWrapper<>(new ShopGoodsSkuPO()
                .setGoodsId(goodsSkuDTO.getGoodsId())));

        goodsSkuDTO.getShopSkuIdList().forEach(x -> {
            this.shopGoodsSkuService.save(new ShopGoodsSkuPO()
                    .setGoodsId(goodsSkuDTO.getGoodsId())
                    .setShopSkuId(x));
        });

        log.debug("【END】 - function end ShopGoodsSkuController - add SKU分配成功，新增的结果为：" + goodsSkuDTO);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_002);
    }

}
