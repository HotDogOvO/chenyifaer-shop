package com.chenyifaer.web.controller.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.constant.SystemConstant;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.chenyifaer.web.entity.dto.UserWeiboDTO;
import com.chenyifaer.web.entity.dto.WeiboUserDTO;
import com.chenyifaer.web.entity.po.WebUserPO;
import com.chenyifaer.web.entity.po.WebUserRolePO;
import com.chenyifaer.web.entity.po.WebUserWeiboPO;
import com.chenyifaer.web.entity.vo.LoginUserVO;
import com.chenyifaer.web.enums.UserTypeEnum;
import com.chenyifaer.web.hanlder.WeiboHanlder;
import com.chenyifaer.web.service.WebUserRoleService;
import com.chenyifaer.web.service.WebUserService;
import com.chenyifaer.web.service.WebUserWeiboService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 前台账号管理 - 微博用户信息表 前端控制器
 * </p>
 *
 * @author wudh
 * @since 2019-07-30
 */
@Slf4j
@RestController
@RequestMapping("/user/weibo")
@Api(value = "登录", tags = {"登录 - 微博登录"})
public class WebUserWeiboController {

    @Autowired
    private WebUserService webUserService;

    @Autowired
    private WebUserWeiboService webUserWeiboService;

    @Autowired
    private WebUserRoleService webUserRoleService;

    @Autowired
    private WeiboHanlder weiboHanlder;

    @ApiOperation(value = "登录 - 微博登录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code", value = "code", dataType = "string"),
    })
    @RequestMapping(value = "/weiboLogin" , method = RequestMethod.POST)
    public JsonResult weiboLogin(@RequestBody UserWeiboDTO userWeiboDTO) {
        log.debug("【START】 - function WebUserWeiboController - weiboLogin");
        //获取用户token & uid
        Map<String,Object> map = weiboHanlder.getAccessToken(userWeiboDTO.getCode());
        String accessToken = map.get("accessToken").toString();
        String uid = map.get("uid").toString();
        log.debug("【RUN】 - function WebUserWeiboController - weiboLogin - 获取的token为：【{}】，获取的uid为：【{}】",accessToken,uid);
        //判断用户是否已经存在
        int count = this.webUserWeiboService.count(new QueryWrapper<>(new WebUserWeiboPO().setUid(uid)));
        //大于0表示已经存在了，直接放行
        if(count > 0){
            List<LoginUserVO> list = this.webUserWeiboService.getWeiboLoginUser(new UserWeiboDTO().setUid(uid)) ;
            log.debug("【END】 - function WebUserWeiboController - weiboLogin - 该用户已经存在了，直接放行 - 用户uid为：【{}】",uid);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS,list);
        }

        //获取用户信息
        WeiboUserDTO weiboUserDTO = weiboHanlder.getUserDetail(accessToken,uid);
        log.debug("【RUN】 - function WebUserWeiboController - weiboLogin - 获取的用户信息为：【{}】",weiboUserDTO);
        //插入用户主表
        WebUserPO webUserPO = new WebUserPO()
                .setUserAccount(UUID.randomUUID().toString().replaceAll("\\-",""))
                .setUserPassword(UUID.randomUUID().toString().replaceAll("\\-",""))
                .setUserNickName(weiboUserDTO.getScreen_name())
                .setUserType(UserTypeEnum.WEIBO.getCode());
        boolean flag = this.webUserService.save(webUserPO);
        if(flag){
            //插入微博用户表
            flag = this.webUserWeiboService.save(new WebUserWeiboPO()
                    .setUserId(webUserPO.getUserId())
                    .setUid(uid)
                    .setScreenName(weiboUserDTO.getScreen_name())
                    .setProvinceId(weiboUserDTO.getProvince())
                    .setCityId(weiboUserDTO.getCity())
                    .setLocation(weiboUserDTO.getLocation())
                    .setDescription(weiboUserDTO.getDescription())
                    .setWeiboHeadImage(weiboUserDTO.getProfile_image_url())
                    .setWeiboSex((weiboUserDTO.getGender().equals(SystemConstant.SYSTEM_PARAM_BOY_CODE) ? 1 : 2)));
            if(flag){
                //分配角色
                flag = this.webUserRoleService.save(new WebUserRolePO()
                        .setUserId(webUserPO.getUserId())
                        .setRoleId(SystemConstant.DEFAULT_USER_ROLE_ID));
                if(flag){
                    //登录成功，返回成功信息
                    LoginUserVO loginUserVO = new LoginUserVO();
                    loginUserVO.setUserId(webUserPO.getUserId())
                            .setStatus(webUserPO.getStatus())
                            .setUserNickName(weiboUserDTO.getScreen_name())
                            .setUserHeadImage(weiboUserDTO.getProfile_image_url())
                            .setRoleName(SystemConstant.DEFAULT_USER_NICK_NAME);
                    List<LoginUserVO> list = new ArrayList<>();
                    list.add(loginUserVO);
                    log.debug("【END】 - function WebUserWeiboController - weiboLogin");
                    return ResponseResult.Success(ResultCodeEnums.SUCCESS,list);
                }
            }
        }
        log.debug("【ERROR】 - function WebUserWeiboController - weiboLogin");
        return ResponseResult.Fail(ResultCodeEnums.FAIL);
    }

}
