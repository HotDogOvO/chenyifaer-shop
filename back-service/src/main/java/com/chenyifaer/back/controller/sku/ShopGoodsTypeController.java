package com.chenyifaer.back.controller.sku;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.back.annotation.LogAnnotation;
import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.constant.LogConstant;
import com.chenyifaer.back.entity.dto.ShopGoodsTypeDTO;
import com.chenyifaer.back.entity.po.ShopGoodsTypePO;
import com.chenyifaer.back.entity.vo.ShopGoodsTypeNameVO;
import com.chenyifaer.back.entity.vo.ShopGoodsTypeVO;
import com.chenyifaer.back.service.ShopGoodsTypeService;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.DateUtil;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
 * 分类管理 - 商品分类表 前端控制器
 * @author wudh
 * @since 2019-04-25
 */

@Slf4j
@RestController
@RequestMapping("/goods/type")
@Api(value = "商品管理",tags = {"商品管理 - 商品分类管理"})
public class ShopGoodsTypeController {
    
    @Autowired
    private ShopGoodsTypeService shopGoodsTypeService;

    @ApiOperation(value = "查询分类列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "当前页条数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "typeName", value = "分类名称", required = false, dataType = "string"),
            @ApiImplicitParam(name = "rank", value = "分类等级", required = false, dataType = "int"),
            @ApiImplicitParam(name = "status", value = "分类状态（0：禁用 1：启用）", required = false, dataType = "int"),
            @ApiImplicitParam(name = "parentTypeName", value = "父分类名称", required = false, dataType = "string"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(@RequestBody @Validated ShopGoodsTypeDTO shopGoodsTypeDTO , BindingResult br) {
        log.debug("【START】 - function ShopGoodsTypeController - list");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopGoodsTypeController - list 参数校验失败");
            return check;
        }
        PageHelper.startPage(shopGoodsTypeDTO.getPageIndex(), shopGoodsTypeDTO.getPageSize());
        List<ShopGoodsTypeVO> list = this.shopGoodsTypeService.getList(shopGoodsTypeDTO);
        PageInfo<ShopGoodsTypeVO> pageList = new PageInfo<>(list);
        log.debug("【END】 - function end ShopGoodsTypeController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, pageList);
    }

    @ApiOperation(value = "查询分类下拉框")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "rank", value = "分类等级", required = true, dataType = "int"),
        @ApiImplicitParam(name = "parentTypeId", value = "父级分类ID", required = false, dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/getTypeName" , method = RequestMethod.POST)
    public JsonResult getTypeName(@RequestBody @Validated ShopGoodsTypeDTO shopGoodsTypeDTO , BindingResult br) {
        log.debug("【START】 - function ShopGoodsTypeController - getTypeName");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopGoodsTypeController - getTypeName 参数校验失败");
            return check;
        }
        List<ShopGoodsTypePO> list = this.shopGoodsTypeService.list(new QueryWrapper<>(
                new ShopGoodsTypePO().setRank(shopGoodsTypeDTO.getRank())
                        .setParentTypeId(shopGoodsTypeDTO.getParentTypeId())));
        List<ShopGoodsTypeNameVO> typeNameList = new ArrayList<>();
        list.stream().filter(
            x -> typeNameList.add(new ShopGoodsTypeNameVO()
                .setTypeName(x.getTypeName())
                .setShopGoodsTypeId(x.getShopGoodsTypeId())
        )).collect(Collectors.toList());
        log.debug("【END】 - function end ShopGoodsTypeController - getTypeName 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, typeNameList);
    }

    @ApiOperation(value = "新增分类")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "typeName", value = "分类名称", required = true, dataType = "string"),
        @ApiImplicitParam(name = "rank", value = "分类等级", required = true, dataType = "int"),
        @ApiImplicitParam(name = "parentTypeId", value = "父分类ID", required = false, dataType = "int"),
    })
    @LogAnnotation(
            menuName = LogConstant.GOODS_TYPE_MENU_NAME,
            action = LogConstant.ADD,
            operation = LogConstant.OPERATION_GOODS_TYPE_ADD)
    @RsaAnnotation
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public JsonResult add(@RequestBody @Validated(ShopGoodsTypeDTO.Add.class) ShopGoodsTypeDTO shopGoodsTypeDTO , BindingResult br) {
        log.debug("【START】 - function ShopGoodsTypeController - add");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopGoodsTypeController - add 参数校验失败");
            return check;
        }

        boolean flag = this.shopGoodsTypeService.save(new ShopGoodsTypePO()
                .setTypeName(shopGoodsTypeDTO.getTypeName())
                .setRank(shopGoodsTypeDTO.getRank())
                .setParentTypeId(shopGoodsTypeDTO.getParentTypeId()));
        if(flag){
            log.debug("【END】 - function ShopGoodsTypeController - add - 新增分类成功 - 新增的数据为：" + shopGoodsTypeDTO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_002);
        }
        log.debug("【END】 - function end ShopGoodsTypeController - add - 新增分类失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10002);
    }

    @ApiOperation(value = "更新分类")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "shopGoodsTypeId", value = "主键", required = true, dataType = "int"),
        @ApiImplicitParam(name = "typeName", value = "分类名称", required = false, dataType = "string"),
        @ApiImplicitParam(name = "rank", value = "分类等级", required = false, dataType = "int"),
        @ApiImplicitParam(name = "parentTypeId", value = "父分类ID", required = false, dataType = "int"),
    })
    @LogAnnotation(
            menuName = LogConstant.GOODS_TYPE_MENU_NAME,
            action = LogConstant.UPDATE,
            operation = LogConstant.OPERATION_GOODS_TYPE_UPDATE)
    @RsaAnnotation
    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    public JsonResult update(@RequestBody @Validated(ShopGoodsTypeDTO.Update.class) ShopGoodsTypeDTO shopGoodsTypeDTO , BindingResult br) {
        log.debug("【START】 - function ShopGoodsTypeController - update");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopGoodsTypeController - update 参数校验失败");
            return check;
        }

        boolean flag = this.shopGoodsTypeService.updateById(new ShopGoodsTypePO()
                .setShopGoodsTypeId(shopGoodsTypeDTO.getShopGoodsTypeId())
                .setTypeName(shopGoodsTypeDTO.getTypeName())
                .setRank(shopGoodsTypeDTO.getRank())
                .setParentTypeId(shopGoodsTypeDTO.getParentTypeId())
                .setUpdateTime(DateUtil.getTime()));
        if(flag){
            log.debug("【END】 - function ShopGoodsTypeController - update - 更新分类成功 - 更新的数据为：" + shopGoodsTypeDTO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_003);
        }
        log.debug("【END】 - function end ShopGoodsTypeController - update - 更新分类失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
    }

    @ApiOperation(value = "禁用/启用分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopGoodsTypeId", value = "主键", required = true, dataType = "int"),
            @ApiImplicitParam(name = "status", value = "状态（0：禁用 1：启用）", required = true, dataType = "int"),
    })
    @LogAnnotation(
            menuName = LogConstant.GOODS_TYPE_MENU_NAME,
            action = LogConstant.UPDATE,
            operation = LogConstant.OPERATION_GOODS_TYPE_UPDATE)
    @RsaAnnotation
    @RequestMapping(value = "/disable" , method = RequestMethod.POST)
    public JsonResult disable(@RequestBody @Validated(ShopGoodsTypeDTO.Disable.class) ShopGoodsTypeDTO shopGoodsTypeDTO , BindingResult br) {
        log.debug("【START】 - function ShopGoodsTypeController - disable");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopGoodsTypeController - disable 参数校验失败");
            return check;
        }

        boolean flag = this.shopGoodsTypeService.updateById(new ShopGoodsTypePO()
                .setShopGoodsTypeId(shopGoodsTypeDTO.getShopGoodsTypeId())
                .setStatus(shopGoodsTypeDTO.getStatus())
                .setUpdateTime(DateUtil.getTime()));
        if(flag){
            log.debug("【END】 - function ShopGoodsTypeController - disable - 禁用/启用分类成功 - 更新的数据为：" + shopGoodsTypeDTO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_003);
        }
        log.debug("【END】 - function end ShopGoodsTypeController - disable - 禁用/启用分类失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
    }

}
