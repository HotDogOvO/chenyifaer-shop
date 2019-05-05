package com.chenyifaer.back.controller.sku;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.back.annotation.LogAnnotation;
import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.constant.LogConstant;
import com.chenyifaer.back.entity.dto.SkuKeyDTO;
import com.chenyifaer.back.entity.po.ShopSkuKeyPO;
import com.chenyifaer.back.entity.po.ShopSkuPO;
import com.chenyifaer.back.entity.vo.SkuKeyVO;
import com.chenyifaer.back.service.ShopSkuKeyService;
import com.chenyifaer.back.service.ShopSkuService;
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
 * 分类管理 - sku分类表 前端控制器
 * @author wudh
 * @since 2019-04-30
 */

@Slf4j
@RestController
@RequestMapping("/goods/skuKey")
@Api(value = "SKU管理",tags = {"SKU管理 - 商品SKU管理"})
public class ShopSkuKeyController {

    @Autowired
    private ShopSkuService shopSkuService;

    @Autowired
    private ShopSkuKeyService shopSkuKeyService;

    @ApiOperation(value = "查询分类列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页码", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "当前页条数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "keyName", value = "key值", required = false, dataType = "string"),
        @ApiImplicitParam(name = "valueName", value = "value值", required = false, dataType = "string"),
        @ApiImplicitParam(name = "startTime", value = "起始时间", required = false, dataType = "string"),
        @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, dataType = "string"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(@RequestBody @Validated SkuKeyDTO skuKeyDTO , BindingResult br) {
        log.debug("【START】 - function ShopSkuController - list");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopSkuController - list 参数校验失败");
            return check;
        }
        List<SkuKeyVO> list = this.shopSkuKeyService.getList(skuKeyDTO);
        log.debug("【END】 - function end ShopSkuController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, list);
    }

    @ApiOperation(value = "新增Key")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "keyName", value = "key值", required = true, dataType = "string"),
        @ApiImplicitParam(name = "valueIdList", value = "Value主键集合", required = true, dataType = "list"),
    })
    @RsaAnnotation
    @LogAnnotation(
            menuName = LogConstant.GOODS_SKU_KEY_MENU_NAME,
            action = LogConstant.ADD,
            operation = LogConstant.OPERATION_SKU_KEY_ADD)
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public JsonResult add(@RequestBody @Validated(SkuKeyDTO.Add.class) SkuKeyDTO skuKeyDTO , BindingResult br) {
        log.debug("【START】 - function ShopSkuController - add");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopSkuController - add 参数校验失败");
            return check;
        }
        ShopSkuKeyPO shopSkuKeyPO = new ShopSkuKeyPO().setKeyName(skuKeyDTO.getKeyName());
        //插入Key表
        boolean flag = this.shopSkuKeyService.save(shopSkuKeyPO);
        if(flag){
            //插入中间表
            skuKeyDTO.getValueIdList().forEach(x -> {
                this.shopSkuService.save(new ShopSkuPO()
                        .setSkuKeyId(shopSkuKeyPO.getShopSkuKeyId())
                        .setSkuValueId(x));
            });
            log.debug("【END】 - function end ShopSkuController - add 新增sku-key成功，插入的数据为：" + skuKeyDTO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_002);
        }
        log.debug("【END】 - function end ShopSkuController - add 新增Key失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10002);
    }

    @ApiOperation(value = "更新Key")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "skuKeyId", value = "主键", required = true, dataType = "int"),
        @ApiImplicitParam(name = "keyName", value = "key值", required = false, dataType = "string"),
        @ApiImplicitParam(name = "valueIdList", value = "Value主键集合", required = false, dataType = "list"),
    })
    @RsaAnnotation
    @LogAnnotation(
            menuName = LogConstant.GOODS_SKU_KEY_MENU_NAME,
            action = LogConstant.UPDATE,
            operation = LogConstant.OPERATION_SKU_KEY_UPDATE)
    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    public JsonResult update(@RequestBody @Validated(SkuKeyDTO.Update.class) SkuKeyDTO skuKeyDTO , BindingResult br) {
        log.debug("【START】 - function ShopSkuController - update");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopSkuController - update 参数校验失败");
            return check;
        }
        //插入Key表
        boolean flag = this.shopSkuKeyService.updateById(new ShopSkuKeyPO()
                .setShopSkuKeyId(skuKeyDTO.getSkuKeyId())
                .setKeyName(skuKeyDTO.getKeyName()));
        if(flag){
            //清空中间表
            flag = this.shopSkuService.remove(new QueryWrapper<>(
                    new ShopSkuPO().setSkuKeyId(skuKeyDTO.getSkuKeyId())));
            if(flag){
                //插入中间表
                skuKeyDTO.getValueIdList().forEach(x -> {
                    this.shopSkuService.save(new ShopSkuPO()
                            .setSkuKeyId(skuKeyDTO.getSkuKeyId())
                            .setSkuValueId(x));
                });
                log.debug("【END】 - function end ShopSkuController - update 更新sku-key成功，更新的数据为：" + skuKeyDTO);
                return ResponseResult.Success(ResultCodeEnums.SUCCESS_003);
            }
            log.debug("【END】 - function end ShopSkuController - update 更新Key失败 - 清空中间表失败");
            return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
        }
        log.debug("【END】 - function end ShopSkuController - update 更新Key失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
    }

    @ApiOperation(value = "删除SKU")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "skuKeyId", value = "主键", required = true, dataType = "int"),
    })
    @RsaAnnotation
    @LogAnnotation(
            menuName = LogConstant.GOODS_SKU_KEY_MENU_NAME,
            action = LogConstant.DELETE,
            operation = LogConstant.OPERATION_SKU_KEY_DELETE)
    @RequestMapping(value = "/delete" , method = RequestMethod.POST)
    public JsonResult delete(@RequestBody @Validated(SkuKeyDTO.Delete.class) SkuKeyDTO skuKeyDTO , BindingResult br) {
        log.debug("【START】 - function ShopSkuController - delete");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopSkuController - delete 参数校验失败");
            return check;
        }

        //删除key表
        boolean flag = this.shopSkuKeyService.removeById(skuKeyDTO.getSkuKeyId());
        if(flag){
            //删除中间表
            flag = this.shopSkuService.remove(new QueryWrapper<>(
                    new ShopSkuPO().setSkuKeyId(skuKeyDTO.getSkuKeyId())));
            if(flag){
                log.debug("【END】 - function end ShopSkuController - delete 删除成功，删除的ID为：" + skuKeyDTO.getSkuKeyId());
                return ResponseResult.Success(ResultCodeEnums.SUCCESS_004);
            }
            log.debug("【END】 - function end ShopSkuController - delete 删除sku中间表失败，删除的ID为：" + skuKeyDTO.getSkuKeyId());
            return ResponseResult.Fail(ResultCodeEnums.FAIL_10004);
        }
        log.debug("【END】 - function end ShopSkuController - delete 删除sku失败，删除的ID为：" + skuKeyDTO.getSkuKeyId());
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10004);
    }

}
