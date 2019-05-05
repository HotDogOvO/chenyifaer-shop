package com.chenyifaer.basic.authorization.service;

import com.chenyifaer.basic.authorization.feign.BackUserFeign;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.entity.LoginAppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
 * 重写Oauth2内部方法，实现系统内部用户认证
 * @Author:wudh
 * @Date: 2019/4/13 16:38
 */
@Slf4j
@Service("userDetailsService")
public class UserService implements UserDetailsService {

    @Autowired
    private BackUserFeign backUserFeign;

    /**
     * Oauth2.0用户验证
     * 由于Oauth验证需要的参数必须为username
     * 所以这里的参数为username，但实际上传递的为account
     * @Author:wudh
     * @Date: 2019/4/13 18:21
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("[START] - function loadUserByUsername");

        // 为了支持多类型登录，这里username后面拼装上登录类型,如username|type
        String[] params = username.split("\\|");

        // 真正的用户名
        String relAccount = params[0];

        log.debug("[RUN] - function loadUserByUsername - 用户验证 - 传递的account为：" + relAccount);

        // 用户名模式
        LoginAppUser loginAppUser = backUserFeign.findByUsername(relAccount);

        // Oauth验证失败
        if (loginAppUser == null) {
            // 用户名密码错误
            log.debug("[RUN] - function loadUserByUsername - 用户验证失败，原因是：用户名密码错误");
            throw new AuthenticationCredentialsNotFoundException(ResultCodeEnums.FAIL_11001.msg());
        } else if (loginAppUser.getEnabled() == false) {
            // 当前用户被禁用用户禁用
            log.debug("[RUN] - function loadUserByUsername - 用户验证失败，原因是：用户名被禁用");
            throw new DisabledException(ResultCodeEnums.FAIL_11002.msg());
        }
        log.debug("[END] - function loadUserByUsername");
        return loginAppUser;
    }
}
