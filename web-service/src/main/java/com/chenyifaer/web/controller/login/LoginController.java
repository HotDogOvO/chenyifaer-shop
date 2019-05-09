package com.chenyifaer.web.controller.login;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.constant.SystemConstant;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.chenyifaer.web.annotation.RsaAnnotation;
import com.chenyifaer.web.entity.dto.UserDTO;
import com.chenyifaer.web.entity.po.WebUserDetailPO;
import com.chenyifaer.web.entity.po.WebUserPO;
import com.chenyifaer.web.entity.po.WebUserRolePO;
import com.chenyifaer.web.entity.vo.LoginUserVO;
import com.chenyifaer.web.enums.UserStatusEnum;
import com.chenyifaer.web.enums.UserTypeEnum;
import com.chenyifaer.web.service.WebUserDetailService;
import com.chenyifaer.web.service.WebUserRoleService;
import com.chenyifaer.web.service.WebUserService;
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
 * 登录
 * @Author:wudh
 * @Date: 2019/5/9 18:46
 */
@Slf4j
@RestController
@RequestMapping("/sys")
@Api(value = "登录", tags = {"登录 - 登录"})
public class LoginController {

    @Autowired
    private WebUserService webUserService;

    @Autowired
    private WebUserDetailService webUserDetailService;

    @Autowired
    private WebUserRoleService webUserRoleService;

    @ApiOperation(value = "用户注册")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userAccount", value = "用户名", dataType = "string"),
        @ApiImplicitParam(name = "userPassword", value = "密码", dataType = "string"),
        @ApiImplicitParam(name = "userValidPassword", value = "验证密码", dataType = "string"),
        @ApiImplicitParam(name = "userType", value = "用户渠道（1：注册 2：微信用户 3：QQ用户 4：支付宝用户 5：微博用户）", dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JsonResult register(@RequestBody @Validated(UserDTO.Register.class) UserDTO userDTO, BindingResult br) {
        log.debug("【START】 - function WebUserController - register ");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function WebUserController - register 参数校验失败");
            return check;
        }
        //如果密码与验证密码不同，则返回失败
        if (!userDTO.getUserPassword().equals(userDTO.getUserValidPassword())) {
            log.error("【ERROR】 - function WebUserController - register 密码验证失败");
            return ResponseResult.Fail(ResultCodeEnums.FAIL_11003);
        }

        //验证用户名是否存在
        int count = this.webUserService.count(new QueryWrapper<>(new WebUserPO().setUserAccount(userDTO.getUserAccount())));
        if(count > 0){
            log.error("【ERROR】 - function WebUserController - register 用户名【{}】已经存在" , userDTO.getUserAccount());
            return ResponseResult.Fail(ResultCodeEnums.FAIL_11005);
        }

        WebUserPO userPO = new WebUserPO()
                .setUserAccount(userDTO.getUserAccount())
                .setUserPassword(userDTO.getUserPassword())
                //插入默认昵称
                .setUserNickName(SystemConstant.DEFAULT_USER_NICK_NAME);
        //若用户来源为空，则默认为注册用户
        if (userDTO.getUserType() == null) {
            userPO.setUserType(UserTypeEnum.REGISTER.getCode());
        }

        boolean flag = this.webUserService.save(userPO);
        if (flag) {
            //插入用户详情
            flag = this.webUserDetailService.save(new WebUserDetailPO()
                    .setUserId(userPO.getUserId())
                    //插入默认头像
                    .setUserHeadImage(SystemConstant.DEFAULT_USER_HEAD_URL));
            if (flag) {
                //默认角色为游客
                flag = this.webUserRoleService.save(new WebUserRolePO()
                        .setUserId(userPO.getUserId())
                        .setRoleId(SystemConstant.DEFAULT_USER_ROLE_ID));
                if(flag){
                    log.debug("【END】 - function WebUserController - register 注册成功，注册用户为" + userDTO);
                    return ResponseResult.Success(ResultCodeEnums.SUCCESS_008);
                }
                log.error("【ERROR】 - function WebUserController - register 注册失败，原因是：插入用户角色失败");
                return ResponseResult.Fail(ResultCodeEnums.FAIL_10008);
            }
            log.error("【ERROR】 - function WebUserController - register 注册失败，原因是：插入用户详情表失败");
            return ResponseResult.Fail(ResultCodeEnums.FAIL_10008);
        }
        log.error("【ERROR】 - function WebUserController - register 注册失败，原因是：插入用户表失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10008);
    }

    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAccount", value = "用户名", dataType = "string"),
            @ApiImplicitParam(name = "userPassword", value = "密码", dataType = "string"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login(@RequestBody @Validated(UserDTO.Login.class) UserDTO userDTO, BindingResult br) {
        log.debug("【START】 - function WebUserController - login ");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function WebUserController - login 参数校验失败");
            return check;
        }

        int count = this.webUserService.count(new QueryWrapper<>(new WebUserPO()
                .setUserAccount(userDTO.getUserAccount())
                .setUserPassword(userDTO.getUserPassword())));
        if(count == 0){
            log.error("【ERROR】 - function WebUserController - login 登录失败，原因是：用户名或密码错误，当前登录用户为【{}】",userDTO.getUserAccount() );
            return ResponseResult.Fail(ResultCodeEnums.FAIL_11001);
        }

        List<LoginUserVO> list = this.webUserService.getLoginUser(new UserDTO().setUserAccount(userDTO.getUserAccount()));
        //取得用户状态
        Integer status = list.get(0).getStatus();
        //如果用户状态为0，则表示用户被禁用
        if(status.equals(UserStatusEnum.DISABLE.getCode())){
            log.error("【ERROR】 - function WebUserController - login 登录失败，原因是：用户被禁用，当前登录用户为【{}】",userDTO.getUserAccount() );
            return ResponseResult.Fail(ResultCodeEnums.FAIL_11002);
        }
        //如果用户状态为9，则表示用户已注销
        if(status.equals(UserStatusEnum.CANCELL.getCode())){
            log.error("【ERROR】 - function WebUserController - login 登录失败，原因是：用户已注销，当前登录用户为【{}】",userDTO.getUserAccount() );
            return ResponseResult.Fail(ResultCodeEnums.FAIL_11004);
        }

        log.debug("【END】 - function WebUserController - register 登录成功，登录用户为，当前登录用户为【{}】",userDTO.getUserAccount() );
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_LOGIN,list);
    }

}
