package com.chenyifaer.web.controller.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.FileUploadUrlEnum;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.DateUtil;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.chenyifaer.web.annotation.RsaAnnotation;
import com.chenyifaer.web.config.FilesConfig;
import com.chenyifaer.web.constant.EmailConstant;
import com.chenyifaer.web.entity.dto.EmailDTO;
import com.chenyifaer.web.entity.dto.UserDTO;
import com.chenyifaer.web.entity.po.WebUserDetailPO;
import com.chenyifaer.web.entity.po.WebUserPO;
import com.chenyifaer.web.entity.vo.UserVO;
import com.chenyifaer.web.rabbitMq.send.SendService;
import com.chenyifaer.web.redis.RedisService;
import com.chenyifaer.web.service.WebUserDetailService;
import com.chenyifaer.web.service.WebUserService;
import com.chenyifaer.web.util.FileUploadUtil;
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
 * _____ _            __     ___ ______                ________ ____ ______ ____
 * / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 * | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 * | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 * | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 * \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 */

/**
 * 前台账号管理 - 前台用户表 前端控制器
 * @author wudh
 * @since 2019-05-07
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "用户管理", tags = {"用户管理 - 用户管理"})
public class WebUserController {
    
    @Autowired
    private WebUserService webUserService;

    @Autowired
    private WebUserDetailService webUserDetailService;

    @Autowired
    private SendService sendService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private FilesConfig filesConfig;

    @ApiOperation(value = "个人信息 - 查询用户信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/getUser" , method = RequestMethod.POST)
    public JsonResult getUser(@RequestBody @Validated(UserDTO.GetUser.class) UserDTO userDTO, BindingResult br) {
        log.debug("【START】 - function WebUserController - getUser");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function WebUserController - getUser 参数校验失败");
            return check;
        }
        List<UserVO> list = this.webUserService.getUser(userDTO);

        log.debug("【END】 - function end WebUserController - getUser，查询用户信息成功，查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS, list);
    }

    @ApiOperation(value = "个人信息 - 修改用户信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    public JsonResult update(@RequestBody @Validated(UserDTO.Update.class) UserDTO userDTO, BindingResult br) {
        log.debug("【START】 - function WebUserController - update");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function WebUserController - update 参数校验失败");
            return check;
        }

        //更新用户表
        boolean flag = this.webUserService.updateById(new WebUserPO()
                .setUserId(userDTO.getUserId())
                .setUserNickName(userDTO.getUserNickName())
                .setUpdateTime(DateUtil.getTime()));
        if(flag){
            //更新用户详情
            flag = this.webUserDetailService.update(new WebUserDetailPO()
                    .setUserName(userDTO.getUserName())
                    .setUserSex(userDTO.getUserSex())
                    .setUserPhone(userDTO.getUserPhone())
                    .setUserEmail(userDTO.getUserEmail())
                    .setUserHeadImage(userDTO.getUserHeadImage())
                    .setUpdateTime(DateUtil.getTime()),
                    new QueryWrapper(new WebUserDetailPO().setUserId(userDTO.getUserId())));
            if(flag){
                log.debug("【END】 - function end WebUserController - update，更新用户成功，更新的数据为：" + userDTO);
                return ResponseResult.Success(ResultCodeEnums.SUCCESS);
            }
            log.error("【ERROR】 - function end WebUserController - update，更新用户失败");
            return ResponseResult.Fail(ResultCodeEnums.FAIL);
        }
        log.error("【ERROR】 - function end WebUserController - update，更新用户失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL);
    }

    @ApiOperation(value = "上传头像")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "file", value = "图片文件", required = true, dataType = "file"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/upload" , method = RequestMethod.POST)
    public JsonResult upload(@RequestPart("file") MultipartFile file){
        log.debug("【START】 - function WebUserController upload");
        JsonResult jsonResult = FileUploadUtil.upload(file,filesConfig.getFormalPath(), FileUploadUrlEnum.USER_HEAD_URL.getMsg());
        log.debug("【END】 - function WebUserController upload");
        return jsonResult;
    }

    @ApiOperation(value = "个人信息 - 修改密码")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "int"),
        @ApiImplicitParam(name = "userPassword", value = "新密码", dataType = "string"),
        @ApiImplicitParam(name = "userValidPassword", value = "验证密码", dataType = "string"),
        @ApiImplicitParam(name = "userOldPassword", value = "原密码", dataType = "string"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/updatePassWord" , method = RequestMethod.POST)
    public JsonResult updatePassWord(@RequestBody @Validated(UserDTO.UpdatePassWord.class) UserDTO userDTO, BindingResult br) {
        log.debug("【START】 - function WebUserController - updatePassWord");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function WebUserController - updatePassWord 参数校验失败");
            return check;
        }
        //判断新密码和验证密码是否相等
        if(!userDTO.getUserPassword().equals(userDTO.getUserValidPassword())){
            //如果不相等，返回失败
            log.debug("【DEBUG】 - function end WebUserController - updatePassWord，更新失败，原因是：新密码与验证密码校验失败，传递的数据为："+userDTO);
            return ResponseResult.Fail(ResultCodeEnums.FAIL_11003);
        }

        //获取用户原信息
        List<WebUserPO> userList = this.webUserService.list(new QueryWrapper<>(new WebUserPO().setUserId(userDTO.getUserId())));
        String userOldPassword = userList.get(0).getUserPassword();
        //判断原密码是否相等
        if(!userOldPassword.equals(userDTO.getUserOldPassword())){
            //如果不相等，返回失败
            log.debug("【DEBUG】 - function end WebUserController - updatePassWord，更新失败，原因是：原密码校验失败，传递的数据为："+userDTO);
            return ResponseResult.Fail(ResultCodeEnums.FAIL_11007);
        }

        //验证通过，更新密码
        boolean flag = this.webUserService.updateById(new WebUserPO()
                .setUserId(userDTO.getUserId())
                .setUserPassword(userDTO.getUserPassword())
                .setUpdateTime(DateUtil.getTime()));
        if(flag){
            log.debug("【END】 - function end WebUserController - updatePassWord，更新用户密码成功");
            return ResponseResult.Success(ResultCodeEnums.SUCCESS);
        }
        log.error("【END】 - function end WebUserController - updatePassWord，更新用户密码失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL);
    }

    @ApiOperation(value = "个人信息 - 修改邮箱 - 获取邮箱验证码")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userEmail", value = "邮箱", dataType = "string"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/getEmailCode" , method = RequestMethod.POST)
    public JsonResult getEmailCode(@RequestBody @Validated(UserDTO.GetEmailCode.class) UserDTO userDTO, BindingResult br) {
        log.debug("【START】 - function WebUserController - getEmailCode");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function WebUserController - getEmailCode 参数校验失败");
            return check;
        }
        this.sendService.email(new EmailDTO()
                .setEmail(userDTO.getUserEmail())
                .setEmailTitle(EmailConstant.EMAIL_TITLE)
                .setEmailText(EmailConstant.EMAIL_TEXT));

        log.debug("【END】 - function end WebUserController - updatePassWord，更新用户密码成功");
        return ResponseResult.Success(ResultCodeEnums.SUCCESS);
    }

    @ApiOperation(value = "个人信息 - 修改邮箱信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "string"),
        @ApiImplicitParam(name = "userEmail", value = "邮箱", dataType = "string"),
        @ApiImplicitParam(name = "code", value = "邮箱验证码", dataType = "string"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/updateEmail" , method = RequestMethod.POST)
    public JsonResult updateEmail(@RequestBody @Validated(UserDTO.UpdateEmail.class) UserDTO userDTO, BindingResult br) {
        log.debug("【START】 - function WebUserController - updateEmail");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function WebUserController - updateEmail 参数校验失败");
            return check;
        }
        Integer code = redisService.getEmailCode(userDTO.getUserEmail());
        //如果为0，代表验证码过期
        if(code == 0){
            log.error("【ERROR】 - function WebUserController - updateEmail 验证码已过期");
            return ResponseResult.Fail(ResultCodeEnums.FAIL_11009);
        }
        //如果验证码验证错误，返回失败
        if(!code.equals(userDTO.getCode())){
            log.error("【ERROR】 - function WebUserController - updateEmail 验证码验证错误");
            return ResponseResult.Fail(ResultCodeEnums.FAIL_11008);
        }
        //验证通过，修改邮箱
        boolean flag = this.webUserDetailService.update(new WebUserDetailPO().setUserEmail(userDTO.getUserEmail()),
                new QueryWrapper<>(new WebUserDetailPO().setUserId(userDTO.getUserId())));
        if(flag){
            log.debug("【END】 - function end WebUserController - updatePassWord，更新用户密码成功");
            return ResponseResult.Success(ResultCodeEnums.SUCCESS);
        }
        log.debug("【END】 - function end WebUserController - updatePassWord，更新用户密码失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL);
    }
}
