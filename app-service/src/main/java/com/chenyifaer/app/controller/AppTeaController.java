package com.chenyifaer.app.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.app.constant.AppConstant;
import com.chenyifaer.app.entity.dto.TeaDTO;
import com.chenyifaer.app.entity.po.AppTeaPO;
import com.chenyifaer.app.entity.vo.TeaDetailVO;
import com.chenyifaer.app.entity.vo.TeaVO;
import com.chenyifaer.app.service.AppTeaService;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.constant.SystemConstant;
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
 * <p>
 * 小程序端 - 茶话会表 前端控制器
 * </p>
 *
 * @author wudh
 * @since 2019-07-08
 */
@Slf4j
@RestController
@RequestMapping("/tea")
@Api(value = "茶话会管理",tags = {"茶话会管理 - 茶话会管理"})
public class AppTeaController {
    
    @Autowired
    private AppTeaService appTeaService;

    @ApiOperation(value = "获取音乐列表")
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(){
        log.debug("【START】 - function AppTeaController - list");
        List<AppTeaPO> list = this.appTeaService.list(new QueryWrapper<AppTeaPO>()
                .orderByDesc(SystemConstant.CREATE_TIME));
        List<TeaVO> teaList = new ArrayList<>();
        list.stream().filter(x ->
            teaList.add(new TeaVO()
                .setTeaId(x.getTeaId())
                .setTeaName(x.getTeaName())
                .setUrl(x.getUrl())
                .setCreateTime(x.getCreateTime()))
        ).collect(Collectors.toList());
        log.debug("【END】 - function AppTeaController - list - 获取的数据为【{}】",teaList);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS,teaList);
    }

    @ApiOperation(value = "获取音乐列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "teaId", value = "主键", dataType = "int"),
    })
    @RequestMapping(value = "/getDetail" , method = RequestMethod.POST)
    public JsonResult getDetail(@RequestBody @Validated TeaDTO teaDTO , BindingResult br){
        log.debug("【START】 - function AppTeaController - getDetail");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("【ERROR】 - function AppMusicController - getDetail 参数校验失败");
            return check;
        }

        List<AppTeaPO> list = this.appTeaService.list(new QueryWrapper<>(new AppTeaPO().setTeaId(teaDTO.getTeaId())));
        List<TeaDetailVO> teaList = new ArrayList<>();
        list.stream().filter(x ->
            teaList.add(new TeaDetailVO()
                .setTeaName(x.getTeaName())
                .setUrl(x.getUrl())
                .setTeaContent(x.getTeaContent()))
        ).collect(Collectors.toList());
        log.debug("【END】 - function AppTeaController - getDetail - 获取的数据为【{}】",teaList);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS,teaList);
    }

    @ApiOperation(value = "获取首页音乐列表")
    @RequestMapping(value = "/getIndexList" , method = RequestMethod.POST)
    public JsonResult getIndexList(){
        log.debug("【START】 - function AppTeaController - getIndexList");
        //MybatisPlus Sql拼接
        StringBuffer sb = new StringBuffer(AppConstant.LIMIT)
                .append(AppConstant.START_SIZE)
                .append(AppConstant.SPLIT)
                .append(AppConstant.END_SIZE);

        List<AppTeaPO> list = this.appTeaService.list(new QueryWrapper<AppTeaPO>()
                .orderByDesc(SystemConstant.CREATE_TIME).last(sb.toString()));
        List<TeaVO> teaList = new ArrayList<>();
        list.stream().filter(x ->
            teaList.add(new TeaVO()
                .setTeaId(x.getTeaId())
                .setUrl(x.getUrl())
                .setTeaName(x.getTeaName()))
        ).collect(Collectors.toList());
        log.debug("【END】 - function AppTeaController - getIndexList - 获取的数据为【{}】",teaList);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS,teaList);
    }

}
