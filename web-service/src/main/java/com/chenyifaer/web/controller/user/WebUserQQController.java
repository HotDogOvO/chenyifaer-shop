package com.chenyifaer.web.controller.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.constant.SystemConstant;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.chenyifaer.web.entity.dto.QQUserDTO;
import com.chenyifaer.web.entity.dto.UserQQDTO;
import com.chenyifaer.web.entity.po.WebUserPO;
import com.chenyifaer.web.entity.po.WebUserQQPO;
import com.chenyifaer.web.entity.po.WebUserRolePO;
import com.chenyifaer.web.entity.vo.LoginUserVO;
import com.chenyifaer.web.enums.UserTypeEnum;
import com.chenyifaer.web.hanlder.QQHanlder;
import com.chenyifaer.web.service.WebUserQQService;
import com.chenyifaer.web.service.WebUserRoleService;
import com.chenyifaer.web.service.WebUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 前台账号管理 - QQ用户信息表 前端控制器
 * </p>
 *
 * @author wudh
 * @since 2019-07-29
 */
@Slf4j
@RestController
@RequestMapping("/user/qq")
@Api(value = "登录", tags = {"登录 - QQ登录"})
public class WebUserQQController {

    @Autowired
    private QQHanlder qqHanlder;

    @Autowired
    private WebUserService webUserService;

    @Autowired
    private WebUserQQService webUserQQService;

    @Autowired
    private WebUserRoleService webUserRoleService;

    @ApiOperation(value = "登录 - QQ登录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code", value = "code", dataType = "string"),
    })
    @RequestMapping("/qqLogin")
    public JsonResult qqLogin(@RequestBody UserQQDTO userQQDTO){
        log.debug("【START】 - function WebUserQQController - qqLogin");
        //获取AccessToken
        String accessToken = qqHanlder.getAccessToken(userQQDTO.getCode());
        //获取openId
        String openId = qqHanlder.getOpenId(accessToken);
        //查询该用户是否已经存在了，若存在，则直接放行
        int count = this.webUserQQService.count(new QueryWrapper<>(new WebUserQQPO().setOpenId(openId)));
        if(count > 0){
            List<LoginUserVO> list = this.webUserQQService.getQQLoginUser(new UserQQDTO().setOpenId(openId));
            log.debug("【END】 - function WebUserQQController - qqLogin - 该用户已经存在了，直接放行 - 用户OpenId为：【{}】",openId);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS,list);
        }
        //获取用户详情
        QQUserDTO qqUserDTO = qqHanlder.getUserDetail(accessToken,openId);
        //插入用户表
        WebUserPO webUserPO = new WebUserPO()
                .setUserAccount(UUID.randomUUID().toString().replaceAll("\\-",""))
                .setUserPassword(UUID.randomUUID().toString().replaceAll("\\-",""))
                .setUserNickName(qqUserDTO.getNickname())
                .setUserType(UserTypeEnum.QQ.getCode());
        boolean flag = this.webUserService.save(webUserPO);
        if(flag){
            //插入QQ用户表
            flag = this.webUserQQService.save(new WebUserQQPO()
                    .setUserId(webUserPO.getUserId())
                    .setOpenId(openId)
                    .setQqYear(qqUserDTO.getYear())
                    .setQqProvince(qqUserDTO.getProvince())
                    .setQqCity(qqUserDTO.getCity())
                    .setQqConstellation(qqUserDTO.getConstellation())
                    .setQqHeadImage(qqUserDTO.getFigureurl_qq())
                    .setQqSex(qqUserDTO.getGender().equals(SystemConstant.SYSTEM_PARAM_BOY) ? 1 : 2));
            if(flag){
                flag = this.webUserRoleService.save(new WebUserRolePO()
                        .setUserId(webUserPO.getUserId())
                        .setRoleId(SystemConstant.DEFAULT_USER_ROLE_ID));
                if(flag){
                    LoginUserVO loginUserVO = new LoginUserVO();
                    loginUserVO.setUserId(webUserPO.getUserId())
                            .setStatus(webUserPO.getStatus())
                            .setUserNickName(qqUserDTO.getNickname())
                            .setUserHeadImage(qqUserDTO.getFigureurl_qq())
                            .setRoleName(SystemConstant.DEFAULT_USER_NICK_NAME);
                    List<LoginUserVO> list = new ArrayList<>();
                    list.add(loginUserVO);
                    log.debug("【END】 - function WebUserQQController - qqLogin");
                    return ResponseResult.Success(ResultCodeEnums.SUCCESS,list);
                }
            }
        }
        log.error("【ERROR】 - function WebUserQQController - qqLogin");
        return ResponseResult.Fail(ResultCodeEnums.FAIL);
    }
}
