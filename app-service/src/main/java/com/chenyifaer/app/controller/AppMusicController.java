package com.chenyifaer.app.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.app.constant.AppConstant;
import com.chenyifaer.app.entity.dto.MusicDTO;
import com.chenyifaer.app.entity.po.AppMusicPO;
import com.chenyifaer.app.entity.vo.MusicDetailVO;
import com.chenyifaer.app.entity.vo.MusicVO;
import com.chenyifaer.app.service.AppMusicService;
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
 * 小程序端 - 新歌发布表 前端控制器
 * </p>
 *
 * @author wudh
 * @since 2019-07-08
 */
@Slf4j
@RestController
@RequestMapping("/music")
@Api(value = "歌曲管理",tags = {"歌曲管理 - 歌曲管理"})
public class AppMusicController {

    @Autowired
    private AppMusicService appMusicService;

    @ApiOperation(value = "获取音乐列表")
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(){
        log.debug("【START】 - function AppMusicController - list");
        List<AppMusicPO> list = this.appMusicService.list(new QueryWrapper<AppMusicPO>()
                .orderByDesc(SystemConstant.CREATE_TIME));
        List<MusicVO> musicList = new ArrayList<>();
        list.stream().filter(x ->
            musicList.add(new MusicVO()
                .setMusicName(x.getMusicName())
                .setMusicId(x.getMusicId())
                .setMusicImgUrl(x.getMusicImgUrl()))
        ).collect(Collectors.toList());
        log.debug("【END】 - function AppMusicController - list - 获取的数据为【{}】",musicList);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS,musicList);
    }

    @ApiOperation(value = "获取音乐详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "musicId", value = "主键", dataType = "int"),
    })
    @RequestMapping(value = "/getDetail" , method = RequestMethod.POST)
    public JsonResult getDetail(@RequestBody @Validated MusicDTO musicDTO, BindingResult br){
        log.debug("【START】 - function AppMusicController - getDetail");

        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("【ERROR】 - function AppMusicController - getDetail 参数校验失败");
            return check;
        }

        List<AppMusicPO> list = this.appMusicService.list(new QueryWrapper<>(new AppMusicPO()
                .setMusicId(musicDTO.getMusicId())));
        List<MusicDetailVO> musicList = new ArrayList<>();
        list.stream().filter(x ->
            musicList.add(new MusicDetailVO()
                .setMusicName(x.getMusicName())
                .setMusicAuthor(x.getMusicAuthor())
                .setMusicContent(x.getMusicContent())
                .setMusicImgUrl(x.getMusicImgUrl())
                .setMusicUrl(x.getMusicUrl())
                .setCreateTime(x.getCreateTime()))
        ).collect(Collectors.toList());

        log.debug("【END】 - function AppMusicController - getDetail - 获取的数据为【{}】",musicList);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS,musicList);
    }

    @ApiOperation(value = "获取首页音乐列表")
    @RequestMapping(value = "/getIndexList" , method = RequestMethod.POST)
    public JsonResult getIndexList(){
        log.debug("【START】 - function AppMusicController - getIndexList");
        //MybatisPlus Sql拼接
        StringBuffer sb = new StringBuffer(AppConstant.LIMIT)
                .append(AppConstant.START_SIZE)
                .append(AppConstant.SPLIT)
                .append(AppConstant.MUSIC_END_SIZE);
        List<AppMusicPO> list = this.appMusicService.list(new QueryWrapper<AppMusicPO>()
                .orderByDesc(SystemConstant.CREATE_TIME).last(sb.toString()));
        List<MusicVO> musicList = new ArrayList<>();
        list.stream().filter(x ->
            musicList.add(new MusicVO()
                .setMusicId(x.getMusicId())
                .setMusicName(x.getMusicName())
                .setMusicAuthor(x.getMusicAuthor())
                .setMusicImgUrl(x.getMusicImgUrl()))
        ).collect(Collectors.toList());
        log.debug("【END】 - function AppMusicController - getIndexList - 获取的数据为【{}】",musicList);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS,musicList);
    }
}
