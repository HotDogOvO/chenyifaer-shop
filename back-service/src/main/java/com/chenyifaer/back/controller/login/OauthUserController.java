package com.chenyifaer.back.controller.login;

import com.chenyifaer.back.annotation.LogAnnotation;
import com.chenyifaer.back.constant.LogConstant;
import com.chenyifaer.back.entity.dto.OauthUserDTO;
import com.chenyifaer.back.entity.vo.OauthUserVO;
import com.chenyifaer.back.service.AdminUserService;
import com.chenyifaer.basic.common.emuns.UserStatusEnum;
import com.chenyifaer.basic.common.entity.LoginAppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
 * Oauth2.0系统内部用户验证
 * @Author:wudh
 * @Date: 2019/4/14 15:34
 */
@Slf4j
@RestController
public class OauthUserController {

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 查询Oauth认证 - 登录用户详细信息
     * @Author:wudh
     * @Date: 2019/4/15 17:21
     */
    @RequestMapping(value = "/getOauthUserDetail" , method = RequestMethod.POST)
    public LoginAppUser login(@RequestParam("account") String account) {
        log.debug("[START] - function OauthUserController login");

        OauthUserVO oauthUserVO = this.adminUserService.getOauthUser(
                new OauthUserDTO().setAdminUserAccount(account));

        // 根据后台用户登录名查询出用户详情
        if (oauthUserVO != null ) {
            //為了防止惡意攻擊，設置用戶默認禁用
            boolean enabled = false;
            //如果當前用戶的狀態為啟用，則更改enabled的值為true
            if(oauthUserVO.getStatus().equals(UserStatusEnum.ENABLE.getCode())){
                enabled = true;
            }

            //插入返回的实体类
            LoginAppUser loginAppUser = new LoginAppUser();
            loginAppUser.setAdminUserId(oauthUserVO.getAdminUserId());
            loginAppUser.setAdminUserAccount(oauthUserVO.getAdminUserAccount());
            loginAppUser.setAdminUserName(oauthUserVO.getAdminUserName());
            loginAppUser.setEnabled(enabled);
            // 处理用户密码
            loginAppUser.setAdminUserPassword(new BCryptPasswordEncoder()
                    .encode(oauthUserVO.getAdminUserPassword()));

            this.loginLog(loginAppUser.getAdminUserName());

            log.debug("[END] - function OauthUserController login return");
            return loginAppUser;
        }
        log.debug("[error] - function OauthUserController login return: null");
        return null;
    }

    /**
     * 处理登录日志
     * @Author:wudh
     * @Date: 2019/4/15 17:20
     */
    @LogAnnotation(
            menuName = LogConstant.SYSTEM_MENU_NAME,
            action = LogConstant.LOGIN,
            operation = LogConstant.OPERATION_SYSTEM_LOGIN)
    public void loginLog(String username){
        log.debug("[RUN] - function loginLog 登录日志记录，登录的用户为：{}",username);
    }

}
