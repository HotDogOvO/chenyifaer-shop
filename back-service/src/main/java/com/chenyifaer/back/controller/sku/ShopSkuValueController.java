package com.chenyifaer.back.controller.sku;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.back.annotation.LogAnnotation;
import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.constant.LogConstant;
import com.chenyifaer.back.entity.dto.SkuValueDTO;
import com.chenyifaer.back.entity.po.ShopSkuPO;
import com.chenyifaer.back.entity.po.ShopSkuValuePO;
import com.chenyifaer.back.entity.vo.SkuValueTreeVO;
import com.chenyifaer.back.entity.vo.SkuValueVO;
import com.chenyifaer.back.service.ShopSkuService;
import com.chenyifaer.back.service.ShopSkuValueService;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
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

import java.util.List;

/**
 * 分类管理 - sku属性表 前端控制器
 * @author wudh
 * @since 2019-04-30
 */
@Slf4j
@RestController
@RequestMapping("/goods/skuValue")
@Api(value = "SKU管理",tags = {"SKU管理 - 商品SKU管理"})
public class ShopSkuValueController {

    @Autowired
    private ShopSkuValueService shopSkuValueService;

    @Autowired
    private ShopSkuService shopSkuService;

    @ApiOperation(value = "查询属性列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页码", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "当前页条数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "valueName", value = "value值", required = false, dataType = "string"),
        @ApiImplicitParam(name = "startTime", value = "起始时间", required = false, dataType = "string"),
        @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, dataType = "string"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(@RequestBody @Validated SkuValueDTO skuValueDTO, BindingResult br) {
        log.debug("【START】 - function ShopSkuValueController - list");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopSkuValueController - list 参数校验失败");
            return check;
        }
        PageHelper.startPage(skuValueDTO.getPageIndex(), skuValueDTO.getPageSize());
        List<SkuValueVO> list = this.shopSkuValueService.getList(skuValueDTO);
        PageInfo<SkuValueVO> pageList = new PageInfo<>(list);
        log.debug("【END】 - function end ShopSkuValueController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, pageList);
    }

    @ApiOperation(value = "查询属性树状图")
    @RsaAnnotation
    @RequestMapping(value = "/getTreeList" , method = RequestMethod.POST)
    public JsonResult getTreeList() {
        log.debug("【START】 - function ShopSkuValueController - getTreeList");
        List<SkuValueTreeVO> list = this.shopSkuValueService.getTreeList();
        log.debug("【END】 - function end ShopSkuValueController - getTreeList 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, list);
    }

    @ApiOperation(value = "新增属性")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "valueName", value = "value值", required = true, dataType = "string"),
        @ApiImplicitParam(name = "shopGoodsTypeId", value = "分类ID", required = true, dataType = "int"),
    })
    @RsaAnnotation
    @LogAnnotation(
            menuName = LogConstant.GOODS_SKU_VALUE_MENU_NAME,
            action = LogConstant.ADD,
            operation = LogConstant.OPERATION_SKU_VALUE_ADD)
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public JsonResult add(@RequestBody @Validated(SkuValueDTO.Add.class) SkuValueDTO skuValueDTO, BindingResult br) {
        log.debug("【START】 - function ShopSkuValueController - add");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopSkuValueController - add 参数校验失败");
            return check;
        }
        boolean flag = this.shopSkuValueService.save(new ShopSkuValuePO()
                .setValueName(skuValueDTO.getValueName())
                .setShopGoodsTypeId(skuValueDTO.getShopGoodsTypeId()));
        if(flag){
            log.debug("【END】 - function end ShopSkuValueController - add 新增sku-value成功，插入的数据为：" + skuValueDTO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_002);
        }
        log.debug("【END】 - function end ShopSkuValueController - add 新增sku-value失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10002);
    }

    @ApiOperation(value = "更新属性")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "skuValueId", value = "主键", required = true, dataType = "int"),
        @ApiImplicitParam(name = "valueName", value = "value值", required = false, dataType = "string"),
        @ApiImplicitParam(name = "shopGoodsTypeId", value = "分类ID", required = false, dataType = "int"),
    })
    @RsaAnnotation
    @LogAnnotation(
            menuName = LogConstant.GOODS_SKU_VALUE_MENU_NAME,
            action = LogConstant.ADD,
            operation = LogConstant.OPERATION_SKU_VALUE_UPDATE)
    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    public JsonResult update(@RequestBody @Validated(SkuValueDTO.Update.class) SkuValueDTO skuValueDTO, BindingResult br) {
        log.debug("【START】 - function ShopSkuValueController - update");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopSkuValueController - update 参数校验失败");
            return check;
        }
        boolean flag = this.shopSkuValueService.updateById(new ShopSkuValuePO()
                .setShopSkuValueId(skuValueDTO.getSkuValueId())
                .setValueName(skuValueDTO.getValueName())
                .setShopGoodsTypeId(skuValueDTO.getShopGoodsTypeId()));
        if(flag){
            log.debug("【END】 - function end ShopSkuValueController - update 更新sku-value成功，更新的数据为：" + skuValueDTO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_003);
        }
        log.debug("【END】 - function end ShopSkuValueController - update 更新sku-value失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
    }

    @ApiOperation(value = "删除属性")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "skuValueId", value = "主键", required = true, dataType = "int"),
    })
    @RsaAnnotation
    @LogAnnotation(
            menuName = LogConstant.GOODS_SKU_VALUE_MENU_NAME,
            action = LogConstant.ADD,
            operation = LogConstant.OPERATION_SKU_VALUE_DELETE)
    @RequestMapping(value = "/delete" , method = RequestMethod.POST)
    public JsonResult delete(@RequestBody @Validated(SkuValueDTO.Delete.class) SkuValueDTO skuValueDTO, BindingResult br) {
        log.debug("【START】 - function ShopSkuValueController - delete");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopSkuValueController - delete 参数校验失败");
            return check;
        }
        //删除主表
        boolean flag = this.shopSkuValueService.removeById(skuValueDTO.getSkuValueId());
        if(flag){
            //删除中间表
            flag = this.shopSkuService.remove(new QueryWrapper<>(new ShopSkuPO().setSkuValueId(skuValueDTO.getSkuValueId())));
            if(flag){
                log.debug("【END】 - function end ShopSkuValueController - delete 删除属性成功，删除的数据为：" + skuValueDTO.getSkuValueId());
                return ResponseResult.Success(ResultCodeEnums.SUCCESS_004);
            }
            log.debug("【END】 - function end ShopSkuValueController - delete 删除属性中间表失败，删除的数据为：" + skuValueDTO.getSkuValueId());
            return ResponseResult.Fail(ResultCodeEnums.FAIL_10004);
        }
        log.debug("【END】 - function end ShopSkuValueController - delete 删除属性失败，删除的数据为：" + skuValueDTO.getSkuValueId());
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10004);
    }

}
