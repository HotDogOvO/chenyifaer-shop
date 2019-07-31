package com.chenyifaer.web.hanlder;
/**
 * _____ _            __     ___ ______                ________ ____ ______ ____
 * / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 * | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 * | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 * | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 * \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 */

import com.alibaba.fastjson.JSONObject;
import com.chenyifaer.basic.common.util.OkHttpUtil;
import com.chenyifaer.web.config.WeiboConfig;
import com.chenyifaer.web.constant.WeiboConstant;
import com.chenyifaer.web.entity.dto.WeiboUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 微博功能对接 - 工具类
 * @Author:wudh
 * @Date: 2019/7/30 16:52
 */
@Slf4j
@Service
public class WeiboHanlder {

    /** appKey */
    public final String appKey = WeiboConfig.appKey;
    /** appSecret */
    public final String appSecret = WeiboConfig.appSecret;
    /** redirectUrl */
    public final String redirectUrl = WeiboConfig.redirectUrl;
    /** grantType */
    public final String grantType = WeiboConfig.grantType;
//    /** appKey */
//    public final String appKey = "101740252";
//    /** appSecret */
//    public final String appSecret = "4251d31abbb2413bb758ef90742049d9";
//    /** redirectUrl */
//    public final String redirectUrl = "https://www.chenyifaer67373.com/loginRedirect";
//    /** grantType */
//    public final String grantType = "authorization_code";

    /**
     * 获取AccessToken & uid
     * @Author:wudh
     * @Date: 2019/7/30 16:53
     */
    public Map<String,Object> getAccessToken(String code){
        String url = WeiboConstant.ACCESS_TOKEN_URL
                .replace("${appKey}",appKey)
                .replace("${appSecret}",appSecret)
                .replace("${redirectUrl}",redirectUrl)
                .replace("${grantType}",grantType)
                .replace("${code}",code);
        JSONObject jsonObject = OkHttpUtil.doPostJson(url,"");
        Map<String,Object> map = new HashMap<>(2);
        map.put("accessToken",jsonObject.get("access_token"));
        map.put("uid",jsonObject.get("uid"));
        return map;
    }

    /**
     * 获取用户信息
     * @Author:wudh
     * @Date: 2019/7/30 17:36
     */
    public WeiboUserDTO getUserDetail(String accessToken, String uid){
        String url = WeiboConstant.USER_DETAIL_URL
                .replace("${accessToken}",accessToken)
                .replace("${uid}",uid);
        JSONObject jsonObject = OkHttpUtil.doGetJson(url);
        WeiboUserDTO weiboUserDTO = jsonObject.toJavaObject(WeiboUserDTO.class);
        return weiboUserDTO;
    }
}
