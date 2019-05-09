package com.chenyifaer.web.entity.dto;
/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 前台用户 - DTO
 * @Author:wudh
 * @Date: 2019/5/9 16:54
 */
@Data
@Accessors(chain = true)
public class UserDTO {

    public interface Register{};

    public interface Login{};

    /** 用户名 */
    @NotNull(groups = {UserDTO.Register.class,UserDTO.Login.class} , message = "用户名不能为空")
    private String userAccount;

    /** 密码 */
    @NotNull(groups = {UserDTO.Register.class,UserDTO.Login.class} , message = "密码不能为空")
    private String userPassword;

    /** 验证密码 */
    @NotNull(groups = {UserDTO.Register.class} , message = "验证密码不能为空")
    private String userValidPassword;

    /** 用户注册渠道（1：注册 2：微信用户 3：QQ用户 4：支付宝用户 5：微博用户） */
    private Integer userType;

}
