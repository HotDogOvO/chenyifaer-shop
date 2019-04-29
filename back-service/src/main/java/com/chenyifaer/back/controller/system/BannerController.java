package com.chenyifaer.back.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.back.annotation.LogAnnotation;
import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.config.FilesConfig;
import com.chenyifaer.back.constant.LogConstant;
import com.chenyifaer.back.entity.dto.BannerDTO;
import com.chenyifaer.back.entity.po.BannerPO;
import com.chenyifaer.back.service.BannerService;
import com.chenyifaer.back.util.FileUploadUtil;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 系统管理 - 轮播图管理表 前端控制器
 * @author wudh
 * @since 2019-04-28
 */

@Slf4j
@RestController
@RequestMapping("/system/banner")
@Api(value = "系统管理",tags = {"系统管理 - 轮播图管理"})
public class BannerController {

    @Autowired
    private FilesConfig filesConfig;

    @Autowired
    private BannerService bannerService;

    @ApiOperation(value = "查询轮播图列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "当前页条数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "bannerName", value = "轮播图名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "status", value = "轮播图状态（0：禁用 1：启用）", required = false, paramType = "query", dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(@RequestBody @Validated BannerDTO bannerDTO , BindingResult br) {
        log.debug("【START】 - function BannerController - list");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function BannerController - list 参数校验失败");
            return check;
        }
        PageHelper.startPage(bannerDTO.getPageIndex(), bannerDTO.getPageSize());
        List<BannerPO> list = this.bannerService.list(new QueryWrapper<>(new BannerPO()
                .setBannerName(bannerDTO.getBannerName() == "" ? null : bannerDTO.getBannerName())
                .setStatus(bannerDTO.getStatus())));
        PageInfo<BannerPO> pageList = new PageInfo<>(list);
        log.debug("【END】 - function BannerController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, pageList);
    }

    @ApiOperation(value = "新增轮播图")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "bannerName", value = "轮播图名", required = true, paramType = "query", dataType = "file"),
        @ApiImplicitParam(name = "bannerImageUrl", value = "轮播图路径", required = true, paramType = "query", dataType = "file"),
        @ApiImplicitParam(name = "weight", value = "权重", required = true, paramType = "query", dataType = "file"),
    })
    @RsaAnnotation
    @LogAnnotation(
            menuName = LogConstant.BANNER_MENU_NAME,
            action = LogConstant.ADD,
            operation = LogConstant.OPERATION_BANNER_ADD)
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public JsonResult add(@RequestBody @Validated(BannerDTO.Add.class) BannerDTO bannerDTO , BindingResult br){
        log.debug("【START】 - function BannerController - add");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function BannerController - add 参数校验失败");
            return check;
        }
        boolean flag = this.bannerService.save(new BannerPO()
                .setBannerName(bannerDTO.getBannerName())
                .setBannerImageUrl(bannerDTO.getBannerImageUrl())
                .setWeight(bannerDTO.getWeight()));
        if(flag){
            log.debug("【END】 - function BannerController - add - 新增轮播图成功 - 新增的数据为：" + bannerDTO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_002);
        }
        log.debug("【END】 - function BannerController - add 新增轮播图失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10002);
    }

    @ApiOperation(value = "更新轮播图")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "bannerId", value = "主键", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "bannerName", value = "轮播图名", required = false, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = "bannerImageUrl", value = "轮播图路径", required = false, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = "weight", value = "权重", required = false, paramType = "query", dataType = "int"),
    })
    @RsaAnnotation
    @LogAnnotation(
            menuName = LogConstant.BANNER_MENU_NAME,
            action = LogConstant.UPDATE,
            operation = LogConstant.OPERATION_BANNER_UPDATE)
    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    public JsonResult update(@RequestBody @Validated(BannerDTO.Update.class) BannerDTO bannerDTO , BindingResult br){
        log.debug("【START】 - function BannerController - update");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function BannerController - update 参数校验失败");
            return check;
        }
        boolean flag = this.bannerService.updateById(new BannerPO()
                .setBannerId(bannerDTO.getBannerId())
                .setBannerName(bannerDTO.getBannerName())
                .setBannerImageUrl(bannerDTO.getBannerImageUrl())
                .setWeight(bannerDTO.getWeight())
                .setUpdateTime(DateUtil.getTime()));
        if(flag){
            log.debug("【END】 - function BannerController - update - 更新轮播图成功 - 更新的数据为：" + bannerDTO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_003);
        }
        log.debug("【END】 - function BannerController - update 更新轮播图失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
    }

    @ApiOperation(value = "启用/禁用轮播图")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "bannerId", value = "主键", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "status", value = "状态（0：禁用 1：启用）", required = true, paramType = "query", dataType = "int"),
    })
    @RsaAnnotation
    @LogAnnotation(
            menuName = LogConstant.BANNER_MENU_NAME,
            action = LogConstant.DISABLE,
            operation = LogConstant.OPERATION_BANNER_DISABLE)
    @RequestMapping(value = "/disable" , method = RequestMethod.POST)
    public JsonResult disable(@RequestBody @Validated(BannerDTO.Disable.class) BannerDTO bannerDTO , BindingResult br){
        log.debug("【START】 - function BannerController - disable");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function BannerController - disable 参数校验失败");
            return check;
        }
        boolean flag = this.bannerService.updateById(new BannerPO()
                .setBannerId(bannerDTO.getBannerId())
                .setStatus(bannerDTO.getStatus())
                .setUpdateTime(DateUtil.getTime()));
        if(flag){
            log.debug("【END】 - function BannerController - disable - 启用/禁用轮播图成功 - 更新的数据为：" + bannerDTO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_003);
        }
        log.debug("【END】 - function BannerController - disable 启用/禁用轮播图失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
    }

    @ApiOperation(value = "上传轮播图图片")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "file", value = "图片文件", required = true, paramType = "query", dataType = "file"),
    })
    @RsaAnnotation
    @LogAnnotation(
            menuName = LogConstant.BANNER_MENU_NAME,
            action = LogConstant.UPLOAD,
            operation = LogConstant.OPERATION_BANNER_UPLOAD)
    @RequestMapping(value = "/upload" , method = RequestMethod.POST)
    public JsonResult upload(@RequestPart("file") MultipartFile file){
        log.debug("【START】 - function upload");
        JsonResult jsonResult = FileUploadUtil.upload(file,filesConfig.getFormalPath());
        log.debug("【END】 - function upload");
        return jsonResult;
    }

}
