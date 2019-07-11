package com.chenyifaer.basic.common.util;

import com.alibaba.fastjson.JSONObject;
import com.chenyifaer.basic.common.entity.LoginAppUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Map;

/**
 * 系统Oauth认证 - User信息统一处理
 * @Author:wudh
 * @Date: 2019/4/15 16:56
 */
public class SystemUserUtil {

    /**
     * 获取登陆的 LoginAppUser
     *
     * @return
     */
    public static LoginAppUser getLoginAppUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Auth = (OAuth2Authentication) authentication;
            authentication = oAuth2Auth.getUserAuthentication();

            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
                Object principal = authentication.getPrincipal();
                if (principal instanceof LoginAppUser) {
                    return (LoginAppUser) principal;
                }
                Map map = (Map) authenticationToken.getDetails();
                map = (Map) map.get("principal");
                return JSONObject.parseObject(JSONObject.toJSONString(map), LoginAppUser.class);
            }
        }
        return null;
    }
}
